package com.neveragain.prototype.mopt.fragment.info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.neveragain.prototype.mopt.R
import com.neveragain.prototype.mopt.data.Child
import com.neveragain.prototype.mopt.databinding.FragmentChildInformationBinding

class ChildInformationFragment : Fragment() {

    private lateinit var binding: FragmentChildInformationBinding
    private lateinit var currentChild: Child

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChildInformationBinding.inflate(inflater, container, false)
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
            bundle.getFloat("infoHeight").toDouble(),
            bundle.getFloat("infoWeight").toDouble()
        )

        binding.infoChildNameText.text = currentChild.fullName
        binding.infoAddressText.text = currentChild.address
        binding.infoCareGiverNameText.text = currentChild.nameOfCaregiver
        binding.infoCareGiverContactText.text = currentChild.contactOfCaregiver

        binding.infoBirthDateText.text = currentChild.dateOfBirth
        binding.infoWeighingDateText.text = currentChild.dateOfWeighing

        binding.infoIsIndigenousPreschoolChildText.text = currentChild.isIndigenousPreschoolChild
        binding.infoSexText.text = currentChild.sex
        binding.infoHeightText.text = currentChild.height.toString()
        binding.infoWeightText.text = currentChild.weight.toString()
        binding.infoAgeInMonthsText.text = "Not Coded Yet"

        binding.infoWeightOverHeightText.text = "Not Coded Yet"
        binding.infoWeightOverAgeText.text = "Not Coded Yet"
        binding.infoHeightOverAgeText.text = "Not Coded Yet"

        binding.editChildButton.setOnClickListener {
            editChild()
        }

        return binding.root
    }

    private fun editChild() {
        val bundle = Bundle()
        bundle.putInt("infoChildId", currentChild.id)
        bundle.putString("infoChildName", currentChild.fullName)
        bundle.putString("infoAddress", currentChild.address)
        bundle.putString("infoCaregiverName", currentChild.nameOfCaregiver)
        bundle.putString("infoCaregiverContact", currentChild.contactOfCaregiver)
        bundle.putString("infoBirthDate", currentChild.dateOfBirth)
        bundle.putString("infoWeighingDate", currentChild.dateOfWeighing)
        bundle.putFloat("infoHeight", currentChild.height.toFloat())
        bundle.putFloat("infoWeight", currentChild.weight.toFloat())
        bundle.putString("infoSex", currentChild.sex)
        bundle.putString("infoIsIndigenousPreschoolChild", currentChild.isIndigenousPreschoolChild)
        findNavController().navigate(R.id.action_childInformationFragment_to_updateChildFragment2, bundle)
    }

}