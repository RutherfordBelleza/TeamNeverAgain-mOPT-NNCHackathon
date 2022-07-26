package com.neveragain.prototype.mopt.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neveragain.prototype.mopt.R
import com.neveragain.prototype.mopt.data.Child
import com.neveragain.prototype.mopt.data.ChildViewModel
import com.neveragain.prototype.mopt.databinding.FragmentListBinding

class ListFragment : Fragment(), ListClickHandler {

    private lateinit var binding: FragmentListBinding
    private lateinit var mChildViewModel: ChildViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_newSubjectFragment)
        }

        //Recyclerview
        val adapter = ChildListAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mChildViewModel = ViewModelProvider(this)[ChildViewModel::class.java]

        mChildViewModel.readAllData.observe(viewLifecycleOwner, Observer { children ->
            adapter.setData(children)
        })


        return binding.root
    }

    override fun onClick(child: Child) {
        super.onClick(child)
        val bundle = Bundle()
        bundle.putInt("infoChildId", child.id)
        bundle.putString("infoChildName", child.fullName)
        bundle.putString("infoAddress", child.address)
        bundle.putString("infoCaregiverName", child.nameOfCaregiver)
        bundle.putString("infoCaregiverContact", child.contactOfCaregiver)
        bundle.putString("infoBirthDate", child.dateOfBirth)
        bundle.putString("infoWeighingDate", child.dateOfWeighing)
        bundle.putFloat("infoHeight", child.height.toFloat())
        bundle.putFloat("infoWeight", child.weight.toFloat())
        bundle.putString("infoSex", child.sex)
        findNavController().navigate(R.id.action_listFragment_to_childInformationFragment, bundle)
    }

}