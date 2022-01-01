package com.example.movierater_200864d

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatDelegate




class MainActivity : AppCompatActivity() {
    var myDB:MyDBAdapter? = null
    var moviesAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.addMovieMainMI){
            var MyAddMovieIntent = Intent(this,AddMovieActivity::class.java)
            startActivity(MyAddMovieIntent)
        }
        else if(item.itemId == R.id.night_mode){
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toggleVisibility() {
        if (moviesLV.count > 0) {
            noitemsTV.visibility = View.GONE
            moviesLV.visibility = View.VISIBLE
        } else {
            moviesLV.visibility = View.GONE
            noitemsTV.visibility = View.VISIBLE
        }
    }

    private fun retrieveMovies() {
        val contactList: List<String>
        val mc = MyMovies.ourInstance
        contactList = mc.retrieveAll(applicationContext)
        moviesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList)
        moviesLV.adapter = moviesAdapter

    }

    override fun onResume() {
        retrieveMovies()
        toggleVisibility()
        super.onResume()
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View,
                                     menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v.id == R.id.moviesLV) {
            menu.add(1, 0, 0, "Remove")
        }

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val mc = MyMovies.ourInstance
        mc.deleteFrmDatabase(info.position,applicationContext)
        retrieveMovies()
        toggleVisibility()
        return super.onContextItemSelected(item)
    }
}