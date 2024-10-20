package com.example.medicalinfo.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalinfo.R
import com.example.medicalinfo.common.MedicalInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment(), MedicalInfoListener {
    private lateinit var rvMedicalInfo : RecyclerView
    private lateinit var fabAddData : FloatingActionButton
    private lateinit var adapter : MedicalInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initMedicalInfoList()
        getDataFromInputFragment()
        fabAddData?.setOnClickListener{
            //getting data from fragment argument
            val bundle = bundleOf("medicalInfoData" to adapter.currentList.toTypedArray())
            //navigate to input fragment and passing data
            findNavController().navigate(R.id.action_mainFragment_to_inputFragment, bundle)
        }
    }

    //initialize view & adapter
    private fun initViews(){
        view?.let {
            rvMedicalInfo = it.findViewById(R.id.rv_medical_info)
            fabAddData = it.findViewById(R.id.fab_add_data)
            adapter = MedicalInfoAdapter(this)
        }
    }

    private fun initMedicalInfoList(){
        rvMedicalInfo?.layoutManager = LinearLayoutManager(context)
        rvMedicalInfo?.adapter = adapter
        adapter.setData(generateDummyData())
    }

    private fun generateDummyData(): List<MedicalInfo>{
        return listOf(
            MedicalInfo(name = "RSAB Harapan Kita"),
            MedicalInfo(name = "RSUD Koja"),
            MedicalInfo(name = "RS Hermina Podomoro"),
        )
    }

    //getting data from input fragment
    private fun getDataFromInputFragment(){
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<List<MedicalInfo>>("resultKey")
            ?.observe(viewLifecycleOwner){ result ->
                adapter.setData(result)
            }
    }

    override fun onPhoneNumberClicked(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${phoneNumber}")
        startActivity(intent)
    }
}