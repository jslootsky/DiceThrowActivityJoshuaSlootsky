package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"
    val CURRENT_DIE_VALUE_KEY = "currentvalue"

    private var currentValue: Int = 0

    lateinit var dieTextView: TextView

    var dieSides: Int = 6

    //lateinit because it is an object
    lateinit var dieViewModel: DieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }

        //fragment is made the parent with 'this' meaning the activity cannot see the ViewModel instance
        dieViewModel = ViewModelProvider(this)[DieViewModel::class.java]
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //(lifeCycle, observer)
        //
        dieViewModel.getCurrentRoll().observe(viewLifecycleOwner){
            dieTextView.text = it.toString()
        }

        if(savedInstanceState == null){
            throwDie()
        }

    }

    fun throwDie() {
        dieViewModel.setCurrentRoll(Random.nextInt(dieSides) + 1)

//        val rolledValue = (Random.nextInt(dieSides) + 1)
//        dieTextView.text = rolledValue.toString()
//        currentValue = rolledValue
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