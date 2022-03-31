package com.meteor.todolist.module.service

import com.meteor.todolist.module.domain.Todo
import com.meteor.todolist.module.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    /**
     * 할 일 리스트 전부 조회
     */
    fun getTodos(): MutableIterable<Todo> = todoRepository.findAllByOrderByIdDesc()

    /**
     * 투두 리스트 추가
     */
    // 네이밍 파라미터를 사용했네
    fun insertTodo(todoName:String) = todoRepository.save(Todo(todoName = todoName)) // 로직이 한줄이면 중괄호 없이 = 으로 표현 가능

    /**
     * 할일 상태 변경
     */
    fun updateTodo(todoId: Long) : Todo {
        // todoId 값을 찾아보고 있으면 반환 하고 없으면 null 을 반환한다.
        var todo = todoRepository.findByIdOrNull(todoId)?: throw Exception()
        todo.completed = !todo.completed // 지금 상태의 반대로 , 완료 -> 미완료 / 미완료 -> 완료
        return todoRepository.save(todo)
    }

    /**
     * 할일 삭제
     */
    fun deleteTodo(todoId: Long) = todoRepository.deleteById(todoId) // 로직이 한줄이면 중괄호 없이 = 으로 표현 가능





}