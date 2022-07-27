package com.neveragain.prototype.mopt.fragment.update

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.neveragain.prototype.mopt.R
import com.neveragain.prototype.mopt.calculations.DateCalculations
import com.neveragain.prototype.mopt.calculations.WeightForAgeValues
import com.neveragain.prototype.mopt.data.Child
import com.neveragain.prototype.mopt.data.ChildViewModel
import com.neveragain.prototype.mopt.databinding.FragmentUpdateChildBinding


class UpdateChildFragment : Fragment() {

    private lateinit var binding: FragmentUpdateChildBinding
    private lateinit var currentChild: Child
    private lateinit var mChildViewModel: ChildViewModel

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_email).setVisible(false)
        menu.findItem(R.id.action_deleteall).setVisible(false)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateChildBinding.inflate(inflater, container, false)
        val bundle = this.arguments

        currentChild = Child(
            bundle?.getInt("infoChildId")!!,
            bundle.getString("infoChildName")!!,
            bundle.getString("infoCaregiverName")!!,
            bundle.getString("infoCaregiverContact")!!,
            bundle.getString("infoAddress")!!,
            bundle.getString("infoSex")!!,
            bundle.getString("infoIsIndigenousPreschoolChild")!!,
            bundle.getString("infoBirthDate")!!,
            bundle.getString("infoWeighingDate")!!,
            bundle.getFloat("infoWeight"),
            bundle.getFloat("infoHeight")
        )

        val dateListener = DateCalculations.createDateListener(requireContext(), null, null)
        binding.updateBirthDateField.setOnClickListener(dateListener)
        binding.updateWeighingDateField.setOnClickListener(dateListener)

        ArrayAdapter.createFromResource(
            requireContext(),
            com.neveragain.prototype.mopt.R.array.is_indigenous_values,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.updateIsIndigenousPreschoolChildField.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            com.neveragain.prototype.mopt.R.array.sex_values,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.updateSexField.adapter = adapter
        }

        binding.updateChildNameField.setText(currentChild.fullName)
        binding.updateCaregiverNameField.setText(currentChild.nameOfCaregiver)
        binding.updateCaregiverContactField.setText(currentChild.contactOfCaregiver)
        binding.updateAddressField.setText(currentChild.address)

        if (currentChild.sex == "M") {
            binding.updateSexField.setSelection(1)
        } else {
            binding.updateSexField.setSelection(0)
        }

        if (currentChild.isIndigenousPreschoolChild == "YES") {
            binding.updateIsIndigenousPreschoolChildField.setSelection(0)
        } else {
            binding.updateIsIndigenousPreschoolChildField.setSelection(1)
        }

        binding.updateBirthDateField.setText(currentChild.dateOfBirth)
        binding.updateWeighingDateField.setText(currentChild.dateOfWeighing)
        binding.updateWeightField.setText(currentChild.weight.toString())
        binding.updateHeightField.setText(currentChild.height.toString())

        binding.confirmUpdateButton.setOnClickListener {
            updateChild()
        }

        mChildViewModel = ViewModelProvider(this).get(ChildViewModel::class.java)


        return binding.root
    }

    private fun updateChild() {
        if (checkInput()) {
            val childNameString = binding.updateChildNameField.text.toString()
            val caregiverNameString = binding.updateCaregiverNameField.text.toString()
            val caregiverContactString = binding.updateCaregiverContactField.text.toString()
            val addressString = binding.updateAddressField.text.toString()
            val sexString = binding.updateSexField.selectedItem.toString()
            val isIndigenousPreschoolChildString =
                binding.updateIsIndigenousPreschoolChildField.selectedItem.toString()
            val birthDateString = binding.updateBirthDateField.text.toString()
            val weighingDateString = binding.updateWeighingDateField.text.toString()
            val weightString = binding.updateWeightField.text.toString()
            val heightString = binding.updateHeightField.text.toString()

            val newChild = Child(
                currentChild.id,
                childNameString,
                caregiverNameString,
                caregiverContactString,
                addressString,
                sexString,
                isIndigenousPreschoolChildString,
                birthDateString,
                weighingDateString,
                weightString.toFloat(),
                heightString.toFloat()
            )

            val bundle = Bundle()
            bundle.putInt("infoChildId", newChild.id)
            bundle.putString("infoChildName", newChild.fullName)
            bundle.putString("infoAddress", newChild.address)
            bundle.putString("infoCaregiverName", newChild.nameOfCaregiver)
            bundle.putString("infoCaregiverContact", newChild.contactOfCaregiver)
            bundle.putString("infoBirthDate", newChild.dateOfBirth)
            bundle.putString("infoWeighingDate", newChild.dateOfWeighing)
            bundle.putFloat("infoWeight", newChild.weight.toFloat())
            bundle.putFloat("infoHeight", newChild.height.toFloat())
            bundle.putString("infoSex", newChild.sex)
            bundle.putString("infoIsIndigenousPreschoolChild", newChild.isIndigenousPreschoolChild)
            Log.i("RUTHER", "CHILD ID = " + newChild.id)
            mChildViewModel.updateChild(newChild)

            findNavController().navigate(
                R.id.action_updateChildFragment2_to_childInformationFragment,
                bundle
            )
        }
    }

    private fun checkInput(): Boolean {
        return if (
            binding.updateChildNameField.text.isNotBlank() &&
            binding.updateAddressField.text.isNotBlank() &&
            binding.updateCaregiverNameField.text.isNotBlank() &&
            binding.updateCaregiverContactField.text.isNotBlank() &&
            binding.updateBirthDateField.text.isNotBlank() &&
            binding.updateWeightField.text.isNotBlank() &&
            binding.updateHeightField.text.isNotBlank()
        ) {
            //check age
            val ageInMonths = DateCalculations.getMonthsBetweenDateStrings(
                binding.updateBirthDateField.text.toString(),
                binding.updateWeighingDateField.text.toString()
            )
            if (ageInMonths < 0 || ageInMonths > 71) {
                Toast.makeText(
                    requireContext(),
                    "Invalid Birthdate and Weighing Date!",
                    Toast.LENGTH_LONG
                ).show()
                return false
            }

            if (binding.updateSexField.selectedItem.toString() == "F") {
                //check input weight
                if (binding.updateWeightField.text.toString()
                        .toFloat() > WeightForAgeValues.femaleWeightForAge[ageInMonths][4]
                ) {
                    Toast.makeText(requireContext(), "Weight is too high", Toast.LENGTH_LONG).show()
                    return false
                }
                if (ageInMonths < 24) {
                    if (binding.updateHeightField.text.toString()
                            .toFloat() < 45 || binding.updateHeightField.text.toString()
                            .toFloat() > 110
                    ) {
                        Toast.makeText(
                            requireContext(),
                            "Height is outside the range",
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }
                } else {
                    if (binding.updateHeightField.text.toString()
                            .toFloat() < 65 || binding.updateHeightField.text.toString()
                            .toFloat() > 110
                    ) {
                        Toast.makeText(
                            requireContext(),
                            "Height is outside the range",
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }
                }

            } else {
                //check input weight
                if (binding.updateWeightField.text.toString()
                        .toFloat() > WeightForAgeValues.maleWeightForAge[ageInMonths][4]
                ) {
                    Toast.makeText(requireContext(), "Weight is too high", Toast.LENGTH_LONG).show()
                    return false
                }

                if (ageInMonths < 24) {
                    if (binding.updateHeightField.text.toString().toFloat() < 45) {
                        Toast.makeText(
                            requireContext(),
                            "Height is outside the range",
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }
                } else {
                    if (binding.updateHeightField.text.toString().toFloat() < 65) {
                        Toast.makeText(
                            requireContext(),
                            "Height is outside the range",
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }
                }
            }
            Toast.makeText(requireContext(), "Successfully Updated!", Toast.LENGTH_LONG).show()
            true
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_LONG).show()
            false
        }
    }

}