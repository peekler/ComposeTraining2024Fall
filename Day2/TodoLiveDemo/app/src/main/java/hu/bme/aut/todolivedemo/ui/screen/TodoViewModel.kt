package hu.bme.aut.todolivedemo.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private var _todoList =
        mutableStateListOf<TodoItem>()

    fun getAllToDoList(): List<TodoItem> {
        return _todoList
    }

    fun addTodoList(todoItem: TodoItem) {
        _todoList.add(todoItem)
    }

    fun removeTodoItem(todoItem: TodoItem) {
        _todoList.remove(todoItem)
    }

    fun changeTodoState(todoItem: TodoItem, value: Boolean) {
        val index = _todoList.indexOf(todoItem)

        val newTodo = todoItem.copy(
            title = todoItem.title,
            description = todoItem.description,
            createDate = todoItem.createDate,
            isDone = value
        )

        _todoList[index] = newTodo
    }

    fun clearAllTodos() {
        _todoList.clear()
    }
}

data class TodoItem(
    val id: Int,
    val title:String,
    val description:String,
    val createDate:String,
    var isDone: Boolean
)