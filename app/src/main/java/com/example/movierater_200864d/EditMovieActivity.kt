package com.example.movierater_200864d

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import com.example.movierater_200864d.data.MovieRating
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_add_movie.chkBoxAudience
import kotlinx.android.synthetic.main.activity_add_movie.chkBoxLanguageUsed
import kotlinx.android.synthetic.main.activity_add_movie.chkBoxViolence
import kotlinx.android.synthetic.main.activity_edit_movie.*

class EditMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)
        // calling the action bar
        var actionBar = supportActionBar

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        chkBoxAudience.setOnClickListener{
            if (chkBoxAudience.isChecked == true){
                chkBoxViolence.visibility = View.VISIBLE;
                chkBoxLanguageUsed.visibility = View.VISIBLE;
            }

            else{
                chkBoxViolence.visibility = View.INVISIBLE;
                chkBoxLanguageUsed.visibility = View.INVISIBLE;
//                reason = ""
//                violenceCheker = ""
//                langChecker = ""
            }

        }
        var i = MovieRating(
            "Venom",
            "Overview",
            "No Reviews yet. \\nLong press here to add your review.",
            "English",
            "19-10-2018",
            "Yes"
        )
        (titleEditET as TextView).text = i.name
        (descriptionEditET as TextView).text = i.description
        (releaseDateEditET as TextView).text = i.releaseDate

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_movie_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
}