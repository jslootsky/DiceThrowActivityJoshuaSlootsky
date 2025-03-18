package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"
    val CURRENT_DIE_VALUE_KEY = "currentvalue"

    private var currentValue: Int = 0

    lateinit var dieTextView: TextView

    var dieSides: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_DIE_VALUE_KEY, currentValue)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState != null){
            currentValue = savedInstanceState.getInt(CURRENT_DIE_VALUE_KEY, 1)
            dieTextView.text = currentValue.toString()
        }else{
            throwDie()
        }

    }

    fun throwDie() {
        val rolledValue = (Random.nextInt(dieSides) + 1)
        dieTextView.text = rolledValue.toString()
        currentValue = rolledValue
    }

    //adding companion that executes alongside the fragments instantiation

    companion object {
        private const val DIESIDE = "sidenumber"

        fun newInstance(sides: Int): DieFragment {
            val fragment = DieFragment()
            fragment.arguments = Bundle().apply {
                putInt(DIESIDE, sides)
            }
            return fragment
        }
    }


}