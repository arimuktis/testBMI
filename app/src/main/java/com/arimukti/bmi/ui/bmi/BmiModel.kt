package com.arimukti.bmi.ui.bmi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BmiModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "BMI Calculator"
    }
    val text: LiveData<String> = _text
}