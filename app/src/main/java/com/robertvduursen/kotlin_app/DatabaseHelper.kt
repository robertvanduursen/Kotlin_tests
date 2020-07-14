package com.robertvduursen.kotlin_app

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.robertvduursen.kotlin_app.database.myActivities

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 2
        val DATABASE_NAME = "First_SQL_Database.db"
        val TABLE_NAME = DBcontract.UserEntry.TABLE_NAME
        val Col_1 = DBcontract.UserEntry.COLUMN_ID
        val Col_2 = DBcontract.UserEntry.COLUMN_FNAME


        private val SQL_CREATE_DATABASE =
            "CREATE TABLE " + DBcontract.UserEntry.TABLE_NAME + " (" +
                    DBcontract.UserEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBcontract.UserEntry.COLUMN_FNAME + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBcontract.UserEntry.TABLE_NAME
    }


    override fun onCreate(db: SQLiteDatabase) {
        // create the database
        db.execSQL(SQL_CREATE_DATABASE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // discard the date and begin again
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertData(fname: String): Long {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val insertvalues = ContentValues()
        insertvalues.put(DBcontract.UserEntry.COLUMN_FNAME, fname)

        // Insert the new row, returning the primary key value of the new row
        val action = db.insert(DBcontract.UserEntry.TABLE_NAME, null, insertvalues)
        db.close() //close database connection
        return action
    }

    @Throws(SQLiteConstraintException::class)
    fun changeData(peop: myActivities): Int {
        // Gets the data repository in write mode
        val db = writableDatabase

        val changevalues = ContentValues()
        changevalues.put(DBcontract.UserEntry.COLUMN_ID, peop.id)
        changevalues.put(DBcontract.UserEntry.COLUMN_FNAME, peop.fname)


        // Which row to update, based on the ID
        val selection = "${DBcontract.UserEntry.COLUMN_ID} LIKE ?"
        val selectionArgs = arrayOf(peop.id)
        val action = db.update(DBcontract.UserEntry.TABLE_NAME, changevalues, selection, selectionArgs)
        db.close()
        return action
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteData(id: String): Int {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBcontract.UserEntry.COLUMN_ID + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(id)
        // SQL statement which will return number of rows deleted
        val action = db.delete(DBcontract.UserEntry.TABLE_NAME, selection, selectionArgs)
        db.close()
        return action
    }


    fun readAllData(): ArrayList<myActivities> {
        val dBdata = ArrayList<myActivities>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBcontract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_DATABASE)
            return ArrayList()
        }

        var id: String
        var fname: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getString(cursor.getColumnIndex(DBcontract.UserEntry.COLUMN_ID))
                fname = cursor.getString(cursor.getColumnIndex(DBcontract.UserEntry.COLUMN_FNAME))

                dBdata.add(myActivities(id, fname))
                cursor.moveToNext()
            }
        }
        print(">> COMPLETE")
        return dBdata
    }

}