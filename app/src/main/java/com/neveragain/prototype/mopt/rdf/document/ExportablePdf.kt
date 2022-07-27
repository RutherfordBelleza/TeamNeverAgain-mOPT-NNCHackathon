package com.neveragain.prototype.mopt.rdf.document

import com.neveragain.prototype.mopt.calculations.*
import com.neveragain.prototype.mopt.data.Child
import com.quinnpiling.quinn.rdf.RdfCell
import com.quinnpiling.quinn.rdf.RdfDocument
import com.quinnpiling.quinn.rdf.RdfManager
import com.quinnpiling.quinn.rdf.RdfTable

class ExportablePdf {

    companion object {
        fun addDataTable(childrenList: List<Child>, document: RdfDocument) {
            val colWidths =
                arrayListOf(36F, 108F, 72F, 72F, 36F, 36F, 72F, 72F, 36F, 36F, 72F, 72F, 72F, 72F)

            val recordedTable = RdfTable(14, colWidths, RdfManager.borderedTableFormat)

            recordedTable.addCell(RdfCell("ID", RdfManager.customDarkerFont))
            recordedTable.addCell(RdfCell("Address", RdfManager.customDarkerFont))
            recordedTable.addCell(
                RdfCell(
                    "Name of Mother/Caregiver",
                    RdfManager.customDarkerFontSmaller
                )
            )
            recordedTable.addCell(RdfCell("Full Name of Child", RdfManager.customDarkerFont))
            recordedTable.addCell(
                RdfCell(
                    "Indigenous Preschool Child?",
                    RdfManager.customDarkerFontTiny
                )
            )
            recordedTable.addCell(RdfCell("Sex", RdfManager.customDarkerFont))
            recordedTable.addCell(RdfCell("Date of Birth", RdfManager.customDarkerFont))
            recordedTable.addCell(RdfCell("Actual Date of Weighing", RdfManager.customDarkerFont))
            recordedTable.addCell(RdfCell("Weight", RdfManager.customDarkerFont))
            recordedTable.addCell(RdfCell("Height", RdfManager.customDarkerFont))
            recordedTable.addCell(RdfCell("Age in Months", RdfManager.customDarkerFont))
            recordedTable.addCell(RdfCell("Weight for Age Status", RdfManager.customDarkerFont))
            recordedTable.addCell(RdfCell("Height for Age Status", RdfManager.customDarkerFont))
            recordedTable.addCell(RdfCell("Weight for Length/Height", RdfManager.customDarkerFont))

            for (child in childrenList) {
                recordedTable.addCell(RdfCell(child.id.toString(), RdfManager.customDarkFont))
                recordedTable.addCell(RdfCell(child.address, RdfManager.customDarkFont))
                recordedTable.addCell(RdfCell(child.nameOfCaregiver, RdfManager.customDarkFont))
                recordedTable.addCell(RdfCell(child.fullName, RdfManager.customDarkFont))

                recordedTable.addCell(
                    RdfCell(
                        child.isIndigenousPreschoolChild,
                        RdfManager.customDarkFont
                    )
                )
                recordedTable.addCell(RdfCell(child.sex, RdfManager.customDarkFont))
                recordedTable.addCell(RdfCell(child.dateOfBirth, RdfManager.customDarkFont))
                recordedTable.addCell(RdfCell(child.dateOfWeighing, RdfManager.customDarkFont))

                recordedTable.addCell(RdfCell(child.weight.toString(), RdfManager.customDarkFont))
                recordedTable.addCell(RdfCell(child.height.toString(), RdfManager.customDarkFont))

                val ageInMonths = DateCalculations.getMonthsBetweenDateStrings(
                    child.dateOfBirth,
                    child.dateOfWeighing
                )
                recordedTable.addCell(RdfCell(ageInMonths.toString(), RdfManager.customDarkFont))

                val wfa = OptCalculator.getWeightForAge(child.weight, ageInMonths, child.sex == "F")
                val wfaString = WeightForAgeValues.getStringEquivalent(wfa)
                recordedTable.addCell(RdfCell(wfaString, RdfManager.customDarkFont))

                val hfa = OptCalculator.getHeightForAge(child.height, ageInMonths, child.sex == "F")
                val hfaString = HeightForAgeValues.getStringEquivalent(hfa)
                recordedTable.addCell(RdfCell(hfaString, RdfManager.customDarkFont))

                val wflh = OptCalculator.getWeightForHeight(
                    child.height,
                    child.weight,
                    ageInMonths,
                    child.sex == "F"
                )
                val wflhString = WeightForHeightValues.getStringEquivalent(wflh)
                recordedTable.addCell(RdfCell(wflhString, RdfManager.customDarkFont))
            }
            document.addTable(recordedTable)
        }
    }
}