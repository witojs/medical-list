package com.example.medicalinfo.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.medicalinfo.R
import com.example.medicalinfo.common.MedicalInfo
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class InputFragment : Fragment() {

    private val btnBack by lazy { view?.findViewById<ImageView>(R.id.iv_back_arrow) }
    private val inputNameField by lazy { view?.findViewById<TextInputEditText>(R.id.input_hospital_name) }
    private val inputAddressField by lazy { view?.findViewById<TextInputEditText>(R.id.input_hospital_address) }
    private val inputPhoneField by lazy { view?.findViewById<TextInputEditText>(R.id.input_hospital_phone) }
    private val btnSave by lazy { view?.findViewById<MaterialButton>(R.id.btn_save) }
    private val btnDiscard by lazy { view?.findViewById<MaterialButton>(R.id.btn_discard) }

    private val medicalInfoData = mutableListOf<MedicalInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getting argument from main fragment
        medicalInfoData.addAll(
            arguments?.getParcelableArray("medicalInfoData")
                ?.toMutableList() as MutableList<MedicalInfo>
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDiscard?.setOnClickListener {
            discardData()
        }
        btnSave?.setOnClickListener {
            if(isEntryValid()){
                saveData()
            } else {
                showToast("Mohon lengkapi data terlebih dahulu")
            }
        }
        btnBack?.setOnClickListener {
            backToMainFragment()
        }
        handleOnBackPressed()
    }

    //handle back button
    private fun handleOnBackPressed(){
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                backToMainFragment()
            }
        }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    private fun discardData() {
        inputNameField?.setText("")
        inputAddressField?.setText("")
        inputPhoneField?.setText("")
    }

    private fun saveData() {
        val newDataEntry = MedicalInfo(
            name = inputNameField?.text.toString(),
            address = inputAddressField?.text.toString(),
            phoneNumber = inputPhoneField?.text.toString()
        )
        try {
            medicalInfoData.add(newDataEntry)
            discardData()
            showToast("Data berhasil ditambahkan")
        } catch (e: Exception) {
            showToast("Terjadi Kesalahan!")
        }
    }

    private fun isEntryValid(): Boolean {
        return !(inputNameField?.text.toString().isBlank() ||
                inputAddressField?.text.toString().isBlank() ||
                inputPhoneField?.text.toString().isBlank())
    }

    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    //go to previous fragment and send data
    private fun backToMainFragment(){
        val resultData = medicalInfoData
        findNavController().previousBackStackEntry?.savedStateHandle?.set("resultKey", resultData)
        findNavController().navigateUp()
    }
}