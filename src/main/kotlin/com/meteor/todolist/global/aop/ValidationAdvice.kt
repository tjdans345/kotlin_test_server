package com.meteor.todolist.global.aop

import com.meteor.todolist.global.error.common.CommonException
import org.apache.juli.logging.Log
import org.apache.juli.logging.LogFactory
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class ValidationAdvice {
    val logger: Log = LogFactory.getLog(ValidationAdvice::class.java)

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    fun postMapping(): Unit {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    fun putMapping(): Unit {
    }

    @Before("postMapping() || putMapping()")
    fun validationCheck(joinPoint: JoinPoint) {
        val args = joinPoint.args
        for (arg in args) {
            if(arg is BindingResult) {
                if (arg.hasErrors()) {
                    throw CommonException(code = "Validation Errors", message = arg.fieldError?.defaultMessage, status = HttpStatus.BAD_REQUEST)
                }
            }
        }
    }

    @Before("@annotation(CustomAnnotation)")
    fun setAuthorDefault(joinPoint: JoinPoint) {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request // request 정보
        logger.info("Before")
//        logger.info("Request URI : ${request.requestURI}")
//        logger.info("Request IP : ${request.remoteAddr}")
//        logger.info("Request Method : ${request.method}")
    }

    @After("@annotation(CustomAnnotationTest)")
    fun customAnnotationAdvice() {
        logger.info("After")
    }
}

