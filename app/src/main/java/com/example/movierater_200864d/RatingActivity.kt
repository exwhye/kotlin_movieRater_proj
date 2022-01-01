package com.example.movierater_200864d

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.movierater_200864d.R
import android.view.View

import android.widget.RatingBar
import kotlinx.android.synthetic.main.activity_rating.*


class RatingActivity : AppCompatActivity() {
    var numberOfStars:String =""
    var ratingReview =""

    companion object{
        val STARS = "stars"
        val REVIEWS = "reviews"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        val actionbar = supportActionBar
        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(true)
        movieReviewTV.text = intent.getStringExtra("movieName")


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.rating,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.submitRating) {
            val simpleRatingBar =
                findViewById<View>(R.id.rBar) as RatingBar // initiate a rating bar
            numberOfStars = simpleRatingBar.rating.toString()
            ratingReview = ratingReviewET.text.toString()
            var updatedReviewsIntent = Intent()
            updatedReviewsIntent.putExtra(STARS, numberOfStars)
            updatedReviewsIntent.putExtra(REVIEWS,ratingReview)
            setResult(Activity.RESULT_OK,updatedReviewsIntent)
            finish()

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }

}