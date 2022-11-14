package com.example.taskandnotes.repository

import com.example.taskandnotes.data.dao.NotaDao
import androidx.annotation.WorkerThread
import com.example.taskandnotes.data.model.Nota
import kotlinx.coroutines.flow.Flow

class NotaRepository (private val daoLocal: NotaDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allNotas: Flow<List<Nota>> = daoLocal.getAllOrder()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAsync(nota: Nota) {
        daoLocal.insertar(nota)
    }

}