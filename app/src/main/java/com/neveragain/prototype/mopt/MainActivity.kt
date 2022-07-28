package com.neveragain.prototype.mopt

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.neveragain.prototype.mopt.calculations.*
import com.neveragain.prototype.mopt.data.Child
import com.neveragain.prototype.mopt.data.ChildDatabase
import com.neveragain.prototype.mopt.databinding.ActivityMainBinding
import com.neveragain.prototype.mopt.rdf.document.ExportablePdf
import com.opencsv.CSVWriter
import com.quinnpiling.quinn.rdf.RdfCell
import com.quinnpiling.quinn.rdf.RdfConstants
import com.quinnpiling.quinn.rdf.RdfDocument
import com.quinnpiling.quinn.rdf.RdfManager
import kotlinx.coroutines.launch
import java.io.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val selff = this

    companion object {
        private const val STORAGE_PERMISSION_CODE = 200
    }

    // create an action bar button
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menutest, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // handle button activities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.action_email) {
            if (checkPermission()) {
                exportData()
            } else {
                requestPermission()
            }
        } else if (id == R.id.action_deleteall) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            lifecycleScope.launch {
                ChildDatabase.getDatabase(selff).childDao().deleteAll()
            }
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete All Children Data")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "MOPT"
//        val navHostFragment =
//        supportFragmentManager.findFragmentById(com.neveragain.prototype.mopt.R.id.fragmentContainerView) as NavHostFragment
//        val navController = navHostFragment.navController
//        setupActionBarWithNavController(navController)
    }

    private fun exportData() {
        lifecycleScope.launch {
            val childTable = ChildDatabase.getDatabase(selff).childDao().selectAll()
            val pdfFile = buildPDF(childTable)
            val csvFile = buildExcelFile(childTable)

            val tempUri = FileProvider.getUriForFile(
                selff,
                applicationContext.packageName + ".fileprovider",
                pdfFile
            )
            val tempUri2 = FileProvider.getUriForFile(
                selff,
                applicationContext.packageName + ".fileprovider",
                csvFile
            )

            val emailIntent = Intent(Intent.ACTION_SEND_MULTIPLE)
            emailIntent.type = "plain/text"

            val uris = ArrayList<Uri>()

            if (tempUri != null && tempUri2 != null) {
                uris.add(tempUri)
                uris.add(tempUri2)
                emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
            }
            startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        }
    }

    private fun buildExcelFile(childTable: List<Child>): File {
        val fileNameWithExtension = "csv_output.csv"
        val csvFile = File(
            applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            fileNameWithExtension
        )
        val writer = CSVWriter(FileWriter(csvFile))
        val data: ArrayList<Array<String>> = ArrayList()
        data.add(
            arrayOf(
                "ID",
                "Address",
                "Name of Mother/Caregiver",
                "Full Name of Child",
                "Indigenous Preschool Child?",
                "Sex",
                "Date of Birth",
                "Actual Date of Weighing",
                "Weight",
                "Height",
                "Age in Months",
                "Weight for Age Status",
                "Height for Age Status",
                "Weight for Length/Height"
            )
        )

        for (child in childTable) {

            val ageInMonths = DateCalculations.getMonthsBetweenDateStrings(
                child.dateOfBirth,
                child.dateOfWeighing
            )

            val wfa = OptCalculator.getWeightForAge(child.weight, ageInMonths, child.sex == "F")
            val wfaString = WeightForAgeValues.getStringEquivalent(wfa)

            val hfa = OptCalculator.getHeightForAge(child.height, ageInMonths, child.sex == "F")
            val hfaString = HeightForAgeValues.getStringEquivalent(hfa)

            val wflh = OptCalculator.getWeightForHeight(
                child.height,
                child.weight,
                ageInMonths,
                child.sex == "F"
            )
            val wflhString = WeightForHeightValues.getStringEquivalent(wflh)

            data.add(
                arrayOf(
                    child.id.toString(),
                    child.address,
                    child.nameOfCaregiver,
                    child.fullName,
                    child.isIndigenousPreschoolChild,
                    child.sex,
                    child.dateOfBirth,
                    child.dateOfWeighing,
                    child.weight.toString(),
                    child.height.toString(),
                    ageInMonths.toString(),
                    wfaString,
                    hfaString,
                    wflhString
                )
            )
        }

        writer.writeAll(data)
        writer.close();

        return csvFile

    }

    private fun buildPDF(childTable: List<Child>): File {
        val fileNameWithExtension = "pdf_output.pdf"
        val pdfFile = File(
            applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            fileNameWithExtension
        )
        val output = FileOutputStream(pdfFile)
        val document = RdfDocument(
            output,
            RdfConstants.PAGE_HEIGHT_LONG_BOND,
            RdfConstants.PAGE_WIDTH_LONG_BOND
        )
        ExportablePdf.addDataTable(childTable, document)
        document.close()

        return pdfFile

    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11
            try {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                val uri = Uri.fromParts("package", this.packageName, null)
                intent.data = uri
                storageActivityResultLauncher.launch(intent)
            } catch (e: Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                storageActivityResultLauncher.launch(intent)
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                STORAGE_PERMISSION_CODE
            )
        }
    }

    private fun checkPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            val write = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            val read = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            write == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty()) {
                val write = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val read = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (write && read) {
                    exportData()
                }
            }
        }
    }

    private val storageActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                //Android R
                if (Environment.isExternalStorageManager()) {
                    exportData()
                } else {
                    //DENIED
                }
            } else {
                //Below Android R
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    STORAGE_PERMISSION_CODE
                )
            }
        }

}