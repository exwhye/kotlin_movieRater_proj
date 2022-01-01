package com.example.movierater_200864d
import android.app.Application
import android.content.Context
import android.database.Cursor
import java.util.ArrayList
class MyMovies () : Application(){
    private var movieList: ArrayList<String> = ArrayList<String>()
    private var movieIdList: ArrayList<Int> = ArrayList<Int>()
    companion object{
        val ourInstance = MyMovies()
    }

    fun addToDatabase(Name: String, description: String, c: Context): Long? {
        val db = MyDBAdapter(c)
        db.open()

        val rowIDofInsertedEntry = db.insertEntry(Name,description)
        db.close()

        return rowIDofInsertedEntry

    }

    fun deleteFrmDatabase(rowID: Int, c: Context): Boolean {
        val db = MyDBAdapter(c)
        db.open()

        val id = movieIdList[rowID]

        val updateStatus = db.removeEntry(id)
        db.close()
        return updateStatus

    }


    fun retrieveAll(c: Context): List<String> {

        val myCursor: Cursor?
        var myString = ""
        val db = MyDBAdapter(c)
        db.open()
        movieIdList.clear()
        movieList.clear()
        myCursor = db.retrieveAllEntriesCursor()
        if (myCursor!=null && myCursor!!.count>0){
            myCursor!!.moveToFirst()
            do{
                movieIdList.add(myCursor.getInt(db.COLUMN_KEY_ID))
                myString = myCursor.getString(db.COLUMN_NAME_ID) + "\n"
                movieList.add(myString)
            }while (myCursor.moveToNext())
        }
        db.close()
        return movieList

    }
}