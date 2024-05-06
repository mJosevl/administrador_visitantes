package com.example.mjosevl_20240505.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mjosevl_20240505.entidades.Visitor

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "VisitorDatabase.db"
        const val TABLE_VISITORS = "visitors"
        private const val KEY_RUT = "rut"
        private const val KEY_FIRST_NAME = "firstName"
        private const val KEY_LAST_NAME = "lastName"
        private const val KEY_ENTRY_TIME = "entryTime"
        private const val KEY_EXIT_TIME = "exitTime"
        private const val KEY_APARTMENT = "apartment"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_VISITORS(" +
                "$KEY_RUT TEXT PRIMARY KEY," +
                "$KEY_FIRST_NAME TEXT," +
                "$KEY_LAST_NAME TEXT," +
                "$KEY_ENTRY_TIME INTEGER," +
                "$KEY_EXIT_TIME INTEGER," +
                "$KEY_APARTMENT TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_VISITORS")
        onCreate(db)
    }
    fun addVisitor(visitor: Visitor): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(KEY_RUT, visitor.rut)
            put(KEY_FIRST_NAME, visitor.firstName)
            put(KEY_LAST_NAME, visitor.lastName)
            put(KEY_ENTRY_TIME, visitor.entryTime)
            put(KEY_EXIT_TIME, visitor.exitTime)
            put(KEY_APARTMENT, visitor.apartment)
        }
        val result = db.insert(TABLE_VISITORS, null, values)
        db.close()
        return result != -1L  // Devuelve true si la inserción fue exitosa
    }




    fun getAllVisitors(): List<Visitor> {
        val visitorList = mutableListOf<Visitor>()
        val selectQuery = "SELECT * FROM $TABLE_VISITORS"
        this.readableDatabase.use { db ->
            db.rawQuery(selectQuery, null).use { cursor ->
                val rutIndex = cursor.getColumnIndexOrThrow(KEY_RUT)
                val firstNameIndex = cursor.getColumnIndexOrThrow(KEY_FIRST_NAME)
                val lastNameIndex = cursor.getColumnIndexOrThrow(KEY_LAST_NAME)
                val entryTimeIndex = cursor.getColumnIndexOrThrow(KEY_ENTRY_TIME)
                val exitTimeIndex = cursor.getColumnIndexOrThrow(KEY_EXIT_TIME)
                val apartmentIndex = cursor.getColumnIndexOrThrow(KEY_APARTMENT)

                if (cursor.moveToFirst()) {
                    do {
                        val visitor = Visitor(
                            cursor.getString(rutIndex),
                            cursor.getString(firstNameIndex),
                            cursor.getString(lastNameIndex),
                            cursor.getLong(entryTimeIndex),
                            cursor.getLong(exitTimeIndex),
                            cursor.getString(apartmentIndex)
                        )
                        visitorList.add(visitor)
                    } while (cursor.moveToNext())
                }
            }
        }
        return visitorList
    }
}
