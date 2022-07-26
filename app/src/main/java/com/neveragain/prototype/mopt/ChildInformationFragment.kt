package com.neveragain.prototype.mopt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.neveragain.prototype.mopt.databinding.FragmentChildInformationBinding

class ChildInformationFragment : Fragment() {

    private lateinit var binding:FragmentChildInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChildInformationBinding.inflate(inflater, container, false)
        val bundle = this.arguments

        bundle?.getString("infoChildId")

        binding.infoChildNameText.text = bundle?.getString("infoChildName")
        binding.infoAddressText.text = bundle?.getString("infoAddress")
        binding.infoCareGiverNameText.text = bundle?.getString("infoCaregiverName")
        binding.infoCareGiverContactText.text = bundle?.getString("infoCaregiverContact")

        binding.infoBirthDateText.text = bundle?.getString("infoBirthDate")
        binding.infoWeighingDateText.text = bundle?.getString("infoWeighingDate")

        binding.infoSexText.text = bundle?.getString("infoSex")
        binding.infoHeightText.text = bundle?.getFloat("infoHeight").toString()
        binding.infoWeightText.text = bundle?.getFloat("infoWeight").toString()
        binding.infoAgeInMonthsText.text = "Not Coded Yet"

        binding.infoWeightOverHeightText.text = "Not Coded Yet"
        binding.infoWeightOverAgeText.text = "Not Coded Yet"
        binding.infoHeightOverAgeText.text = "Not Coded Yet"
        return binding.root
    }

}