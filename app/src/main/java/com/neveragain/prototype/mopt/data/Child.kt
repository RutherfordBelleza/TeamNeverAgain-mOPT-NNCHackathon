package com.neveragain.prototype.mopt.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "child_table")
data class Child(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fullName: String,
    val nameOfCaregiver: String,
    val contactOfCaregiver: String,
    val address: String,
    val sex: String,
    val isIndigenousPreschoolChild: String,
    val dateOfBirth: String,
    val dateOfWeighing: String,
    val weight: Double,
    val height: Double
)