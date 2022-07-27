package com.neveragain.prototype.mopt.calculations

class WeightForAgeValues {
    companion object {

        val WEIGHT_STATUS_SEVERLY_UNDERWEIGHT = 200
        val WEIGHT_STATUS_UNDERWEIGHT = 201
        val WEIGHT_STATUS_NORMAL = 202
        val WEIGHT_STATUS_TOO_OLD = 103

        fun getStringEquivalent(id: Int): String {
            return when (id) {
                WEIGHT_STATUS_SEVERLY_UNDERWEIGHT -> "SUW"
                WEIGHT_STATUS_UNDERWEIGHT -> "UW"
                WEIGHT_STATUS_NORMAL -> "N"
                else -> ""
            }
        }

        fun getFullStringEquivalent(id: Int): String {
            return when (id) {
                WEIGHT_STATUS_SEVERLY_UNDERWEIGHT -> "Severly Underweight"
                WEIGHT_STATUS_UNDERWEIGHT -> "Underweight"
                WEIGHT_STATUS_NORMAL -> "Normal"
                else -> ""
            }
        }

        val maleWeightForAge = arrayOf(
            floatArrayOf(2.1F, 2.2F, 2.4F, 2.5F, 4.4F),
            floatArrayOf(2.9F, 3.0F, 3.3F, 3.4F, 5.8F),
            floatArrayOf(3.8F, 3.9F, 4.2F, 4.3F, 7.1F),
            floatArrayOf(4.4F, 4.5F, 4.9F, 5.0F, 8.0F),
            floatArrayOf(4.9F, 5.0F, 5.5F, 5.6F, 8.7F),
            floatArrayOf(5.3F, 5.4F, 5.9F, 6.0F, 9.3F),
            floatArrayOf(5.7F, 5.8F, 6.3F, 6.4F, 9.8F),
            floatArrayOf(5.9F, 6.0F, 6.6F, 6.7F, 10.3F),
            floatArrayOf(6.2F, 6.3F, 6.8F, 6.9F, 10.7F),
            floatArrayOf(6.4F, 6.5F, 7.0F, 7.1F, 11.0F),
            floatArrayOf(6.6F, 6.7F, 7.3F, 7.4F, 11.4F),
            floatArrayOf(6.8F, 6.9F, 7.5F, 7.6F, 11.7F),
            floatArrayOf(6.9F, 7.0F, 7.6F, 7.7F, 12.0F),
            floatArrayOf(7.1F, 7.2F, 7.8F, 7.9F, 12.3F),
            floatArrayOf(7.2F, 7.3F, 8.0F, 8.1F, 12.6F),
            floatArrayOf(7.4F, 7.5F, 8.2F, 8.3F, 12.8F),
            floatArrayOf(7.5F, 7.6F, 8.3F, 8.4F, 13.1F),
            floatArrayOf(7.7F, 7.8F, 8.5F, 8.6F, 13.4F),
            floatArrayOf(7.8F, 7.9F, 8.7F, 8.8F, 13.7F),
            floatArrayOf(8.0F, 8.1F, 8.8F, 8.9F, 13.9F),
            floatArrayOf(8.1F, 8.2F, 9.0F, 9.1F, 14.2F),
            floatArrayOf(8.2F, 8.3F, 9.1F, 9.2F, 14.5F),
            floatArrayOf(8.4F, 8.5F, 9.3F, 9.4F, 14.7F),
            floatArrayOf(8.5F, 8.6F, 9.4F, 9.5F, 15.0F),
            floatArrayOf(8.6F, 8.7F, 9.6F, 9.7F, 15.3F),
            floatArrayOf(8.8F, 8.9F, 9.7F, 9.8F, 15.5F),
            floatArrayOf(8.9F, 9.0F, 9.9F, 10.0F, 15.8F),
            floatArrayOf(9.0F, 9.1F, 10.0F, 10.1F, 16.1F),
            floatArrayOf(9.1F, 9.2F, 10.1F, 10.2F, 16.3F),
            floatArrayOf(9.2F, 9.3F, 10.3F, 10.4F, 16.6F),
            floatArrayOf(9.4F, 9.5F, 10.4F, 10.5F, 16.9F),
            floatArrayOf(9.5F, 9.6F, 10.6F, 10.7F, 17.1F),
            floatArrayOf(9.6F, 9.7F, 10.7F, 10.8F, 17.4F),
            floatArrayOf(9.7F, 9.8F, 10.8F, 10.9F, 17.6F),
            floatArrayOf(9.8F, 9.9F, 10.9F, 11.0F, 17.8F),
            floatArrayOf(9.9F, 10.0F, 11.1F, 11.2F, 18.1F),
            floatArrayOf(10.0F, 10.1F, 11.2F, 11.3F, 18.3F),
            floatArrayOf(10.1F, 10.2F, 11.3F, 11.4F, 18.6F),
            floatArrayOf(10.2F, 10.3F, 11.4F, 11.5F, 18.8F),
            floatArrayOf(10.3F, 10.4F, 11.5F, 11.6F, 19.0F),
            floatArrayOf(10.4F, 10.5F, 11.7F, 11.8F, 19.3F),
            floatArrayOf(10.5F, 10.6F, 11.8F, 11.9F, 19.5F),
            floatArrayOf(10.6F, 10.7F, 11.9F, 12.0F, 19.7F),
            floatArrayOf(10.7F, 10.8F, 12.0F, 12.1F, 20.0F),
            floatArrayOf(10.8F, 10.9F, 12.1F, 12.2F, 20.2F),
            floatArrayOf(10.9F, 11.0F, 12.3F, 12.4F, 20.5F),
            floatArrayOf(11.0F, 11.1F, 12.4F, 12.5F, 20.7F),
            floatArrayOf(11.1F, 11.2F, 12.5F, 12.6F, 20.9F),
            floatArrayOf(11.2F, 11.3F, 12.6F, 12.7F, 21.2F),
            floatArrayOf(11.3F, 11.4F, 12.7F, 12.8F, 21.4F),
            floatArrayOf(11.4F, 11.5F, 12.8F, 12.9F, 21.7F),
            floatArrayOf(11.5F, 11.6F, 13.0F, 13.1F, 21.9F),
            floatArrayOf(11.6F, 11.7F, 13.1F, 13.2F, 22.2F),
            floatArrayOf(11.7F, 11.8F, 13.2F, 13.3F, 22.4F),
            floatArrayOf(11.8F, 11.9F, 13.3F, 13.4F, 22.7F),
            floatArrayOf(11.9F, 12.0F, 13.4F, 13.5F, 22.9F),
            floatArrayOf(12.0F, 12.1F, 13.5F, 13.6F, 23.2F),
            floatArrayOf(12.1F, 12.2F, 13.6F, 13.7F, 23.4F),
            floatArrayOf(12.2F, 12.3F, 13.7F, 13.8F, 23.7F),
            floatArrayOf(12.3F, 12.4F, 13.9F, 14.0F, 23.9F),
            floatArrayOf(12.4F, 12.5F, 14.0F, 14.1F, 24.2F),
            floatArrayOf(12.7F, 12.8F, 14.3F, 14.4F, 24.3F),
            floatArrayOf(12.8F, 12.9F, 14.4F, 14.5F, 24.4F),
            floatArrayOf(13.0F, 13.1F, 14.5F, 14.6F, 24.7F),
            floatArrayOf(13.1F, 13.2F, 14.7F, 14.8F, 24.9F),
            floatArrayOf(13.2F, 13.3F, 14.8F, 14.9F, 25.2F),
            floatArrayOf(13.3F, 13.4F, 14.9F, 15.0F, 25.5F),
            floatArrayOf(13.4F, 13.5F, 15.1F, 15.2F, 25.7F),
            floatArrayOf(13.6F, 13.7F, 15.2F, 15.3F, 26.0F),
            floatArrayOf(13.7F, 13.8F, 15.3F, 15.4F, 26.3F),
            floatArrayOf(13.8F, 13.9F, 15.5F, 15.6F, 26.6F),
            floatArrayOf(13.9F, 14.0F, 15.6F, 15.7F, 26.8F)
        )

        val femaleWeightForAge = arrayOf(
            floatArrayOf(2.0F, 2.1F, 2.3F, 2.4F, 4.2F),
            floatArrayOf(2.7F, 2.8F, 3.1F, 3.2F, 5.5F),
            floatArrayOf(3.4F, 3.5F, 3.8F, 3.9F, 6.6F),
            floatArrayOf(4.0F, 4.1F, 4.4F, 4.5F, 7.5F),
            floatArrayOf(4.4F, 4.5F, 4.9F, 5.0F, 8.2F),
            floatArrayOf(4.8F, 4.9F, 5.3F, 5.4F, 8.8F),
            floatArrayOf(5.1F, 5.2F, 5.6F, 5.7F, 9.3F),
            floatArrayOf(5.3F, 5.4F, 5.9F, 6.0F, 9.8F),
            floatArrayOf(5.6F, 5.7F, 6.2F, 6.3F, 10.2F),
            floatArrayOf(5.8F, 5.9F, 6.4F, 6.5F, 10.5F),
            floatArrayOf(5.9F, 6.0F, 6.6F, 6.7F, 10.9F),
            floatArrayOf(6.1F, 6.2F, 6.8F, 6.9F, 11.2F),
            floatArrayOf(6.3F, 6.4F, 6.9F, 7.0F, 11.5F),
            floatArrayOf(6.4F, 6.5F, 7.1F, 7.2F, 11.8F),
            floatArrayOf(6.6F, 6.7F, 7.3F, 7.4F, 12.1F),
            floatArrayOf(6.7F, 6.8F, 7.5F, 7.6F, 12.4F),
            floatArrayOf(6.9F, 7.0F, 7.6F, 7.7F, 12.6F),
            floatArrayOf(7.0F, 7.1F, 7.8F, 7.9F, 12.9F),
            floatArrayOf(7.2F, 7.3F, 8.0F, 8.1F, 13.2F),
            floatArrayOf(7.3F, 7.4F, 8.1F, 8.2F, 13.5F),
            floatArrayOf(7.5F, 7.6F, 8.3F, 8.4F, 13.7F),
            floatArrayOf(7.6F, 7.7F, 8.5F, 8.6F, 14.0F),
            floatArrayOf(7.8F, 7.9F, 8.6F, 8.7F, 14.3F),
            floatArrayOf(7.9F, 8.0F, 8.8F, 8.9F, 14.6F),
            floatArrayOf(8.1F, 8.2F, 8.9F, 9.0F, 14.8F),
            floatArrayOf(8.2F, 8.3F, 9.1F, 9.2F, 15.1F),
            floatArrayOf(8.4F, 8.5F, 9.3F, 9.4F, 15.4F),
            floatArrayOf(8.5F, 8.6F, 9.4F, 9.5F, 15.7F),
            floatArrayOf(8.6F, 8.7F, 9.6F, 9.7F, 16.0F),
            floatArrayOf(8.8F, 8.9F, 9.7F, 9.8F, 16.2F),
            floatArrayOf(8.9F, 9.0F, 9.9F, 10.0F, 16.5F),
            floatArrayOf(9.0F, 9.1F, 10.0F, 10.1F, 16.8F),
            floatArrayOf(9.1F, 9.2F, 10.2F, 10.3F, 17.1F),
            floatArrayOf(9.3F, 9.4F, 10.3F, 10.4F, 17.3F),
            floatArrayOf(9.4F, 9.5F, 10.4F, 10.5F, 17.6F),
            floatArrayOf(9.5F, 9.6F, 10.6F, 10.7F, 17.9F),
            floatArrayOf(9.6F, 9.7F, 10.7F, 10.8F, 18.1F),
            floatArrayOf(9.7F, 9.8F, 10.8F, 10.9F, 18.4F),
            floatArrayOf(9.8F, 9.9F, 11.0F, 11.1F, 18.7F),
            floatArrayOf(9.9F, 10.0F, 11.1F, 11.2F, 19.0F),
            floatArrayOf(10.1F, 10.2F, 11.2F, 11.3F, 19.2F),
            floatArrayOf(10.2F, 10.3F, 11.4F, 11.5F, 19.5F),
            floatArrayOf(10.3F, 10.4F, 11.5F, 11.6F, 19.8F),
            floatArrayOf(10.4F, 10.5F, 11.6F, 11.7F, 20.1F),
            floatArrayOf(10.5F, 10.6F, 11.7F, 11.8F, 20.4F),
            floatArrayOf(10.6F, 10.7F, 11.9F, 12.0F, 20.7F),
            floatArrayOf(10.7F, 10.8F, 12.0F, 12.1F, 20.9F),
            floatArrayOf(10.8F, 10.9F, 12.1F, 12.2F, 21.2F),
            floatArrayOf(10.9F, 11.0F, 12.2F, 12.3F, 21.5F),
            floatArrayOf(11.0F, 11.1F, 12.3F, 12.4F, 21.8F),
            floatArrayOf(11.1F, 11.2F, 12.4F, 12.5F, 22.1F),
            floatArrayOf(11.2F, 11.3F, 12.6F, 12.7F, 22.4F),
            floatArrayOf(11.3F, 11.4F, 12.7F, 12.8F, 22.6F),
            floatArrayOf(11.4F, 11.5F, 12.8F, 12.9F, 22.9F),
            floatArrayOf(11.5F, 11.6F, 12.9F, 13.0F, 23.2F),
            floatArrayOf(11.6F, 11.7F, 13.1F, 13.2F, 23.5F),
            floatArrayOf(11.7F, 11.8F, 13.2F, 13.3F, 23.8F),
            floatArrayOf(11.8F, 11.9F, 13.3F, 13.4F, 24.1F),
            floatArrayOf(11.9F, 12.0F, 13.4F, 13.5F, 24.4F),
            floatArrayOf(12.0F, 12.1F, 13.5F, 13.6F, 24.6F),
            floatArrayOf(12.1F, 12.2F, 13.6F, 13.7F, 24.7F),
            floatArrayOf(12.4F, 12.5F, 13.9F, 14.0F, 24.8F),
            floatArrayOf(12.5F, 12.6F, 14.0F, 14.1F, 25.1F),
            floatArrayOf(12.6F, 12.7F, 14.1F, 14.2F, 25.4F),
            floatArrayOf(12.7F, 12.8F, 14.2F, 14.3F, 25.6F),
            floatArrayOf(12.8F, 12.9F, 14.3F, 14.4F, 25.9F),
            floatArrayOf(12.9F, 13.0F, 14.5F, 14.6F, 26.2F),
            floatArrayOf(13.0F, 13.1F, 14.6F, 14.7F, 26.5F),
            floatArrayOf(13.1F, 13.2F, 14.7F, 14.8F, 26.7F),
            floatArrayOf(13.2F, 13.3F, 14.8F, 14.9F, 27.0F),
            floatArrayOf(13.3F, 13.4F, 14.9F, 15.0F, 27.3F),
            floatArrayOf(13.4F, 13.5F, 15.1F, 15.2F, 27.6F)
        )
    }
}