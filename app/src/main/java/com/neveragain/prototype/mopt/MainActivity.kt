package com.neveragain.prototype.mopt

import android.R
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.neveragain.prototype.mopt.data.Child
import com.neveragain.prototype.mopt.data.ChildDatabase
import com.neveragain.prototype.mopt.databinding.ActivityMainBinding
import com.neveragain.prototype.mopt.rdf.document.ExportablePdf
import com.quinnpiling.quinn.rdf.RdfConstants
import com.quinnpiling.quinn.rdf.RdfDocument
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream


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
        val navHostFragment = supportFragmentManager.findFragmentById(com.neveragain.prototype.mopt.R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    private fun exportData() {
        lifecycleScope.launch {
            val childTable = ChildDatabase.getDatabase(selff).childDao().selectAll()
            buildPDF(childTable)
        }
    }

    private fun buildPDF(childTable: List<Child>) {
        val fileNameWithExtension = "output.pdf"
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