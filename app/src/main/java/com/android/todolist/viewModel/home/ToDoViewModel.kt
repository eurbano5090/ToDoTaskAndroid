package com.android.todolist.viewModel.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.todolist.data.entity.AppDatabase
import com.android.todolist.data.models.ToDoItem
import com.android.todolist.data.repositories.ToDoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ToDoRepository

    //-> StateFlow para observar cambios automáticamente
    private val _toDoList = MutableStateFlow<List<ToDoItem>>(emptyList())
    val toDoList: StateFlow<List<ToDoItem>> = _toDoList.asStateFlow()

    private val _toDoActual = MutableStateFlow<ToDoItem?>(null)
    val toDoActual: StateFlow<ToDoItem?> = _toDoActual.asStateFlow()

    val pendingToDos: StateFlow<List<ToDoItem>> = _toDoList.map { list ->
        list.filter { !it.isDone }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val doneToDos: StateFlow<List<ToDoItem>> = _toDoList.map { list ->
        list.filter { it.isDone }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    init {
        val dao = AppDatabase.getDatabase(application).toDoDao()
        repository = ToDoRepository(dao)
        observarToDos()
    }

    private fun observarToDos() {
        viewModelScope.launch {
            repository.obtenerToDos().collect { lista ->
                _toDoList.value = lista
            }
        }
    }
    fun cargarToDo(id: String) {
        viewModelScope.launch {
            _toDoActual.value = repository.obtenerPorId(id)
        }
    }

    fun limpiarToDoActual() {
        _toDoActual.value = null
    }

    fun insertarToDo(toDo: ToDoItem) = viewModelScope.launch {
        repository.insertarToDo(toDo)
    }

    fun removeToDo(id: String) = viewModelScope.launch {
        repository.eliminarToDo(id)
    }

    fun actualizarToDo(toDo: ToDoItem) = viewModelScope.launch {
        repository.actualizarToDo(toDo)
    }

    fun doneToDos(id: String) = viewModelScope.launch {
        _toDoList.value = _toDoList.value.map {
            if (it.id == id) it.copy(isDone = !it.isDone) else it
        }

        val item = _toDoList.value.find { it.id == id }

        item?.let {
            repository.toggleDone(id, it.isDone)
        }
    }

    suspend fun obtenerPorId(id: String): ToDoItem? {
        return repository.obtenerPorId(id)
    }
}

// Estados de UI
sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    data class Error(val message: String) : UiState()
}

