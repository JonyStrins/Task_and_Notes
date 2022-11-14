package com.example.taskandnotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taskandnotes.data.dao.NotaDao
import com.example.taskandnotes.data.model.Nota
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Nota::class], version = 1)
abstract class MisNotasDatabase : RoomDatabase() {
    abstract fun notaDao() : NotaDao

    companion object{
        private var INSTANCE: MisNotasDatabase? = null
        val databaseWriteExecutor : ExecutorService
                = Executors.newFixedThreadPool(4)
        fun getDataBase(context : Context,
                        scope: CoroutineScope
        ) : MisNotasDatabase{
            return  INSTANCE ?: synchronized(this){
                val instance  =
                    Room.databaseBuilder(context,
                        MisNotasDatabase::class.java,
                        "midatabase"
                    ).addCallback(NotaDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class NotaDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.notaDao())
                }
            }
        }

        suspend fun populateDatabase(notaDao: NotaDao) {
            // Delete all content here.
            notaDao.deleteAll()

            // Add sample words.
            var nota = Nota(0, "Primer nota","Desc nota",null, null, Calendar.DATE)
            notaDao.insertar(nota)
        }
    }
}