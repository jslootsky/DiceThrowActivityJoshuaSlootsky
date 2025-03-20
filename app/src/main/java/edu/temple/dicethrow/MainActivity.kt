package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    
    val dieViewModel : DieViewModel by lazy {
        ViewModelProvider(this)[DieViewModel::class.java]
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //can potentially cause an error since this is executed asynchronously
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment.newInstance(20)) // Using newInstance()
                .commit()
        }

        //since the only functionality is to click a button, not really an issue
        findViewById<Button>(R.id.rollDiceButton).setOnClickListener {
            dieViewModel.throwDie()
        }
    }
}