package com.neveragain.prototype.mopt.calculations

import kotlin.math.round

class OptCalculator {
    companion object {

        fun getHeightForAge(height: Double, ageInMonths: Int, isFemale: Boolean): Int {
            if (ageInMonths > 72) {
                return HeightForAgeValues.HEIGHT_STATUS_TOO_OLD
            }
            if (isFemale) {
                return if (height <= HeightForAgeValues.femaleHeightForAge[ageInMonths][0]) {
                    HeightForAgeValues.HEIGHT_STATUS_SEVERLY_STUNTED
                } else if (height <= HeightForAgeValues.femaleHeightForAge[ageInMonths][2]) {
                    HeightForAgeValues.HEIGHT_STATUS_STUNTED
                } else if (height < HeightForAgeValues.femaleHeightForAge[ageInMonths][5]) {
                    HeightForAgeValues.HEIGHT_STATUS_NORMAL
                } else {
                    HeightForAgeValues.HEIGHT_STATUS_TALL
                }
            } else {
                return if (height <= HeightForAgeValues.maleHeightForAge[ageInMonths][0]) {
                    HeightForAgeValues.HEIGHT_STATUS_SEVERLY_STUNTED
                } else if (height <= HeightForAgeValues.maleHeightForAge[ageInMonths][2]) {
                    HeightForAgeValues.HEIGHT_STATUS_STUNTED
                } else if (height < HeightForAgeValues.maleHeightForAge[ageInMonths][5]) {
                    HeightForAgeValues.HEIGHT_STATUS_NORMAL
                } else {
                    HeightForAgeValues.HEIGHT_STATUS_TALL
                }
            }
        }

        fun getWeightForAge(weight: Double, ageInMonths: Int, isFemale: Boolean): Int {
            if (ageInMonths > 72) {
                return WeightForAgeValues.WEIGHT_STATUS_TOO_OLD
            }
            if (isFemale) {
                return if (weight <= WeightForAgeValues.femaleWeightForAge[ageInMonths][0]) {
                    WeightForAgeValues.WEIGHT_STATUS_SEVERLY_UNDERWEIGHT
                } else if (weight <= WeightForAgeValues.femaleWeightForAge[ageInMonths][2]) {
                    WeightForAgeValues.WEIGHT_STATUS_UNDERWEIGHT
                } else {
                    WeightForAgeValues.WEIGHT_STATUS_NORMAL
                }
            } else {
                return if (weight <= WeightForAgeValues.maleWeightForAge[ageInMonths][0]) {
                    WeightForAgeValues.WEIGHT_STATUS_SEVERLY_UNDERWEIGHT
                } else if (weight <= WeightForAgeValues.maleWeightForAge[ageInMonths][2]) {
                    WeightForAgeValues.WEIGHT_STATUS_UNDERWEIGHT
                } else {
                    WeightForAgeValues.WEIGHT_STATUS_NORMAL
                }
            }
        }

        fun getWeightForHeight(
            height: Double,
            weight: Double,
            ageInMonths: Int,
            isFemale: Boolean
        ): Int {
            val heightIndex = (round(height * 2) - 90).toInt()
            if (ageInMonths > 72) {
                return WeightForHeightValues.HEALTH_STATUS_TOO_OLD
            }
            if (ageInMonths < 24) {
                if (isFemale) {
                    return if (weight <= WeightForHeightValues.femaleWeightForHeight_0to24[heightIndex][0]) {
                        WeightForHeightValues.HEALTH_STATUS_SEVERLY_ACUTE_MALNUTRITION
                    } else if (weight <= WeightForHeightValues.femaleWeightForHeight_0to24[heightIndex][2]) {
                        WeightForHeightValues.HEALTH_STATUS_MODERATE_ACUTE_MALNUTRITION
                    } else if (weight <= WeightForHeightValues.femaleWeightForHeight_0to24[heightIndex][4]) {
                        WeightForHeightValues.HEALTH_STATUS_NORMAL
                    } else if (weight <= WeightForHeightValues.femaleWeightForHeight_0to24[heightIndex][6]) {
                        WeightForHeightValues.HEALTH_STATUS_OVERWEIGHT
                    } else {
                        WeightForHeightValues.HEALTH_STATUS_OBESE
                    }
                } else {
                    return if (weight <= WeightForHeightValues.maleWeightForHeight_0to24[heightIndex][0]) {
                        WeightForHeightValues.HEALTH_STATUS_SEVERLY_ACUTE_MALNUTRITION
                    } else if (weight <= WeightForHeightValues.maleWeightForHeight_0to24[heightIndex][2]) {
                        WeightForHeightValues.HEALTH_STATUS_MODERATE_ACUTE_MALNUTRITION
                    } else if (weight <= WeightForHeightValues.maleWeightForHeight_0to24[heightIndex][4]) {
                        WeightForHeightValues.HEALTH_STATUS_NORMAL
                    } else if (weight <= WeightForHeightValues.maleWeightForHeight_0to24[heightIndex][6]) {
                        WeightForHeightValues.HEALTH_STATUS_OVERWEIGHT
                    } else {
                        WeightForHeightValues.HEALTH_STATUS_OBESE
                    }
                }
            } else {
                if (isFemale) {
                    return if (weight <= WeightForHeightValues.femaleWeightForHeightOver24[heightIndex][0]) {
                        WeightForHeightValues.HEALTH_STATUS_SEVERLY_ACUTE_MALNUTRITION
                    } else if (weight <= WeightForHeightValues.femaleWeightForHeightOver24[heightIndex][2]) {
                        WeightForHeightValues.HEALTH_STATUS_MODERATE_ACUTE_MALNUTRITION
                    } else if (weight <= WeightForHeightValues.femaleWeightForHeightOver24[heightIndex][4]) {
                        WeightForHeightValues.HEALTH_STATUS_NORMAL
                    } else if (weight <= WeightForHeightValues.femaleWeightForHeightOver24[heightIndex][6]) {
                        WeightForHeightValues.HEALTH_STATUS_OVERWEIGHT
                    } else {
                        WeightForHeightValues.HEALTH_STATUS_OBESE
                    }
                } else {
                    return if (weight <= WeightForHeightValues.maleWeightForHeightOver24[heightIndex][0]) {
                        WeightForHeightValues.HEALTH_STATUS_SEVERLY_ACUTE_MALNUTRITION
                    } else if (weight <= WeightForHeightValues.maleWeightForHeightOver24[heightIndex][2]) {
                        WeightForHeightValues.HEALTH_STATUS_MODERATE_ACUTE_MALNUTRITION
                    } else if (weight <= WeightForHeightValues.maleWeightForHeightOver24[heightIndex][4]) {
                        WeightForHeightValues.HEALTH_STATUS_NORMAL
                    } else if (weight <= WeightForHeightValues.maleWeightForHeightOver24[heightIndex][6]) {
                        WeightForHeightValues.HEALTH_STATUS_OVERWEIGHT
                    } else {
                        WeightForHeightValues.HEALTH_STATUS_OBESE
                    }
                }
            }
        }

        fun validateHeightForAge(height: Double, ageInMonths: Int, isFemale: Boolean): Boolean {
            if (isFemale) {
                if (ageInMonths >= HeightForAgeValues.femaleHeightForAge.size || ageInMonths < 0) {
                    return false
                } else {
                    return true
                }
            } else {
                if (ageInMonths >= HeightForAgeValues.maleHeightForAge.size || ageInMonths < 0) {
                    return false
                } else {
                    return true
                }
            }
        }

        fun validateWeightForAge(weight: Double, ageInMonths: Int, isFemale: Boolean): Boolean {
            if (isFemale) {
                if (ageInMonths >= WeightForAgeValues.femaleWeightForAge.size || ageInMonths < 0) {
                    return false
                } else {
                    return true
                }
            } else {
                if (ageInMonths >= WeightForAgeValues.maleWeightForAge.size || ageInMonths < 0) {
                    return false
                } else {
                    return true
                }
            }
        }

        fun validateWeightForHeight(
            weight: Double,
            height: Double,
            ageInMonths: Int,
            isFemale: Boolean
        ): Boolean {
            if (isFemale) {
                if (ageInMonths > 24) {
                    if (height > WeightForHeightValues.femaleMaximumHeightOver24 || height < WeightForHeightValues.femaleMinimumHeightOver24) {
                        return false
                    } else {
                        return true
                    }
                } else {
                    if (height > WeightForHeightValues.femaleMaximumHeight_0to24 || height < WeightForHeightValues.femaleMinimumHeight_0to24) {
                        return false
                    } else {
                        return true
                    }
                }
            } else {
                if (ageInMonths > 24) {
                    if (height > WeightForHeightValues.maleMaximumHeightOver24 || height < WeightForHeightValues.maleMinimumHeightOver24) {
                        return false
                    } else {
                        return true
                    }
                } else {
                    if (height > WeightForHeightValues.maleMaximumHeight_0to24 || height < WeightForHeightValues.maleMinimumHeight_0to24) {
                        return false
                    } else {
                        return true
                    }
                }
            }
        }

    }
}