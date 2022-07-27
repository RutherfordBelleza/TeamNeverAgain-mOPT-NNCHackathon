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
            doubleArrayOf(2.1, 2.2, 2.4, 2.5, 4.4),
            doubleArrayOf(2.9, 3.0, 3.3, 3.4, 5.8),
            doubleArrayOf(3.8, 3.9, 4.2, 4.3, 7.1),
            doubleArrayOf(4.4, 4.5, 4.9, 5.0, 8.0),
            doubleArrayOf(4.9, 5.0, 5.5, 5.6, 8.7),
            doubleArrayOf(5.3, 5.4, 5.9, 6.0, 9.3),
            doubleArrayOf(5.7, 5.8, 6.3, 6.4, 9.8),
            doubleArrayOf(5.9, 6.0, 6.6, 6.7, 10.3),
            doubleArrayOf(6.2, 6.3, 6.8, 6.9, 10.7),
            doubleArrayOf(6.4, 6.5, 7.0, 7.1, 11.0),
            doubleArrayOf(6.6, 6.7, 7.3, 7.4, 11.4),
            doubleArrayOf(6.8, 6.9, 7.5, 7.6, 11.7),
            doubleArrayOf(6.9, 7.0, 7.6, 7.7, 12.0),
            doubleArrayOf(7.1, 7.2, 7.8, 7.9, 12.3),
            doubleArrayOf(7.2, 7.3, 8.0, 8.1, 12.6),
            doubleArrayOf(7.4, 7.5, 8.2, 8.3, 12.8),
            doubleArrayOf(7.5, 7.6, 8.3, 8.4, 13.1),
            doubleArrayOf(7.7, 7.8, 8.5, 8.6, 13.4),
            doubleArrayOf(7.8, 7.9, 8.7, 8.8, 13.7),
            doubleArrayOf(8.0, 8.1, 8.8, 8.9, 13.9),
            doubleArrayOf(8.1, 8.2, 9.0, 9.1, 14.2),
            doubleArrayOf(8.2, 8.3, 9.1, 9.2, 14.5),
            doubleArrayOf(8.4, 8.5, 9.3, 9.4, 14.7),
            doubleArrayOf(8.5, 8.6, 9.4, 9.5, 15.0),
            doubleArrayOf(8.6, 8.7, 9.6, 9.7, 15.3),
            doubleArrayOf(8.8, 8.9, 9.7, 9.8, 15.5),
            doubleArrayOf(8.9, 9.0, 9.9, 10.0, 15.8),
            doubleArrayOf(9.0, 9.1, 10.0, 10.1, 16.1),
            doubleArrayOf(9.1, 9.2, 10.1, 10.2, 16.3),
            doubleArrayOf(9.2, 9.3, 10.3, 10.4, 16.6),
            doubleArrayOf(9.4, 9.5, 10.4, 10.5, 16.9),
            doubleArrayOf(9.5, 9.6, 10.6, 10.7, 17.1),
            doubleArrayOf(9.6, 9.7, 10.7, 10.8, 17.4),
            doubleArrayOf(9.7, 9.8, 10.8, 10.9, 17.6),
            doubleArrayOf(9.8, 9.9, 10.9, 11.0, 17.8),
            doubleArrayOf(9.9, 10.0, 11.1, 11.2, 18.1),
            doubleArrayOf(10.0, 10.1, 11.2, 11.3, 18.3),
            doubleArrayOf(10.1, 10.2, 11.3, 11.4, 18.6),
            doubleArrayOf(10.2, 10.3, 11.4, 11.5, 18.8),
            doubleArrayOf(10.3, 10.4, 11.5, 11.6, 19.0),
            doubleArrayOf(10.4, 10.5, 11.7, 11.8, 19.3),
            doubleArrayOf(10.5, 10.6, 11.8, 11.9, 19.5),
            doubleArrayOf(10.6, 10.7, 11.9, 12.0, 19.7),
            doubleArrayOf(10.7, 10.8, 12.0, 12.1, 20.0),
            doubleArrayOf(10.8, 10.9, 12.1, 12.2, 20.2),
            doubleArrayOf(10.9, 11.0, 12.3, 12.4, 20.5),
            doubleArrayOf(11.0, 11.1, 12.4, 12.5, 20.7),
            doubleArrayOf(11.1, 11.2, 12.5, 12.6, 20.9),
            doubleArrayOf(11.2, 11.3, 12.6, 12.7, 21.2),
            doubleArrayOf(11.3, 11.4, 12.7, 12.8, 21.4),
            doubleArrayOf(11.4, 11.5, 12.8, 12.9, 21.7),
            doubleArrayOf(11.5, 11.6, 13.0, 13.1, 21.9),
            doubleArrayOf(11.6, 11.7, 13.1, 13.2, 22.2),
            doubleArrayOf(11.7, 11.8, 13.2, 13.3, 22.4),
            doubleArrayOf(11.8, 11.9, 13.3, 13.4, 22.7),
            doubleArrayOf(11.9, 12.0, 13.4, 13.5, 22.9),
            doubleArrayOf(12.0, 12.1, 13.5, 13.6, 23.2),
            doubleArrayOf(12.1, 12.2, 13.6, 13.7, 23.4),
            doubleArrayOf(12.2, 12.3, 13.7, 13.8, 23.7),
            doubleArrayOf(12.3, 12.4, 13.9, 14.0, 23.9),
            doubleArrayOf(12.4, 12.5, 14.0, 14.1, 24.2),
            doubleArrayOf(12.7, 12.8, 14.3, 14.4, 24.3),
            doubleArrayOf(12.8, 12.9, 14.4, 14.5, 24.4),
            doubleArrayOf(13.0, 13.1, 14.5, 14.6, 24.7),
            doubleArrayOf(13.1, 13.2, 14.7, 14.8, 24.9),
            doubleArrayOf(13.2, 13.3, 14.8, 14.9, 25.2),
            doubleArrayOf(13.3, 13.4, 14.9, 15.0, 25.5),
            doubleArrayOf(13.4, 13.5, 15.1, 15.2, 25.7),
            doubleArrayOf(13.6, 13.7, 15.2, 15.3, 26.0),
            doubleArrayOf(13.7, 13.8, 15.3, 15.4, 26.3),
            doubleArrayOf(13.8, 13.9, 15.5, 15.6, 26.6),
            doubleArrayOf(13.9, 14.0, 15.6, 15.7, 26.8)
        )

        val femaleWeightForAge = arrayOf(
            doubleArrayOf(2.0, 2.1, 2.3, 2.4, 4.2),
            doubleArrayOf(2.7, 2.8, 3.1, 3.2, 5.5),
            doubleArrayOf(3.4, 3.5, 3.8, 3.9, 6.6),
            doubleArrayOf(4.0, 4.1, 4.4, 4.5, 7.5),
            doubleArrayOf(4.4, 4.5, 4.9, 5.0, 8.2),
            doubleArrayOf(4.8, 4.9, 5.3, 5.4, 8.8),
            doubleArrayOf(5.1, 5.2, 5.6, 5.7, 9.3),
            doubleArrayOf(5.3, 5.4, 5.9, 6.0, 9.8),
            doubleArrayOf(5.6, 5.7, 6.2, 6.3, 10.2),
            doubleArrayOf(5.8, 5.9, 6.4, 6.5, 10.5),
            doubleArrayOf(5.9, 6.0, 6.6, 6.7, 10.9),
            doubleArrayOf(6.1, 6.2, 6.8, 6.9, 11.2),
            doubleArrayOf(6.3, 6.4, 6.9, 7.0, 11.5),
            doubleArrayOf(6.4, 6.5, 7.1, 7.2, 11.8),
            doubleArrayOf(6.6, 6.7, 7.3, 7.4, 12.1),
            doubleArrayOf(6.7, 6.8, 7.5, 7.6, 12.4),
            doubleArrayOf(6.9, 7.0, 7.6, 7.7, 12.6),
            doubleArrayOf(7.0, 7.1, 7.8, 7.9, 12.9),
            doubleArrayOf(7.2, 7.3, 8.0, 8.1, 13.2),
            doubleArrayOf(7.3, 7.4, 8.1, 8.2, 13.5),
            doubleArrayOf(7.5, 7.6, 8.3, 8.4, 13.7),
            doubleArrayOf(7.6, 7.7, 8.5, 8.6, 14.0),
            doubleArrayOf(7.8, 7.9, 8.6, 8.7, 14.3),
            doubleArrayOf(7.9, 8.0, 8.8, 8.9, 14.6),
            doubleArrayOf(8.1, 8.2, 8.9, 9.0, 14.8),
            doubleArrayOf(8.2, 8.3, 9.1, 9.2, 15.1),
            doubleArrayOf(8.4, 8.5, 9.3, 9.4, 15.4),
            doubleArrayOf(8.5, 8.6, 9.4, 9.5, 15.7),
            doubleArrayOf(8.6, 8.7, 9.6, 9.7, 16.0),
            doubleArrayOf(8.8, 8.9, 9.7, 9.8, 16.2),
            doubleArrayOf(8.9, 9.0, 9.9, 10.0, 16.5),
            doubleArrayOf(9.0, 9.1, 10.0, 10.1, 16.8),
            doubleArrayOf(9.1, 9.2, 10.2, 10.3, 17.1),
            doubleArrayOf(9.3, 9.4, 10.3, 10.4, 17.3),
            doubleArrayOf(9.4, 9.5, 10.4, 10.5, 17.6),
            doubleArrayOf(9.5, 9.6, 10.6, 10.7, 17.9),
            doubleArrayOf(9.6, 9.7, 10.7, 10.8, 18.1),
            doubleArrayOf(9.7, 9.8, 10.8, 10.9, 18.4),
            doubleArrayOf(9.8, 9.9, 11.0, 11.1, 18.7),
            doubleArrayOf(9.9, 10.0, 11.1, 11.2, 19.0),
            doubleArrayOf(10.1, 10.2, 11.2, 11.3, 19.2),
            doubleArrayOf(10.2, 10.3, 11.4, 11.5, 19.5),
            doubleArrayOf(10.3, 10.4, 11.5, 11.6, 19.8),
            doubleArrayOf(10.4, 10.5, 11.6, 11.7, 20.1),
            doubleArrayOf(10.5, 10.6, 11.7, 11.8, 20.4),
            doubleArrayOf(10.6, 10.7, 11.9, 12.0, 20.7),
            doubleArrayOf(10.7, 10.8, 12.0, 12.1, 20.9),
            doubleArrayOf(10.8, 10.9, 12.1, 12.2, 21.2),
            doubleArrayOf(10.9, 11.0, 12.2, 12.3, 21.5),
            doubleArrayOf(11.0, 11.1, 12.3, 12.4, 21.8),
            doubleArrayOf(11.1, 11.2, 12.4, 12.5, 22.1),
            doubleArrayOf(11.2, 11.3, 12.6, 12.7, 22.4),
            doubleArrayOf(11.3, 11.4, 12.7, 12.8, 22.6),
            doubleArrayOf(11.4, 11.5, 12.8, 12.9, 22.9),
            doubleArrayOf(11.5, 11.6, 12.9, 13.0, 23.2),
            doubleArrayOf(11.6, 11.7, 13.1, 13.2, 23.5),
            doubleArrayOf(11.7, 11.8, 13.2, 13.3, 23.8),
            doubleArrayOf(11.8, 11.9, 13.3, 13.4, 24.1),
            doubleArrayOf(11.9, 12.0, 13.4, 13.5, 24.4),
            doubleArrayOf(12.0, 12.1, 13.5, 13.6, 24.6),
            doubleArrayOf(12.1, 12.2, 13.6, 13.7, 24.7),
            doubleArrayOf(12.4, 12.5, 13.9, 14.0, 24.8),
            doubleArrayOf(12.5, 12.6, 14.0, 14.1, 25.1),
            doubleArrayOf(12.6, 12.7, 14.1, 14.2, 25.4),
            doubleArrayOf(12.7, 12.8, 14.2, 14.3, 25.6),
            doubleArrayOf(12.8, 12.9, 14.3, 14.4, 25.9),
            doubleArrayOf(12.9, 13.0, 14.5, 14.6, 26.2),
            doubleArrayOf(13.0, 13.1, 14.6, 14.7, 26.5),
            doubleArrayOf(13.1, 13.2, 14.7, 14.8, 26.7),
            doubleArrayOf(13.2, 13.3, 14.8, 14.9, 27.0),
            doubleArrayOf(13.3, 13.4, 14.9, 15.0, 27.3),
            doubleArrayOf(13.4, 13.5, 15.1, 15.2, 27.6)
        )
    }
}