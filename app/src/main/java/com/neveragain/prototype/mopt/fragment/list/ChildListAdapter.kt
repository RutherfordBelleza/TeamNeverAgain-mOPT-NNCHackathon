package com.neveragain.prototype.mopt.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neveragain.prototype.mopt.R
import com.neveragain.prototype.mopt.calculations.*
import com.neveragain.prototype.mopt.data.Child
import com.neveragain.prototype.mopt.databinding.CustomRecyclerRowBinding

class ChildListAdapter(private val context: ListFragment) :
    RecyclerView.Adapter<ChildListAdapter.ChildViewHolder>() {
    private var childList = emptyList<Child>()

    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CustomRecyclerRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_recycler_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val currentItem = childList[position]
        holder.binding.childNameText.text = currentItem.fullName

        if (currentItem.sex == "M") {
            holder.binding.sexIndicatorImage.setImageResource(R.drawable.ic_baseline_male_face_24)
        } else {
            holder.binding.sexIndicatorImage.setImageResource(R.drawable.ic_baseline_female_face_24)
        }

        holder.binding.birthdayText.text = "Birthday: ${currentItem.dateOfBirth}"

        val ageInMonths = DateCalculations.getMonthsBetweenDateStrings(
            currentItem.dateOfBirth,
            currentItem.dateOfWeighing
        )

        holder.binding.ageInMonthsText.text = "Age: $ageInMonths Months"

        val wfa =
            OptCalculator.getWeightForAge(currentItem.weight, ageInMonths, currentItem.sex == "F")
        when (wfa) {
            WeightForAgeValues.WEIGHT_STATUS_SEVERLY_UNDERWEIGHT -> {
                holder.binding.weightForAge.setBackgroundResource(R.drawable.worst_nutritional_status_bg)
            }
            WeightForAgeValues.WEIGHT_STATUS_UNDERWEIGHT -> {
                holder.binding.weightForAge.setBackgroundResource(R.drawable.bad_nutritional_status_bg)
            }
            WeightForAgeValues.WEIGHT_STATUS_NORMAL -> {
                holder.binding.weightForAge.setBackgroundResource(R.drawable.normal_nutritional_status_bg)
            }
        }
        val wfaString = WeightForAgeValues.getStringEquivalent(wfa)
        holder.binding.weightForAge.text = wfaString

        val hfa =
            OptCalculator.getHeightForAge(currentItem.height, ageInMonths, currentItem.sex == "F")
        when (hfa) {
            HeightForAgeValues.HEIGHT_STATUS_SEVERLY_STUNTED -> {
                holder.binding.heightForAge.setBackgroundResource(R.drawable.worst_nutritional_status_bg)
            }
            HeightForAgeValues.HEIGHT_STATUS_STUNTED -> {
                holder.binding.heightForAge.setBackgroundResource(R.drawable.bad_nutritional_status_bg)
            }
            HeightForAgeValues.HEIGHT_STATUS_NORMAL -> {
                holder.binding.heightForAge.setBackgroundResource(R.drawable.normal_nutritional_status_bg)
            }
            HeightForAgeValues.HEIGHT_STATUS_TALL -> {
                holder.binding.heightForAge.setBackgroundResource(R.drawable.normal_nutritional_status_bg)
            }
        }
        val hfaString = HeightForAgeValues.getStringEquivalent(hfa)
        holder.binding.heightForAge.text = hfaString


        val wflh = OptCalculator.getWeightForHeight(
            currentItem.height,
            currentItem.weight,
            ageInMonths,
            currentItem.sex == "F"
        )
        when (wflh) {
            WeightForHeightValues.HEALTH_STATUS_SEVERLY_ACUTE_MALNUTRITION -> {
                holder.binding.weightForHeight.setBackgroundResource(R.drawable.worst_nutritional_status_bg)
            }
            WeightForHeightValues.HEALTH_STATUS_MODERATE_ACUTE_MALNUTRITION -> {
                holder.binding.weightForHeight.setBackgroundResource(R.drawable.bad_nutritional_status_bg)
            }
            WeightForHeightValues.HEALTH_STATUS_NORMAL -> {
                holder.binding.weightForHeight.setBackgroundResource(R.drawable.normal_nutritional_status_bg)
            }
            WeightForHeightValues.HEALTH_STATUS_OVERWEIGHT -> {
                holder.binding.weightForHeight.setBackgroundResource(R.drawable.bad_nutritional_status_bg)
            }
            WeightForHeightValues.HEALTH_STATUS_OBESE -> {
                holder.binding.weightForHeight.setBackgroundResource(R.drawable.worst_nutritional_status_bg)
            }
        }
        val wflhString = WeightForHeightValues.getStringEquivalent(wflh)
        holder.binding.weightForHeight.text = wflhString

        holder.binding.clickableLayout.setOnClickListener {
            context.onClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    fun setData(children: List<Child>) {
        this.childList = children
        notifyDataSetChanged()
    }

}