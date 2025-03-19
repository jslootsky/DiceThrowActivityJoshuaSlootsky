package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //can potentially cause an error since this is executed asynchronously
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView1, DieFragment.newInstance(20)) // Using newInstance()
                .add(R.id.fragmentContainerView2, DieFragment.newInstance(20))
                .commit()
        }

        //since the only functionality is to click a button, not really an issue
        findViewById<Button>(R.id.rollDiceButton).setOnClickListener{
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView1) as DieFragment)
                .throwDie()
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as DieFragment)
                .throwDie()
        }
    }
}