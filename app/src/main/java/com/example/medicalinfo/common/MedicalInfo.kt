package com.example.medicalinfo.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class MedicalInfo(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "Hospital",
    val address: String = "Jl. Jend. Sudirman No. 50, RT5/RW5, Semanggi, Jakarta Selatan",
    val phoneNumber: String = "+62 812-345-6789"
) : Parcelable
