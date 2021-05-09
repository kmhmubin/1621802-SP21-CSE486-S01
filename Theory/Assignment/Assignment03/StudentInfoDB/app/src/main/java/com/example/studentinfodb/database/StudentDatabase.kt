package com.example.studentinfodb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/*
Annotates class with room database with a table of student class
 */

@Database(entities = [StudentInfo::class], version = 1, exportSchema = false)
public abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDAO(): StudentDAO

    companion object {
        /*
        Singleton prevents multiple instances of database opening at the same time
         */
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase {
            /*
            if the [INSTANCE] is not null, then return it, if it is, then create the database
             */
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}