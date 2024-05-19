import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.signup.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val welcomeTextView: TextView = findViewById(R.id.welcomeTextView)
        val userName = intent.getStringExtra("userName")
        Log.d("Kamesh", "datamila")

        welcomeTextView.text = "Welcome, $userName!"
    }
}