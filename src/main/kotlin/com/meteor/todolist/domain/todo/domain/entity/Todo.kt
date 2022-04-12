package com.meteor.todolist.domain.todo.domain.entity

import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

// DB 와 매핑되는 Entity

@Entity
class Todo(
    // Todo Entitny 의 생성자로 만들어준다
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity DB Autoincrement 전략 따라감
    var id : Long? = null,

    // 해당 변수에 null 이 들어가지 못하게
    // @Column 어노테이션으로 nullable false 를 주었다
    @Column(nullable = false)
    @ColumnDefault("false")
    var completed : Boolean = false,

    @Column(nullable = false)
    var todoName : String

)