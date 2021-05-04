package juarez.roberto.androidbasemodule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import juarez.roberto.mylibrary.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}