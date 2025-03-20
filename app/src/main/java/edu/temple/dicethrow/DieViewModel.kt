package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DieViewModel : ViewModel() {

    private var dieSides: Int = 6

    //liveData object does not change, only the objects inside of it
    //val currentRoll: MutableLiveData<Int> = MutableLiveData()

    //lazy instantiation - identifying a need for an object but only instantiating it when it is needed
    //vars automatically have get/set functions, vals only have getters
    //get/set can be delegated to another function using 'by' keyword
    //use 'lazy' to perform lazy instantiation by manipulating the get function (lazy only does get)
    //subsequent attempts to access a lazy instantiated object just returns the same reference as the first instantiation
    private val currentRoll : MutableLiveData<Int> by lazy {
        MutableLiveData()
    }

    //able to do this as a result of the substitution principle
    //MutableLiveData inherits from LiveData
    //val is exposed/returned as a liveData because it should not be changed
    fun getCurrentRoll(): LiveData<Int> {
        return currentRoll
    }

    //to change, require the use of a function
    //.value changes the value of the integer being stored inside of LiveData
    fun setCurrentRoll(roll: Int){
        currentRoll.value = roll
    }
}