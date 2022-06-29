package com.arimukti.bmi.ui.bmi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arimukti.bmi.BmiActivity
import com.arimukti.bmi.databinding.FragmentBmiBinding
import java.text.DecimalFormat

class BmiFragment : Fragment() {

    private var _binding: FragmentBmiBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var weightCounter = 50
    private var ageCounter = 25
    private var feetValue = 5
    private var inchValue = 4
    private var countWeight: String? = null
    private var countAge: String? = null
    private var heightValue: String? = null
    private var decimalFormat: DecimalFormat? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bmiModel =
            ViewModelProvider(this).get(BmiModel::class.java)

        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.titleText
        bmiModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        initView()

        return root
    }

    private fun initView() = with(binding) {
        counterInit()
        decimalFormat = DecimalFormat(".#")
        weightCardView.setOnClickListener { }
        ageCardView.setOnClickListener { }
        weightBtnInc.setOnClickListener {
            if (weightCounter < 700) weightCounter++
            countWeight = weightCounter.toString()
            weightCounterText.text = countWeight
        }
        weightBtnDec.setOnClickListener {
            if (weightCounter > 0) weightCounter--
            countWeight = weightCounter.toString()
            weightCounterText.text = countWeight
        }
        ageBtnInc.setOnClickListener {
            if (ageCounter < 150) ageCounter++
            countAge = ageCounter.toString()
            ageCounterText.text = countAge
        }
        ageBtnDec.setOnClickListener {
            if (ageCounter > 1) ageCounter--
            countAge = ageCounter.toString()
            ageCounterText.text = countAge
        }
        feetPicker.setOnValueChangedListener { _, _, newVal ->
            feetValue = newVal
            heightValueIs()
        }
        inchPicker.setOnValueChangedListener { _, _, newVal ->
            inchValue = newVal
            heightValueIs()
        }
        calculateBtn.setOnClickListener { calculateBmi() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun counterInit() = with(binding) {
        countWeight = weightCounter.toString()
        weightCounterText.text = countWeight
        countAge = ageCounter.toString()
        ageCounterText.text = countAge
        feetPicker.minValue = 1
        feetPicker.maxValue = 8
        inchPicker.minValue = 0
        inchPicker.maxValue = 11
        feetPicker.value = 5
        inchPicker.value = 4
        heightValueIs()
    }

    private fun heightValueIs() = with(binding) {
        if (inchValue == 0) {
            heightValue = "$feetValue feet "
            heightTitleText.text = heightValue
        } else {
            heightValue = "$feetValue feet $inchValue inches"
            heightTitleText.text = heightValue
        }
    }

    private fun calculateBmi() {
        val heightInInches: Double = (feetValue * 12 + inchValue).toDouble()
        val heightInMetres = heightInInches / 39.37
        val heightInMetreSq = heightInMetres * heightInMetres
        val bmi = weightCounter / heightInMetreSq
        val bmiValue: String = decimalFormat?.format(bmi) ?: ""
        val intent = Intent(activity, BmiActivity::class.java)
        intent.putExtra("bmiVal", bmiValue)
        startActivity(intent)
    }
}