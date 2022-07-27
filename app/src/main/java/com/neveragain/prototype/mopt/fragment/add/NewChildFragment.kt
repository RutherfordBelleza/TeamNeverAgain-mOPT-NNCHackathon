package com.neveragain.prototype.mopt.fragment.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.neveragain.prototype.mopt.R
import com.neveragain.prototype.mopt.calculations.DateCalculations
import com.neveragain.prototype.mopt.calculations.HeightForAgeValues
import com.neveragain.prototype.mopt.calculations.WeightForAgeValues
import com.neveragain.prototype.mopt.calculations.WeightForHeightValues
import com.neveragain.prototype.mopt.data.Child
import com.neveragain.prototype.mopt.data.ChildViewModel
import com.neveragain.prototype.mopt.databinding.FragmentNewChildBinding
import java.util.*

class NewChildFragment : Fragment() {
    private lateinit var binding: FragmentNewChildBinding
    private lateinit var mChildViewModel: ChildViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewChildBinding.inflate(inflater, container, false)
        binding.addButton.setOnClickListener {
            insertToDatabase()
        }

        val dateListener = DateCalculations.createDateListener(requireContext(), null, null)
        binding.birthDateField.setOnClickListener(dateListener)
        binding.weighingDateField.setOnClickListener(dateListener)
        val c = Calendar.getInstance()
        val now = c.time
        binding.weighingDateField.setText(DateCalculations.dateToFormDateString(now))

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.is_indigenous_values,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.isIndigenousPreschoolChildField.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sex_values,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sexField.adapter = adapter
        }

        mChildViewModel = ViewModelProvider(this).get(ChildViewModel::class.java)


        return binding.root
    }

    private fun insertToDatabase() {
        if (checkInput()) {
            val childNameString = binding.childNameField.text.toString()
            val caregiverNameString = binding.caregiverNameField.text.toString()
            val caregiverContactString = binding.caregiverContactField.text.toString()
            val addressString = binding.addressField.text.toString()
            val sexString = binding.sexField.selectedItem.toString()
            val isIndigenousPreschoolChildString =
                binding.isIndigenousPreschoolChildField.selectedItem.toString()
            val birthDateString = binding.birthDateField.text.toString()
            val weighingDateString = binding.weighingDateField.text.toString()
            val weightString = binding.weightField.text.toString()
            val heightString = binding.heightField.text.toString()

            val newChild = Child(
                0,
                childNameString,
                caregiverNameString,
                caregiverContactString,
                addressString,
                sexString,
                isIndigenousPreschoolChildString,
                birthDateString,
                weighingDateString,
                weightString.toDouble(),
                heightString.toDouble()
            )
            mChildViewModel.addChild(newChild)
            findNavController().navigate(R.id.action_newSubjectFragment_to_listFragment)
        }
    }

    private fun checkInput(): Boolean {
        if (
            binding.childNameField.text.isNotBlank() &&
            binding.addressField.text.isNotBlank() &&
            binding.caregiverNameField.text.isNotBlank() &&
            binding.caregiverContactField.text.isNotBlank() &&
            binding.birthDateField.text.isNotBlank() &&
            binding.weightField.text.isNotBlank() &&
            binding.heightField.text.isNotBlank()
        ) {
            //check age
            val ageInMonths = DateCalculations.getMonthsBetweenDateStrings(
                binding.birthDateField.text.toString(),
                binding.weighingDateField.text.toString()
            )
            if (ageInMonths < 0 || ageInMonths > 71) {
                Toast.makeText(
                    requireContext(),
                    "Invalid Birthdate and Weighing Date!",
                    Toast.LENGTH_LONG
                ).show()
                return false
            }

            if (binding.sexField.selectedItem.toString() == "F"){
                //check input weight
                if (binding.weightField.text.toString().toDouble() > WeightForAgeValues.femaleWeightForAge[ageInMonths][4]){
                    Toast.makeText(requireContext(), "Weight is too high", Toast.LENGTH_LONG).show()
                    return false
                }

            } else {
                //check input weight
                if (binding.weightField.text.toString().toDouble() > WeightForAgeValues.maleWeightForAge[ageInMonths][4]){
                    Toast.makeText(requireContext(), "Weight is too high", Toast.LENGTH_LONG).show()
                    return false
                }
            }

            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_LONG).show()
            return true
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_LONG).show()
            return false
        }
    }

}