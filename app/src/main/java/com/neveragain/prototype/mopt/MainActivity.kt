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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.neveragain.prototype.mopt.data.Child
import com.neveragain.prototype.mopt.data.ChildDatabase
import com.neveragain.prototype.mopt.databinding.ActivityMainBinding
import com.neveragain.prototype.mopt.rdf.document.ExportablePdf
import com.quinnpiling.quinn.rdf.RdfConstants
import com.quinnpiling.quinn.rdf.RdfDocument
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val selff = this

    companion object {
        private const val STORAGE_PERMISSION_CODE = 200
    }

    // create an action bar button
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        menuInflater.inflate(com.neveragain.prototype.mopt.R.menu.menutest, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // handle button activities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == com.neveragain.prototype.mopt.R.id.action_email) {
            if (checkPermission()) {
                exportData()
            } else {
                requestPermission()
            }
        } else if (id == com.neveragain.prototype.mopt.R.id.action_deleteall) {
            lifecycleScope.launch {
                ChildDatabase.getDatabase(selff).childDao().deleteAll()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(com.neveragain.prototype.mopt.R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    private fun exportData() {
        lifecycleScope.launch {
            val childTable = ChildDatabase.getDatabase(selff).childDao().selectAll()
            buildPDF(childTable)
        }
    }

//    fun copyFile(input: InputStream?, output: OutputStream?): Boolean {
//        try {
//            val buf = ByteArray(1024)
//            var len: Int
//            if (input != null) {
//                while (input.read(buf).also { len = it } > 0) {
//                    output?.write(buf, 0, len)
//                }
//            }
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//            return false
//        } finally {
//            try {
//                if (input != null) input.close()
//                if (output != null) output.close()
//            } catch (e: java.lang.Exception) {
//            }
//        }
//        return true
//    }
//
//    private fun buildExcelFile(is500: Boolean) {
//        val fileNameWithExtension = "excel_output.xlsx"
//        val tempFileNameWithExtension = "excel_temp.xlsx"
//
//        val excelFile = File(
//            applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
//            fileNameWithExtension
//        )
//        val tempExcel = File(
//            applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
//            tempFileNameWithExtension
//        )
//        val tempOutput = FileOutputStream(tempExcel)
//
//        val oPTFile = if (is500) {
//            resources.openRawResource(
//                resources.getIdentifier(
//                    "opt_500people",
//                    "raw",
//                    packageName
//                )
//            )
//        } else {
//            resources.openRawResource(
//                resources.getIdentifier(
//                    "opt_1000people",
//                    "raw",
//                    packageName
//                )
//            )
//        }
//        copyFile(oPTFile, tempOutput)
//        oPTFile.close()
//        tempOutput.close()
//
//        val inputFile = FileInputStream(tempExcel)
//
//        val workbook = XSSFWorkbook(inputFile) //get workbook
//        val sheet = workbook.getSheet("Sheet1") // declare sheet name
//
//        val bookData = Array(500) {
//            arrayOfNulls<String>(
//                13
//            )
//        }
//
//        for ((j, Book) in bookData.withIndex()) {
//            for ((i, Item) in Book.withIndex()) {
//                println(Item)
//                val cell = sheet.getRow(14 + j).getCell(1 + i)
//                cell.setCellType(CellType.STRING)
//                cell.setCellValue(Item)
//            }
//        }
//
//        val output = FileOutputStream(excelFile)
//        workbook.write(output)
//
//        output.close()
//        workbook.close()
//    }

    private fun buildPDF(childTable: List<Child>) {
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

        val tempUri = FileProvider.getUriForFile(this, applicationContext.packageName + ".fileprovider", pdfFile)

        val emailIntent = Intent(Intent.ACTION_SEND);
        emailIntent.type = "plain/text";
        if (tempUri != null) {
            emailIntent.putExtra(Intent.EXTRA_STREAM, tempUri);
        }
        this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
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
                } else {

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