package com.arimukti.bmi.ui.smallest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SmallestViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val _titleBtn = MutableLiveData<String>().apply {
        value = "Calculate"
    }
    val titleBtn: LiveData<String> = _titleBtn

    fun findSmallestNumber(numbers: List<Int>) {
        _text.postValue(numbers.min().toString())
        _titleBtn.postValue("reset")
    }

    fun setDisplayInput(numbers: List<Int>) {
        _text.postValue("Set of numbers:\n$numbers")
        _titleBtn.postValue("calculate")
    }
}