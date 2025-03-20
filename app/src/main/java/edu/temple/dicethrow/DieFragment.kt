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

    var dieSides: Int = 6

    //lateinit because it is an object
    lateinit var dieViewModel: DieViewModel
    lateinit var dieTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dieSides = it.getInt(DIESIDE, dieSides)
        }

        //fragment is made the parent with 'this' meaning the activity cannot see the ViewModel instance
        dieViewModel = ViewModelProvider(requireActivity())[DieViewModel::class.java]
        dieViewModel.updateDieSides(dieSides)
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
            dieViewModel.throwDie()
        }

    }

    companion object{
        fun newInstance(sides: Int) = DieFragment().apply{
            arguments = Bundle().apply{
                putInt(DIESIDE, sides)
            }
        }
    }


}