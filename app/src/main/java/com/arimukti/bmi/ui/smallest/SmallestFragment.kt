package com.arimukti.bmi.ui.smallest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arimukti.bmi.databinding.FragmentSmallestBinding

class SmallestFragment : Fragment() {

    private var _binding: FragmentSmallestBinding? = null
    private lateinit var smallestViewModel : SmallestViewModel
    private var setOfNumbers = listOf(5408, 6604, 32158 , 84984, 8774 , 34871)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val smallestViewModel =
            ViewModelProvider(this)[SmallestViewModel::class.java]

        _binding = FragmentSmallestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        val btn: Button = binding.calculateBtn
        smallestViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        smallestViewModel.titleBtn.observe(viewLifecycleOwner) {
            btn.text = it
        }
        smallestViewModel.setDisplayInput(setOfNumbers)
        btn.setOnClickListener {
            if (smallestViewModel.titleBtn.value == "reset") smallestViewModel.setDisplayInput(setOfNumbers)
            else smallestViewModel.findSmallestNumber(setOfNumbers)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}