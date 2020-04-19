package me.benju.remoteconfigdemo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
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
        button.setOnClickListener {
            openLink()
        }
        card.visibility =
            if (RemoteConfigurationService.getHolidayPromoEnabled) View.VISIBLE else View.GONE
    }

    private fun openLink() {
        try {
            val url = RemoteConfigurationService.getHolidayPromoLink
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
