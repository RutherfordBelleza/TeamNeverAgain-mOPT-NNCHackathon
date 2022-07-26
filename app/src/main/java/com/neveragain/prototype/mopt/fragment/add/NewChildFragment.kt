package com.neveragain.prototype.mopt.fragment.add

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.neveragain.prototype.mopt.R
import com.neveragain.prototype.mopt.data.Child
import com.neveragain.prototype.mopt.data.ChildViewModel
import com.neveragain.prototype.mopt.databinding.FragmentNewChildBinding
import java.text.SimpleDateFormat
import java.util.*

class NewChildFragment : Fragment() {

    private val formDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
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

        val dateListener = createDateListener(requireContext(), null, null)
        binding.birthDateField.setOnClickListener(dateListener)
        binding.weighingDateField.setOnClickListener(dateListener)
        val c = Calendar.getInstance()
        val now = c.time
        binding.weighingDateField.setText(dateToFormDateString(now))

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.is_indigenous_values,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.isIndigenousField.adapter = adapter
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
            val indigenousString = binding.isIndigenousField.selectedItem.toString()
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
                indigenousString,
                birthDateString,
                weighingDateString,
                weightString.toDouble(),
                heightString.toDouble()
            )
            mChildViewModel.addChild(newChild)
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_newSubjectFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkInput(): Boolean {
        return true
    }

    private fun createDateListener(
        context: Context,
        maxDate: Date?,
        minDate: Date?
    ): View.OnClickListener {
        //General DateDialog Listener
        return View.OnClickListener { view ->
            val newCalendar = Calendar.getInstance()
            val currentView: EditText = view as EditText
            val viewText = view.text.toString()
            if (viewText.isNotBlank()) {
                val date = formDateStringToDate(viewText)
                newCalendar.time = date
            }
            val tempDay = newCalendar.get(Calendar.DAY_OF_MONTH)
            val tempMonth = newCalendar.get(Calendar.MONTH)
            val tempYear = newCalendar.get(Calendar.YEAR)
            val datePicker = DatePickerDialog(context, { _, year, monthOfYear, dayOfMonth ->

                newCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                newCalendar.set(Calendar.MONTH, monthOfYear)

                newCalendar.set(Calendar.YEAR, year)

                currentView.setText(dateToFormDateString(newCalendar.time))

            }, tempYear, tempMonth, tempDay)
            if (maxDate != null) datePicker.datePicker.maxDate = maxDate.time
            if (minDate != null) datePicker.datePicker.minDate = minDate.time
            datePicker.show()
        }
    }

    private fun formDateStringToDate(s: String): Date {
        // expects dd/MM/yyyy
        return formDateFormat.parse(s)!!
    }

    private fun dateToFormDateString(d: Date): String {
        // returns dd/MM/yyyy
        return formDateFormat.format(d)
    }

}