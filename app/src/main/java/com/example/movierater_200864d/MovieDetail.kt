package com.example.movierater_200864d

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import com.example.movierater_200864d.data.MovieRating
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetail : AppCompatActivity() {
    var movieName = ""
    companion object{
        val ADDED_MOVIE_DETAILS = "added movie details"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        registerForContextMenu(reviewPressTV)
        val i = intent.getSerializableExtra(ADDED_MOVIE_DETAILS) as MovieRating
        displayedNameTV.text = i.name
        overviewedTV.text = i.description
        reviewPressTV.text = i.review
        displayedLanguageTV.text = i.language
        displayReleasedDateTV.text = i.releaseDate
        pg13ChkedTV.text = i.audienceChkBox
        movieName = i.name
    }

        override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if(v?.id == R.id.reviewPressTV)
        {
            menu?.add(1,1001,1,"Add Review")
        }
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(item.itemId == 1001){
            var MyRatingIntent = Intent(this,RatingActivity::class.java)
            MyRatingIntent.putExtra("movieName",movieName)
            startActivityForResult(MyRatingIntent,1)
            //startActivity(MyRatingIntent)
        }
        return super.onContextItemSelected(item)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1){
            if (resultCode == RESULT_OK)
            {
                var stars = data?.getStringExtra(RatingActivity.STARS)
                var reviewTxt = data?.getStringExtra(RatingActivity.REVIEWS)

                reviewPressTV.visibility = View.GONE

                hiddenRBarsTV.rating = stars!!.toFloat()
                hiddenRBarsTV.visibility = View.VISIBLE

                hiddenReviewTV.text = reviewTxt
                hiddenReviewTV.visibility = View.VISIBLE
            }

        }
    }



}