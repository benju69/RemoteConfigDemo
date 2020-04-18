package me.benju.remoteconfigdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title = findViewById<TextView>(R.id.holidayPromoTitleTextView)
        val button = findViewById<Button>(R.id.holidayPromoCallToActionButton)
        val card = findViewById<CardView>(R.id.holidayPromoCard)

        title.text = RemoteConfigurationService.getHolidayPromoTitle
        button.text = RemoteConfigurationService.getHolidayPromoButton
        card.visibility =
            if (RemoteConfigurationService.getHolidayPromoEnabled) View.VISIBLE else View.GONE
    }

}
