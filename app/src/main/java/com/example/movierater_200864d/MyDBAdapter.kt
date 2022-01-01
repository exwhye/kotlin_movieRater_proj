package com.example.movierater_200864d

import android.content.Context
import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
class MyDBAdapter (c : Context) {


    private val DATABASE_NAME = "test.db"

    private val DATABASE_TABLE = "myTestDb"
    private val DATABASE_VERSION = 1
    private var _db: SQLiteDatabase? = null
    private val context: Context?= null

    val KEY_ID = "_id"
    val COLUMN_KEY_ID = 0
    val MOVIE_NAME = "Movie_name"
    val COLUMN_NAME_ID = 1
    val DESCRIPTION = "Description"
    val COLUMN_DESCRIPTION_ID = 2
//    val DESCRIPTION = "Description"
//    val COLUMN_DESCRIPTION_ID = 2

    protected val DATABASE_CREATE = ("create table " + DATABASE_TABLE + " " + "(" + KEY_ID + " integer primary key autoincrement, " + MOVIE_NAME + " Text, "
            + DESCRIPTION + " text not null);")

    private val MYDBADAPTER_LOG_CAT = "MY_LOG"

    private var dbHelper: MyDBOpenHelper? = null

    init {
        //TODO 1 : Create a MyDBOpenHelper object
        dbHelper = MyDBOpenHelper(c, DATABASE_NAME,DATABASE_VERSION)
    }

    fun close() {
        //TODO 2 : close the table using the SQLite database handler
        _db?.close()
    }


    fun open() {
        //TODO 3 : Open DB using the appropriate methods
        try{
            _db = dbHelper?.getWritableDatabase()
        }
        catch(e: SQLException){
            _db = dbHelper?.getReadableDatabase()

        }


    }

    fun insertEntry(MovieName: String, Description: String): Long? {
        //TODO 4 - insert record into table
        val newEntryValues = ContentValues()

        newEntryValues.put(MOVIE_NAME, MovieName)
        newEntryValues.put(DESCRIPTION,Description)

        return _db?.insert(DATABASE_TABLE, null , newEntryValues)
    }

    fun removeEntry(_rowIndex: Int): Boolean {
        //TODO 5 - remove record from table
        if(_db!!.delete(DATABASE_TABLE,
                KEY_ID + " = " + _rowIndex,
                null)<=0){
            Log.w(MYDBADAPTER_LOG_CAT,"Removing entry where id = $_rowIndex failed")
            return false
        }

        return true
    }

    fun updateEntry(_rowIndex: Int, MovieName: String, Description: String): Boolean {


        return false
    }

    fun retrieveAllEntriesCursor(): Cursor? {
        //TODO 6 - retrieve all records from table

        var c: Cursor? = null
        try{
            c = _db?.query(DATABASE_TABLE,
                arrayOf(KEY_ID,MOVIE_NAME,DESCRIPTION),
                null,
                null,
                null,
                null,
                null)
        } catch (e: SQLiteException){
            Log.w(DATABASE_TABLE, "Retrieve file")
        }


        return c
    }



    inner class MyDBOpenHelper(c: Context, db_name : String, ver_no : Int ): SQLiteOpenHelper(c, db_name, null, ver_no){


        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(DATABASE_CREATE)
            Log.w(MYDBADAPTER_LOG_CAT, "HELPER : DB $DATABASE_TABLE created!")

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }
    }

}