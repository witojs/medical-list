package com.example.medicalinfo.main

import androidx.recyclerview.widget.DiffUtil
import com.example.medicalinfo.common.MedicalInfo

class DiffUtilCallback : DiffUtil.ItemCallback<MedicalInfo>() {
    override fun areItemsTheSame(oldItem: MedicalInfo, newItem: MedicalInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MedicalInfo, newItem: MedicalInfo): Boolean {
        return oldItem == newItem
    }
}