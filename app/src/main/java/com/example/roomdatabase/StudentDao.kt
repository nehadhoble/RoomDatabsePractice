package com.example.roomdatabase

import androidx.room.*

@Dao
interface StudentDao { //Dao- Data Access Object

        @Query("SELECT * FROM student_table")
        fun getAll(): List<Student?>?


        @Query("SELECT * FROM student_table WHERE roll_no LIKE :roll")
        suspend fun findByRoll(roll: Int): Student


        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(student: Student)

        @Delete
        suspend fun delete(student: Student?)

        @Query("DELETE FROM student_table")
        suspend fun deleteAll()
    }

    /*
     we are going to call this functions in coroutines
     beacause it is IO operations so take time to execute
     Therefore we defined above functions as suspended function
     */
