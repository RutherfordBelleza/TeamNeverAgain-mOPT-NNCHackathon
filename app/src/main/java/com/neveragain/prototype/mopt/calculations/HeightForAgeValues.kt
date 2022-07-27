package com.neveragain.prototype.mopt.calculations

class HeightForAgeValues {
    companion object {

        val HEIGHT_STATUS_SEVERLY_STUNTED = 100
        val HEIGHT_STATUS_STUNTED = 101
        val HEIGHT_STATUS_NORMAL = 102
        val HEIGHT_STATUS_TALL = 103
        val HEIGHT_STATUS_TOO_OLD = 104

        fun getStringEquivalent(id: Int): String {
            return when (id) {
                HEIGHT_STATUS_SEVERLY_STUNTED -> "SSt"
                HEIGHT_STATUS_STUNTED -> "St"
                HEIGHT_STATUS_NORMAL -> "N"
                HEIGHT_STATUS_TALL -> "T"
                else -> ""
            }
        }

        fun getFullStringEquivalent(id: Int): String {
            return when (id) {
                HEIGHT_STATUS_SEVERLY_STUNTED -> "Severly Stunted"
                HEIGHT_STATUS_STUNTED -> "Stunted"
                HEIGHT_STATUS_NORMAL -> "Normal"
                HEIGHT_STATUS_TALL -> "Tall"
                else -> ""
            }
        }

        val maleHeightForAge = arrayOf(
            doubleArrayOf(44.1, 44.2, 46.0, 46.1, 53.7, 53.8),
            doubleArrayOf(48.8, 48.9, 50.7, 50.8, 58.6, 58.7),
            doubleArrayOf(52.3, 52.4, 54.3, 54.4, 62.4, 62.5),
            doubleArrayOf(55.2, 55.3, 57.2, 57.3, 65.5, 65.6),
            doubleArrayOf(57.5, 57.6, 59.6, 59.7, 68.0, 68.1),
            doubleArrayOf(59.5, 59.6, 61.6, 61.7, 70.1, 70.2),
            doubleArrayOf(61.1, 61.2, 63.2, 63.3, 71.9, 72.0),
            doubleArrayOf(62.6, 62.7, 64.7, 64.8, 73.5, 73.6),
            doubleArrayOf(63.9, 64.0, 66.1, 66.2, 75.0, 75.1),
            doubleArrayOf(65.1, 65.2, 67.4, 67.5, 76.5, 76.6),
            doubleArrayOf(66.3, 66.4, 68.6, 68.7, 77.9, 78.0),
            doubleArrayOf(67.5, 67.6, 69.8, 69.9, 79.2, 79.3),
            doubleArrayOf(68.5, 68.6, 70.9, 71.0, 80.5, 80.6),
            doubleArrayOf(69.5, 69.6, 72.0, 72.1, 81.8, 81.9),
            doubleArrayOf(70.5, 70.6, 73.0, 73.1, 83.0, 83.1),
            doubleArrayOf(71.5, 71.6, 74.0, 74.1, 84.2, 84.3),
            doubleArrayOf(72.4, 72.5, 74.9, 75.0, 85.4, 85.5),
            doubleArrayOf(73.2, 73.3, 75.9, 76.0, 86.5, 86.6),
            doubleArrayOf(74.1, 74.2, 76.8, 76.9, 87.7, 87.8),
            doubleArrayOf(74.9, 75.0, 77.6, 77.7, 88.8, 88.9),
            doubleArrayOf(75.7, 75.8, 78.5, 78.6, 89.8, 89.9),
            doubleArrayOf(76.4, 76.5, 79.3, 79.4, 90.9, 91.0),
            doubleArrayOf(77.1, 77.2, 80.1, 80.2, 91.9, 92.0),
            doubleArrayOf(77.9, 78.0, 80.9, 81.0, 92.9, 93.0),
            doubleArrayOf(77.9, 78.0, 80.9, 81.0, 93.2, 93.3),
            doubleArrayOf(78.5, 78.6, 81.6, 81.7, 94.2, 94.3),
            doubleArrayOf(79.2, 79.3, 82.4, 82.5, 95.2, 95.3),
            doubleArrayOf(79.8, 79.9, 83.0, 83.1, 96.1, 96.2),
            doubleArrayOf(80.4, 80.5, 83.7, 83.8, 97.0, 97.1),
            doubleArrayOf(81.0, 81.1, 84.4, 84.5, 97.9, 98.0),
            doubleArrayOf(81.6, 81.7, 85.0, 85.1, 98.7, 98.8),
            doubleArrayOf(82.2, 82.3, 85.6, 85.7, 99.6, 99.7),
            doubleArrayOf(82.7, 82.8, 86.3, 86.4, 100.4, 100.5),
            doubleArrayOf(83.3, 83.4, 86.8, 86.9, 101.2, 101.3),
            doubleArrayOf(83.8, 83.9, 87.4, 87.5, 102.0, 102.1),
            doubleArrayOf(84.3, 84.4, 88.0, 88.1, 102.7, 102.8),
            doubleArrayOf(84.9, 85.0, 88.6, 88.7, 103.5, 103.6),
            doubleArrayOf(85.4, 85.5, 89.1, 89.2, 104.2, 104.3),
            doubleArrayOf(85.9, 86.0, 89.7, 89.8, 105.0, 105.1),
            doubleArrayOf(86.4, 86.5, 90.2, 90.3, 105.7, 105.8),
            doubleArrayOf(86.9, 87.0, 90.8, 90.9, 106.4, 106.5),
            doubleArrayOf(87.4, 87.5, 91.3, 91.4, 107.1, 107.2),
            doubleArrayOf(87.9, 88.0, 91.8, 91.9, 107.8, 107.9),
            doubleArrayOf(88.3, 88.4, 92.3, 92.4, 108.5, 108.6),
            doubleArrayOf(88.8, 88.9, 92.9, 93.0, 109.1, 109.2),
            doubleArrayOf(89.3, 89.4, 93.4, 93.5, 109.8, 109.9),
            doubleArrayOf(89.7, 89.8, 93.9, 94.0, 110.4, 110.5),
            doubleArrayOf(90.2, 90.3, 94.3, 94.4, 111.1, 111.2),
            doubleArrayOf(90.6, 90.7, 94.8, 94.9, 111.7, 111.8),
            doubleArrayOf(91.1, 91.2, 95.3, 95.4, 112.4, 112.5),
            doubleArrayOf(91.5, 91.6, 95.8, 95.9, 113.0, 113.1),
            doubleArrayOf(92.0, 92.1, 96.3, 96.4, 113.6, 113.7),
            doubleArrayOf(92.4, 92.5, 96.8, 96.9, 114.2, 114.3),
            doubleArrayOf(92.9, 93.0, 97.3, 97.4, 114.9, 115.0),
            doubleArrayOf(93.3, 93.4, 97.7, 97.8, 115.5, 115.6),
            doubleArrayOf(93.8, 93.9, 98.2, 98.3, 116.1, 116.2),
            doubleArrayOf(94.2, 94.3, 98.7, 98.8, 116.7, 116.8),
            doubleArrayOf(94.6, 94.7, 99.2, 99.3, 117.4, 117.5),
            doubleArrayOf(95.1, 95.2, 99.6, 99.7, 118.0, 118.1),
            doubleArrayOf(95.5, 95.6, 100.1, 100.2, 118.6, 118.7),
            doubleArrayOf(96.0, 96.1, 100.6, 100.7, 119.2, 119.3),
            doubleArrayOf(96.4, 96.5, 101.0, 101.1, 119.4, 119.5),
            doubleArrayOf(96.8, 96.9, 101.5, 101.6, 120.0, 120.1),
            doubleArrayOf(97.3, 97.4, 101.9, 102.0, 120.6, 120.7),
            doubleArrayOf(97.7, 97.8, 102.4, 102.5, 121.2, 121.3),
            doubleArrayOf(98.1, 98.2, 102.9, 103.0, 121.8, 121.9),
            doubleArrayOf(98.6, 98.7, 103.3, 103.4, 122.4, 122.5),
            doubleArrayOf(99.0, 99.1, 103.8, 103.9, 123.0, 123.1),
            doubleArrayOf(99.4, 99.5, 104.2, 104.3, 123.6, 123.7),
            doubleArrayOf(99.8, 99.9, 104.7, 104.8, 124.1, 124.2),
            doubleArrayOf(100.3, 100.4, 105.1, 105.2, 124.7, 124.8),
            doubleArrayOf(100.7, 100.8, 105.6, 105.7, 125.2, 125.3)
        )

        val femaleHeightForAge = arrayOf(
            doubleArrayOf(43.5, 43.6, 45.3, 45.4, 52.9, 53.0),
            doubleArrayOf(47.7, 47.8, 49.7, 49.8, 57.6, 57.7),
            doubleArrayOf(50.9, 51.0, 52.9, 53.0, 61.1, 61.2),
            doubleArrayOf(53.4, 53.5, 55.5, 55.6, 64.0, 64.1),
            doubleArrayOf(55.5, 55.6, 57.7, 57.8, 66.4, 66.5),
            doubleArrayOf(57.3, 57.4, 59.5, 59.6, 68.5, 68.6),
            doubleArrayOf(58.8, 58.9, 61.1, 61.2, 70.3, 70.4),
            doubleArrayOf(60.2, 60.3, 62.6, 62.7, 71.9, 72.0),
            doubleArrayOf(61.6, 61.7, 63.9, 64.0, 73.5, 73.6),
            doubleArrayOf(62.8, 62.9, 65.2, 65.3, 75.0, 75.1),
            doubleArrayOf(64.0, 64.1, 66.4, 66.5, 76.4, 76.5),
            doubleArrayOf(65.1, 65.2, 67.6, 67.7, 77.8, 77.9),
            doubleArrayOf(66.2, 66.3, 68.8, 68.9, 79.2, 79.3),
            doubleArrayOf(67.2, 67.3, 69.9, 70.0, 80.5, 80.6),
            doubleArrayOf(68.2, 68.3, 70.9, 71.0, 81.7, 81.8),
            doubleArrayOf(69.2, 69.3, 71.9, 72.0, 83.0, 83.1),
            doubleArrayOf(70.1, 70.2, 72.9, 73.0, 84.2, 84.3),
            doubleArrayOf(71.0, 71.1, 73.9, 74.0, 85.4, 85.5),
            doubleArrayOf(71.9, 72.0, 74.8, 74.9, 86.5, 86.6),
            doubleArrayOf(72.7, 72.8, 75.7, 75.8, 87.6, 87.7),
            doubleArrayOf(73.6, 73.7, 76.6, 76.7, 88.7, 88.8),
            doubleArrayOf(74.4, 74.5, 77.4, 77.5, 89.8, 89.9),
            doubleArrayOf(75.1, 75.2, 78.3, 78.4, 90.8, 90.9),
            doubleArrayOf(75.9, 76.0, 79.1, 79.2, 91.9, 92.0),
            doubleArrayOf(75.9, 76.0, 79.2, 79.3, 92.2, 92.3),
            doubleArrayOf(76.7, 76.8, 79.9, 80.0, 93.1, 93.2),
            doubleArrayOf(77.4, 77.5, 80.7, 80.8, 94.1, 94.2),
            doubleArrayOf(78.0, 78.1, 81.4, 81.5, 95.0, 95.1),
            doubleArrayOf(78.7, 78.8, 82.1, 82.2, 96.0, 96.1),
            doubleArrayOf(79.4, 79.5, 82.8, 82.9, 96.9, 97.0),
            doubleArrayOf(80.0, 80.1, 83.5, 83.6, 97.7, 97.8),
            doubleArrayOf(80.6, 80.7, 84.2, 84.3, 98.6, 98.7),
            doubleArrayOf(81.2, 81.3, 84.8, 84.9, 99.4, 99.5),
            doubleArrayOf(81.8, 81.9, 85.5, 85.6, 100.3, 100.4),
            doubleArrayOf(82.4, 82.5, 86.1, 86.2, 101.1, 101.2),
            doubleArrayOf(83.0, 83.1, 86.7, 86.8, 101.9, 102.0),
            doubleArrayOf(83.5, 83.6, 87.3, 87.4, 102.7, 102.8),
            doubleArrayOf(84.1, 84.2, 87.9, 88.0, 103.4, 103.5),
            doubleArrayOf(84.6, 84.7, 88.5, 88.6, 104.2, 104.3),
            doubleArrayOf(85.2, 85.3, 89.1, 89.2, 105.0, 105.1),
            doubleArrayOf(85.7, 85.8, 89.7, 89.8, 105.7, 105.8),
            doubleArrayOf(86.2, 86.3, 90.3, 90.4, 106.4, 106.5),
            doubleArrayOf(86.7, 86.8, 90.8, 90.9, 107.2, 107.3),
            doubleArrayOf(87.3, 87.4, 91.4, 91.5, 107.9, 108.0),
            doubleArrayOf(87.8, 87.9, 91.9, 92.0, 108.6, 108.7),
            doubleArrayOf(88.3, 88.4, 92.4, 92.5, 109.3, 109.4),
            doubleArrayOf(88.8, 88.9, 93.0, 93.1, 110.0, 110.1),
            doubleArrayOf(89.2, 89.3, 93.5, 93.6, 110.7, 110.8),
            doubleArrayOf(89.7, 89.8, 94.0, 94.1, 111.3, 111.4),
            doubleArrayOf(90.2, 90.3, 94.5, 94.6, 112.0, 112.1),
            doubleArrayOf(90.6, 90.7, 95.0, 95.1, 112.7, 112.8),
            doubleArrayOf(91.1, 91.2, 95.5, 95.6, 113.3, 113.4),
            doubleArrayOf(91.6, 91.7, 96.0, 96.1, 114.0, 114.1),
            doubleArrayOf(92.0, 92.1, 96.5, 96.6, 114.6, 114.7),
            doubleArrayOf(92.5, 92.6, 97.0, 97.1, 115.2, 115.3),
            doubleArrayOf(92.9, 93.0, 97.5, 97.6, 115.9, 116.0),
            doubleArrayOf(93.3, 93.4, 98.0, 98.1, 116.5, 116.6),
            doubleArrayOf(93.8, 93.9, 98.4, 98.5, 117.1, 117.2),
            doubleArrayOf(94.2, 94.3, 98.9, 99.0, 117.7, 117.8),
            doubleArrayOf(94.6, 94.7, 99.4, 99.5, 118.3, 118.4),
            doubleArrayOf(95.1, 95.2, 99.8, 99.9, 118.9, 119.0),
            doubleArrayOf(95.2, 95.3, 100.0, 100.1, 119.1, 119.2),
            doubleArrayOf(95.6, 95.7, 100.4, 100.5, 119.7, 119.8),
            doubleArrayOf(96.0, 96.1, 100.9, 101.0, 120.3, 120.4),
            doubleArrayOf(96.4, 96.5, 101.3, 101.4, 120.9, 121.0),
            doubleArrayOf(96.9, 97.0, 101.8, 101.9, 121.5, 121.6),
            doubleArrayOf(97.3, 97.4, 102.2, 102.3, 122.0, 122.1),
            doubleArrayOf(97.7, 97.8, 102.6, 102.7, 122.6, 122.7),
            doubleArrayOf(98.1, 98.2, 103.1, 103.2, 123.2, 123.3),
            doubleArrayOf(98.5, 98.6, 103.5, 103.6, 123.7, 123.8),
            doubleArrayOf(98.9, 99.0, 103.9, 104.0, 124.3, 124.4),
            doubleArrayOf(99.3, 99.4, 104.4, 104.5, 124.8, 124.9)
        )
    }
}