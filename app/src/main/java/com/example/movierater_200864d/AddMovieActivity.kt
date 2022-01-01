package com.example.movierater_200864d

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.content.res.TypedArrayUtils.getText
import com.example.movierater_200864d.data.MovieRating
import kotlinx.android.synthetic.main.activity_add_movie.*

class AddMovieActivity : AppCompatActivity() {
    var langChosen:String = "English"
    var reason:String = ""
    var violenceCheker: String = ""
    var langChecker: String = ""
    var chkbox:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)
        val actionbar = supportActionBar
        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(true)


        //val radio: RadioButton = findViewById(languageRbtn.checkedRadioButtonId)
        languageRbtn.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                if (p1 == R.id.rbtnEnglish) {
                    langChosen = "English"
                } else if (p1 == R.id.rbtnChinese) {
                    langChosen = "chinese"
                } else if (p1 == R.id.rbtnMalay) {
                    langChosen = "Malay"
                } else {
                    langChosen = "Tamil"
                }
            }
        })

        chkBoxAudience.setOnClickListener {
            if (chkBoxAudience.isChecked == true) {
                chkBoxViolence.visibility = View.VISIBLE;
                chkBoxLanguageUsed.visibility = View.VISIBLE;
                reason = "Reason:"
            } else {
                chkBoxViolence.visibility = View.INVISIBLE;
                chkBoxLanguageUsed.visibility = View.INVISIBLE;
                reason = ""
                violenceCheker = ""
                langChecker = ""
            }

        }

        chkBoxViolence.setOnClickListener {
            if (chkBoxViolence.isChecked == true) {
                violenceCheker = "Violence"
            } else {
                violenceCheker = ""
            }
        }

        chkBoxLanguageUsed.setOnClickListener {
            if (chkBoxLanguageUsed.isChecked == true) {
                langChecker = "Language"
            } else {
                langChecker = ""
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_movie_updated,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.id.addMovieMI) {
            var id: Int = languageRbtn.checkedRadioButtonId
            if (titleET.text.toString().isEmpty()) {
                titleET.error = "Movie title cannot be empty"
            } else if (descriptionET.text.toString().isEmpty()) {
                descriptionET.error = "Description cannot be empty"
            } else if (id == -1) {
                displayToast("enter language")
            } else if (releaseDateET.text.toString().isEmpty()) {
                releaseDateET.error = "Release Date cannot be empty"
            } else {
                if(chkBoxAudience.isChecked == true){
                    chkbox = "yes"
                }
                else{
                    chkbox = "No"
                }
                val mv = MyMovies.ourInstance
                mv.addToDatabase(titleET.text.toString(),descriptionET.text.toString(),applicationContext)

                val movieDataObj = MovieRating(titleET.text.toString(),descriptionET.text.toString(),"No Reviews yet. \r\nLong press here to add your review.",langChosen,releaseDateET.text.toString(),chkbox)
                var MyMovieDetailsIntent = Intent(this,MovieDetail::class.java)
                MyMovieDetailsIntent.putExtra(MovieDetail.ADDED_MOVIE_DETAILS,movieDataObj)
                startActivity(MyMovieDetailsIntent)
            }

        }
        //clear function
        else if(item.itemId == R.id.clear){
            (titleET as TextView).text = ""
            (descriptionET as TextView).text = ""
            rbtnEnglish.isChecked = false
            rbtnChinese.isChecked = false
            rbtnMalay.isChecked = false
            rbtnTamil.isChecked = false
            (releaseDateET as TextView).text = ""
            chkBoxAudience.isChecked = false
            chkBoxLanguageUsed.isChecked = false
            chkBoxViolence.isChecked = false
        }


        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun displayToast(message:String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    //TODO 13 : Add listener to add contact into database

//    addRecordsBtn.setOnClickListener {
//        val mc = MyContacts.ourInstance
//        var addNameStr= addNameET.text.toString()
//        var addNumberStr = addNumET.text.toString()
//
//        mc.addToDatabase(addNameStr,addNumberStr,applicationContext)
//
//        finish()
//
//    }
}