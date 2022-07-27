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
            floatArrayOf(44.1F, 44.2F, 46.0F, 46.1F, 53.7F, 53.8F),
            floatArrayOf(48.8F, 48.9F, 50.7F, 50.8F, 58.6F, 58.7F),
            floatArrayOf(52.3F, 52.4F, 54.3F, 54.4F, 62.4F, 62.5F),
            floatArrayOf(55.2F, 55.3F, 57.2F, 57.3F, 65.5F, 65.6F),
            floatArrayOf(57.5F, 57.6F, 59.6F, 59.7F, 68.0F, 68.1F),
            floatArrayOf(59.5F, 59.6F, 61.6F, 61.7F, 70.1F, 70.2F),
            floatArrayOf(61.1F, 61.2F, 63.2F, 63.3F, 71.9F, 72.0F),
            floatArrayOf(62.6F, 62.7F, 64.7F, 64.8F, 73.5F, 73.6F),
            floatArrayOf(63.9F, 64.0F, 66.1F, 66.2F, 75.0F, 75.1F),
            floatArrayOf(65.1F, 65.2F, 67.4F, 67.5F, 76.5F, 76.6F),
            floatArrayOf(66.3F, 66.4F, 68.6F, 68.7F, 77.9F, 78.0F),
            floatArrayOf(67.5F, 67.6F, 69.8F, 69.9F, 79.2F, 79.3F),
            floatArrayOf(68.5F, 68.6F, 70.9F, 71.0F, 80.5F, 80.6F),
            floatArrayOf(69.5F, 69.6F, 72.0F, 72.1F, 81.8F, 81.9F),
            floatArrayOf(70.5F, 70.6F, 73.0F, 73.1F, 83.0F, 83.1F),
            floatArrayOf(71.5F, 71.6F, 74.0F, 74.1F, 84.2F, 84.3F),
            floatArrayOf(72.4F, 72.5F, 74.9F, 75.0F, 85.4F, 85.5F),
            floatArrayOf(73.2F, 73.3F, 75.9F, 76.0F, 86.5F, 86.6F),
            floatArrayOf(74.1F, 74.2F, 76.8F, 76.9F, 87.7F, 87.8F),
            floatArrayOf(74.9F, 75.0F, 77.6F, 77.7F, 88.8F, 88.9F),
            floatArrayOf(75.7F, 75.8F, 78.5F, 78.6F, 89.8F, 89.9F),
            floatArrayOf(76.4F, 76.5F, 79.3F, 79.4F, 90.9F, 91.0F),
            floatArrayOf(77.1F, 77.2F, 80.1F, 80.2F, 91.9F, 92.0F),
            floatArrayOf(77.9F, 78.0F, 80.9F, 81.0F, 92.9F, 93.0F),
            floatArrayOf(77.9F, 78.0F, 80.9F, 81.0F, 93.2F, 93.3F),
            floatArrayOf(78.5F, 78.6F, 81.6F, 81.7F, 94.2F, 94.3F),
            floatArrayOf(79.2F, 79.3F, 82.4F, 82.5F, 95.2F, 95.3F),
            floatArrayOf(79.8F, 79.9F, 83.0F, 83.1F, 96.1F, 96.2F),
            floatArrayOf(80.4F, 80.5F, 83.7F, 83.8F, 97.0F, 97.1F),
            floatArrayOf(81.0F, 81.1F, 84.4F, 84.5F, 97.9F, 98.0F),
            floatArrayOf(81.6F, 81.7F, 85.0F, 85.1F, 98.7F, 98.8F),
            floatArrayOf(82.2F, 82.3F, 85.6F, 85.7F, 99.6F, 99.7F),
            floatArrayOf(82.7F, 82.8F, 86.3F, 86.4F, 100.4F, 100.5F),
            floatArrayOf(83.3F, 83.4F, 86.8F, 86.9F, 101.2F, 101.3F),
            floatArrayOf(83.8F, 83.9F, 87.4F, 87.5F, 102.0F, 102.1F),
            floatArrayOf(84.3F, 84.4F, 88.0F, 88.1F, 102.7F, 102.8F),
            floatArrayOf(84.9F, 85.0F, 88.6F, 88.7F, 103.5F, 103.6F),
            floatArrayOf(85.4F, 85.5F, 89.1F, 89.2F, 104.2F, 104.3F),
            floatArrayOf(85.9F, 86.0F, 89.7F, 89.8F, 105.0F, 105.1F),
            floatArrayOf(86.4F, 86.5F, 90.2F, 90.3F, 105.7F, 105.8F),
            floatArrayOf(86.9F, 87.0F, 90.8F, 90.9F, 106.4F, 106.5F),
            floatArrayOf(87.4F, 87.5F, 91.3F, 91.4F, 107.1F, 107.2F),
            floatArrayOf(87.9F, 88.0F, 91.8F, 91.9F, 107.8F, 107.9F),
            floatArrayOf(88.3F, 88.4F, 92.3F, 92.4F, 108.5F, 108.6F),
            floatArrayOf(88.8F, 88.9F, 92.9F, 93.0F, 109.1F, 109.2F),
            floatArrayOf(89.3F, 89.4F, 93.4F, 93.5F, 109.8F, 109.9F),
            floatArrayOf(89.7F, 89.8F, 93.9F, 94.0F, 110.4F, 110.5F),
            floatArrayOf(90.2F, 90.3F, 94.3F, 94.4F, 111.1F, 111.2F),
            floatArrayOf(90.6F, 90.7F, 94.8F, 94.9F, 111.7F, 111.8F),
            floatArrayOf(91.1F, 91.2F, 95.3F, 95.4F, 112.4F, 112.5F),
            floatArrayOf(91.5F, 91.6F, 95.8F, 95.9F, 113.0F, 113.1F),
            floatArrayOf(92.0F, 92.1F, 96.3F, 96.4F, 113.6F, 113.7F),
            floatArrayOf(92.4F, 92.5F, 96.8F, 96.9F, 114.2F, 114.3F),
            floatArrayOf(92.9F, 93.0F, 97.3F, 97.4F, 114.9F, 115.0F),
            floatArrayOf(93.3F, 93.4F, 97.7F, 97.8F, 115.5F, 115.6F),
            floatArrayOf(93.8F, 93.9F, 98.2F, 98.3F, 116.1F, 116.2F),
            floatArrayOf(94.2F, 94.3F, 98.7F, 98.8F, 116.7F, 116.8F),
            floatArrayOf(94.6F, 94.7F, 99.2F, 99.3F, 117.4F, 117.5F),
            floatArrayOf(95.1F, 95.2F, 99.6F, 99.7F, 118.0F, 118.1F),
            floatArrayOf(95.5F, 95.6F, 100.1F, 100.2F, 118.6F, 118.7F),
            floatArrayOf(96.0F, 96.1F, 100.6F, 100.7F, 119.2F, 119.3F),
            floatArrayOf(96.4F, 96.5F, 101.0F, 101.1F, 119.4F, 119.5F),
            floatArrayOf(96.8F, 96.9F, 101.5F, 101.6F, 120.0F, 120.1F),
            floatArrayOf(97.3F, 97.4F, 101.9F, 102.0F, 120.6F, 120.7F),
            floatArrayOf(97.7F, 97.8F, 102.4F, 102.5F, 121.2F, 121.3F),
            floatArrayOf(98.1F, 98.2F, 102.9F, 103.0F, 121.8F, 121.9F),
            floatArrayOf(98.6F, 98.7F, 103.3F, 103.4F, 122.4F, 122.5F),
            floatArrayOf(99.0F, 99.1F, 103.8F, 103.9F, 123.0F, 123.1F),
            floatArrayOf(99.4F, 99.5F, 104.2F, 104.3F, 123.6F, 123.7F),
            floatArrayOf(99.8F, 99.9F, 104.7F, 104.8F, 124.1F, 124.2F),
            floatArrayOf(100.3F, 100.4F, 105.1F, 105.2F, 124.7F, 124.8F),
            floatArrayOf(100.7F, 100.8F, 105.6F, 105.7F, 125.2F, 125.3F)
        )

        val femaleHeightForAge = arrayOf(
            floatArrayOf(43.5F, 43.6F, 45.3F, 45.4F, 52.9F, 53.0F),
            floatArrayOf(47.7F, 47.8F, 49.7F, 49.8F, 57.6F, 57.7F),
            floatArrayOf(50.9F, 51.0F, 52.9F, 53.0F, 61.1F, 61.2F),
            floatArrayOf(53.4F, 53.5F, 55.5F, 55.6F, 64.0F, 64.1F),
            floatArrayOf(55.5F, 55.6F, 57.7F, 57.8F, 66.4F, 66.5F),
            floatArrayOf(57.3F, 57.4F, 59.5F, 59.6F, 68.5F, 68.6F),
            floatArrayOf(58.8F, 58.9F, 61.1F, 61.2F, 70.3F, 70.4F),
            floatArrayOf(60.2F, 60.3F, 62.6F, 62.7F, 71.9F, 72.0F),
            floatArrayOf(61.6F, 61.7F, 63.9F, 64.0F, 73.5F, 73.6F),
            floatArrayOf(62.8F, 62.9F, 65.2F, 65.3F, 75.0F, 75.1F),
            floatArrayOf(64.0F, 64.1F, 66.4F, 66.5F, 76.4F, 76.5F),
            floatArrayOf(65.1F, 65.2F, 67.6F, 67.7F, 77.8F, 77.9F),
            floatArrayOf(66.2F, 66.3F, 68.8F, 68.9F, 79.2F, 79.3F),
            floatArrayOf(67.2F, 67.3F, 69.9F, 70.0F, 80.5F, 80.6F),
            floatArrayOf(68.2F, 68.3F, 70.9F, 71.0F, 81.7F, 81.8F),
            floatArrayOf(69.2F, 69.3F, 71.9F, 72.0F, 83.0F, 83.1F),
            floatArrayOf(70.1F, 70.2F, 72.9F, 73.0F, 84.2F, 84.3F),
            floatArrayOf(71.0F, 71.1F, 73.9F, 74.0F, 85.4F, 85.5F),
            floatArrayOf(71.9F, 72.0F, 74.8F, 74.9F, 86.5F, 86.6F),
            floatArrayOf(72.7F, 72.8F, 75.7F, 75.8F, 87.6F, 87.7F),
            floatArrayOf(73.6F, 73.7F, 76.6F, 76.7F, 88.7F, 88.8F),
            floatArrayOf(74.4F, 74.5F, 77.4F, 77.5F, 89.8F, 89.9F),
            floatArrayOf(75.1F, 75.2F, 78.3F, 78.4F, 90.8F, 90.9F),
            floatArrayOf(75.9F, 76.0F, 79.1F, 79.2F, 91.9F, 92.0F),
            floatArrayOf(75.9F, 76.0F, 79.2F, 79.3F, 92.2F, 92.3F),
            floatArrayOf(76.7F, 76.8F, 79.9F, 80.0F, 93.1F, 93.2F),
            floatArrayOf(77.4F, 77.5F, 80.7F, 80.8F, 94.1F, 94.2F),
            floatArrayOf(78.0F, 78.1F, 81.4F, 81.5F, 95.0F, 95.1F),
            floatArrayOf(78.7F, 78.8F, 82.1F, 82.2F, 96.0F, 96.1F),
            floatArrayOf(79.4F, 79.5F, 82.8F, 82.9F, 96.9F, 97.0F),
            floatArrayOf(80.0F, 80.1F, 83.5F, 83.6F, 97.7F, 97.8F),
            floatArrayOf(80.6F, 80.7F, 84.2F, 84.3F, 98.6F, 98.7F),
            floatArrayOf(81.2F, 81.3F, 84.8F, 84.9F, 99.4F, 99.5F),
            floatArrayOf(81.8F, 81.9F, 85.5F, 85.6F, 100.3F, 100.4F),
            floatArrayOf(82.4F, 82.5F, 86.1F, 86.2F, 101.1F, 101.2F),
            floatArrayOf(83.0F, 83.1F, 86.7F, 86.8F, 101.9F, 102.0F),
            floatArrayOf(83.5F, 83.6F, 87.3F, 87.4F, 102.7F, 102.8F),
            floatArrayOf(84.1F, 84.2F, 87.9F, 88.0F, 103.4F, 103.5F),
            floatArrayOf(84.6F, 84.7F, 88.5F, 88.6F, 104.2F, 104.3F),
            floatArrayOf(85.2F, 85.3F, 89.1F, 89.2F, 105.0F, 105.1F),
            floatArrayOf(85.7F, 85.8F, 89.7F, 89.8F, 105.7F, 105.8F),
            floatArrayOf(86.2F, 86.3F, 90.3F, 90.4F, 106.4F, 106.5F),
            floatArrayOf(86.7F, 86.8F, 90.8F, 90.9F, 107.2F, 107.3F),
            floatArrayOf(87.3F, 87.4F, 91.4F, 91.5F, 107.9F, 108.0F),
            floatArrayOf(87.8F, 87.9F, 91.9F, 92.0F, 108.6F, 108.7F),
            floatArrayOf(88.3F, 88.4F, 92.4F, 92.5F, 109.3F, 109.4F),
            floatArrayOf(88.8F, 88.9F, 93.0F, 93.1F, 110.0F, 110.1F),
            floatArrayOf(89.2F, 89.3F, 93.5F, 93.6F, 110.7F, 110.8F),
            floatArrayOf(89.7F, 89.8F, 94.0F, 94.1F, 111.3F, 111.4F),
            floatArrayOf(90.2F, 90.3F, 94.5F, 94.6F, 112.0F, 112.1F),
            floatArrayOf(90.6F, 90.7F, 95.0F, 95.1F, 112.7F, 112.8F),
            floatArrayOf(91.1F, 91.2F, 95.5F, 95.6F, 113.3F, 113.4F),
            floatArrayOf(91.6F, 91.7F, 96.0F, 96.1F, 114.0F, 114.1F),
            floatArrayOf(92.0F, 92.1F, 96.5F, 96.6F, 114.6F, 114.7F),
            floatArrayOf(92.5F, 92.6F, 97.0F, 97.1F, 115.2F, 115.3F),
            floatArrayOf(92.9F, 93.0F, 97.5F, 97.6F, 115.9F, 116.0F),
            floatArrayOf(93.3F, 93.4F, 98.0F, 98.1F, 116.5F, 116.6F),
            floatArrayOf(93.8F, 93.9F, 98.4F, 98.5F, 117.1F, 117.2F),
            floatArrayOf(94.2F, 94.3F, 98.9F, 99.0F, 117.7F, 117.8F),
            floatArrayOf(94.6F, 94.7F, 99.4F, 99.5F, 118.3F, 118.4F),
            floatArrayOf(95.1F, 95.2F, 99.8F, 99.9F, 118.9F, 119.0F),
            floatArrayOf(95.2F, 95.3F, 100.0F, 100.1F, 119.1F, 119.2F),
            floatArrayOf(95.6F, 95.7F, 100.4F, 100.5F, 119.7F, 119.8F),
            floatArrayOf(96.0F, 96.1F, 100.9F, 101.0F, 120.3F, 120.4F),
            floatArrayOf(96.4F, 96.5F, 101.3F, 101.4F, 120.9F, 121.0F),
            floatArrayOf(96.9F, 97.0F, 101.8F, 101.9F, 121.5F, 121.6F),
            floatArrayOf(97.3F, 97.4F, 102.2F, 102.3F, 122.0F, 122.1F),
            floatArrayOf(97.7F, 97.8F, 102.6F, 102.7F, 122.6F, 122.7F),
            floatArrayOf(98.1F, 98.2F, 103.1F, 103.2F, 123.2F, 123.3F),
            floatArrayOf(98.5F, 98.6F, 103.5F, 103.6F, 123.7F, 123.8F),
            floatArrayOf(98.9F, 99.0F, 103.9F, 104.0F, 124.3F, 124.4F),
            floatArrayOf(99.3F, 99.4F, 104.4F, 104.5F, 124.8F, 124.9F)
        )
    }
}