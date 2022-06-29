package com.arimukti.bmi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arimukti.bmi.databinding.ActivityBmiBinding

class BmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding

    private var category: String? = null
    private var bmiValOutput: String? = null
    private var bmiTipsArray: Array<String> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bmiTipsArray = resources.getStringArray(R.array.tips_array)
        bmiValOutput = intent.getStringExtra("bmiVal")
        binding.bmiValue.text = bmiValOutput
        findCategory()
        categoryTips()
        binding.calculateAgainBtn.setOnClickListener { onBackPressed() }
    }

    private fun categoryTips() = with(binding) {
        val result = bmiValOutput!!.toDouble()
        when {
            result < 15 -> bmiTips.text = bmiTipsArray[0]
            result in 15.0..16.0 -> bmiTips.text = bmiTipsArray[0]
            result in 16.0..18.5 -> bmiTips.text = bmiTipsArray[1]
            result in 18.5..25.0 -> bmiTips.text = bmiTipsArray[2]
            result in 25.0..30.0 -> bmiTips.text = bmiTipsArray[3]
            result in 30.0..35.0 -> bmiTips.text = bmiTipsArray[4]
            result in 35.0..50.0 -> bmiTips.text = bmiTipsArray[4]
            else -> bmiTips.text = bmiTipsArray[4]
        }
    }

    private fun findCategory() = with(binding) {
        val result = bmiValOutput!!.toDouble()
        when {
            result < 15 -> {
                category = "Very Severely Underweight"
                bmiCategory.text = category
            }
            result in 15.0..16.0 -> {
                category = "Severely Underweight"
                bmiCategory.text = category
            }
            result in 16.0..18.5 -> {
                category = "Underweight"
                bmiCategory.text = category
            }
            result in 18.5..25.0 -> {
                category = "Normal (Healthy weight)"
                bmiCategory.text = category
            }
            result in 25.0..30.0 -> {
                category = "Overweight"
                bmiCategory.text = category
            }
            result in 30.0..35.0 -> {
                category = "Moderately Obese"
                bmiCategory.text = category
            }
            result in 35.0..50.0 -> {
                bmiCategory.text = category
                category = "Severely Obese"
            }
            else -> category = "Very Severely Obese"
        }
        bmiCategory.text = category
    }
}