package com.meteor.todolist.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    // 코틀린은 타입스크립트랑 많이 비슷함 ㅋㅋ
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**") // 어떤 경로를 허락 해줄건지 설정하는 부분 => 모든경로는 **
            .allowedOrigins("http://localhost:3000") // 특정 도메인만 허용 해주는 설정
            .allowedOrigins("http://127.0.0.1:5501") // 특정 도메인만 허용 해주는 설정
//            .allowedOrigins("http://127.0.0.1:5500") // 특정 도메인만 허용 해주는 설정
//            .allowedOrigins("http://192.168.0.49:5500") // 특정 도메인만 허용 해주는 설정
            .allowedMethods(
                HttpMethod.GET.name,
                HttpMethod.POST.name,
                HttpMethod.PUT.name,
                HttpMethod.DELETE.name,
            )
    }

}

