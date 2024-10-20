# Medical Info Application

## Creating Project

## Creating Main Activity Layout
1. Pada activity_main.xml atur layout seperti berikut:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".main.MainActivity">

    <FrameLayout
        android:id="@+id/fragment_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
Pada main activity kita berikan frame layout yang akan menjadi wadah dari fragment main dan fragment input.

## Creating MedicalInfo Data class
1. Buat package baru dengan nama common di package utama lalu buat Data class dengan nama MedicalInfo
2. Tambahkan plugin Parcelize pada build.gradle module app seperti berikut:
```
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.plugin.parcelize")
}
```
3. Pada berkas MedicalInfo Buat data class seperti berikut:

```kotlin
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
```
Data class yang kita buat akan menampung data medical info yang terdiri dari name, address, phoneNumber. Untuk id opsional pada kasus ini kita memanfaatkan fungsi UUID. Pada data class tersebut kita juga memberikan default value untuk masing-masing variabel.

## Creating Item Medical Info CardView
1. Pada folder layout buat new layout resource file dan beri nama item_medical_info
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_marginHorizontal="16dp"
    android:layout_marginVertical="4dp"
    app:cardCornerRadius="16dp">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/cl_parent"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:id="@+id/tv_hospital_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            tools:text="Hospital"
            android:layout_margin="16dp"
            android:textSize="18sp"
            android:textStyle="bold"
            />

        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:id="@+id/tv_hospital_address"
            app:layout_constraintStart_toStartOf="@id/tv_hospital_name"
            app:layout_constraintTop_toBottomOf="@id/tv_hospital_name"
            app:layout_constraintEnd_toEndOf="parent"
            tools:text="Jl. Jendral Sudirman No 50 RT 005 RW 005, Semanggi, Jakarta Selatan"
            android:layout_marginTop="16dp"
            android:layout_marginEnd="16dp"
            />

        <TextView
            android:layout_width="wrap_content"
            android:id="@+id/tv_hospital_phone_number"
            app:layout_constraintTop_toBottomOf="@id/tv_hospital_address"
            app:layout_constraintStart_toStartOf="@id/tv_hospital_name"
            app:layout_constraintBottom_toBottomOf="parent"
            android:layout_marginVertical="16dp"
            tools:text="+62 812 3456 7890"
            android:layout_height="wrap_content" />

    </androidx.constraintlayout.widget.ConstraintLayout>


</androidx.cardview.widget.CardView>
```

## Creating Main Fragment - Layout
Main Fragment ini akan menjadi halaman utama yang berisikan list dari Medical Info.
1. Pada berkas fragment_main.xml buat layout yang terdiri dari title, recylerview dan floating action button
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/main_background"
    tools:context=".main.MainFragment">


    <TextView
        android:id="@+id/tv_greeting"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        android:text="Great Day!"
        android:textColor="@color/white"
        android:textSize="32sp"
        android:textStyle="bold"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv_medical_info"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        android:layout_marginTop="16dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_greeting"
        tools:itemCount="5"
        tools:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        tools:listitem="@layout/item_medical_info"
        />

    <!-- Mengambil resource drawable icon dari android    -->
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab_add_data"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:backgroundTint="@color/fab_color"
        android:src="@android:drawable/ic_input_add"
        app:tint="@color/white"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_margin="16dp"
        />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## Creating DiffUtilCallBack
1. Selanjutnya pada package main, buat class dengan nama DiffUtilCallback seperti berikut:
```kotlin
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
```
DiffUtilCallback ini akan digunakan pada recylerView yang nantinya akan berfungsi agar list item yang ditampilkan sesuai dengan perubahan dan tidak di render ulang semua dari awal lagi.

## Creating Medical Info Listener
1. Pada package main, buat interface dengan name MedicalInfoListener
```kotlin
package com.example.medicalinfo.main

interface MedicalInfoListener {
    fun onPhoneNumberClicked(phoneNumber: String)
}
```
interface tersebut akan kita gunakan pada recylerView yakni dibagian phone number, agar nantinya ketika phone number di klik, maka akan melakukan implicit intent dial phone.

## Creating Medical Info Adapter
1. Pada package main, buat class dengan nama MedicalInfoAdapter

```kotlin
package com.example.medicalinfo.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalinfo.R
import com.example.medicalinfo.common.MedicalInfo

class MedicalInfoAdapter(val listener: MedicalInfoListener): ListAdapter<MedicalInfo, MedicalInfoAdapter.MedicalInfoViewHolder>(DiffUtilCallback()) {

    inner class MedicalInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MedicalInfo){
            val hospitalNameView = itemView.findViewById<TextView>(R.id.tv_hospital_name)
            val hospitalAddressView = itemView.findViewById<TextView>(R.id.tv_hospital_address)
            val phoneNumberView = itemView.findViewById<TextView>(R.id.tv_hospital_phone_number)

            hospitalNameView.text = item.name
            hospitalAddressView.text = item.address
            phoneNumberView.text = item.phoneNumber

            phoneNumberView.setOnClickListener {
                listener.onPhoneNumberClicked(phoneNumberView.text.toString())
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medical_info, parent, false)
        return MedicalInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicalInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(itemList: List<MedicalInfo>) {
        submitList(itemList)
    }

    fun addItems(newItems: List<MedicalInfo>) {
        submitList(currentList + newItems)
    }
}
```
Pada MedicalInfoAdapter, kita membuat inner class MedicalInfoViewHolder class ini berfungsi untuk menampung view dari layout list item medical info yang telah kita buat.
Di view holder tersebut kita juga memberikan nilai untuk view tersebut yakni data dari MedicalInfo. Lalu pada phoneNumberView kita juga berikan setOnClickListener dimana kita memberikan function onPhoneNumberClicked dari MedicalInfoListener.
Selanjutnya, kita meng-override onCreateViewHolder, function ini akan menghubungkan layout list medical info dan memberikan informasi tersebut ke view holder. 
Lalu kita juga meng-override function onBindViewHolder dimana kita memanggil fungsi bind dari view holder dan memberikan nilai sesuai posisi list item.
Terdapat juga fungsi setData, fungsi ini akan menambahkan data list dari MedicalInfo ke adapter. Terakhir function addItems berfungsi untuk menambahkan list medical info baru dengan data list medical info yang sudah ada.

## Creating Input Fragment - Layout
1. Buat package baru dengan nama input, kemudian buat fragment baru dengan nama InputFragment
2. Pada bagian fragment_input.xml atur layout seperti berikut:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".input.InputFragment">

    <ImageView
        android:id="@+id/iv_back_arrow"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        android:src="@drawable/baseline_arrow_back_24"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/tv_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Add New List"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintStart_toStartOf="@id/iv_back_arrow"
        app:layout_constraintTop_toBottomOf="@id/iv_back_arrow" />

    <TextView
        android:id="@+id/tv_hospital_name_label"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Name"
        app:layout_constraintStart_toStartOf="@id/tv_title"
        app:layout_constraintTop_toBottomOf="@id/tv_title" />

    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/input_hospital_name"
        android:layout_width="match_parent"
        android:layout_height="42dp"
        android:layout_marginHorizontal="16dp"
        android:layout_marginTop="4dp"
        android:background="@drawable/textfield_background_shape"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_hospital_name_label" />

    <TextView
        android:id="@+id/tv_hospital_address_label"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Address"
        app:layout_constraintStart_toStartOf="@id/input_hospital_name"
        app:layout_constraintTop_toBottomOf="@id/input_hospital_name" />

    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/input_hospital_address"
        android:layout_width="match_parent"
        android:layout_height="68dp"
        android:layout_marginHorizontal="16dp"
        android:layout_marginTop="4dp"
        android:background="@drawable/textfield_background_shape"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_hospital_address_label" />

    <TextView
        android:id="@+id/tv_hospital_phone_label"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Phone Number"
        app:layout_constraintStart_toStartOf="@id/input_hospital_address"
        app:layout_constraintTop_toBottomOf="@id/input_hospital_address" />

    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/input_hospital_phone"
        android:layout_width="match_parent"
        android:layout_height="42dp"
        android:layout_marginHorizontal="16dp"
        android:layout_marginTop="4dp"
        android:background="@drawable/textfield_background_shape"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_hospital_phone_label" />

    <com.google.android.material.button.MaterialButton
        android:id="@+id/btn_save"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        android:text="Save"
        app:cornerRadius="8dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@id/input_hospital_phone" />

    <com.google.android.material.button.MaterialButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/btn_discard"
        android:text="Discard"
        app:layout_constraintTop_toTopOf="@id/btn_save"
        app:layout_constraintBottom_toBottomOf="@id/btn_save"
        app:layout_constraintEnd_toStartOf="@id/btn_save"
        android:layout_marginEnd="16dp"
        app:cornerRadius="8dp"
        android:backgroundTint="@color/white"
        android:textColor="@color/main_background"
        app:strokeColor="@color/main_background"
        app:strokeWidth="1dp"


        />


</androidx.constraintlayout.widget.ConstraintLayout>
```
Pada layout di atas, kita membuat layout yang terdiri dari tombol back, title, edit Text serta button save & discard.

## Navigation
Kita akan menerapkan navigation dengan menggunakan navigation graph. Pada navigasi tersebut kita juga akan mengimplementasikan pengiriman data antar fragment.

### Creating nav graph
1. Pada folder res buat folder baru dengan nama navigation lalu buat berkas dengan nama nav_graph.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/mainFragment">

    <fragment
        android:id="@+id/mainFragment"
        android:name="com.example.medicalinfo.main.MainFragment"
        android:label="fragment_main"
        tools:layout="@layout/fragment_main" >

        <!-- Data argumen to pass to the fragment  -->
        <argument
            android:name="medicalInfoData"
            app:argType="com.example.medicalinfo.common.MedicalInfo" />

        <action
            android:id="@+id/action_mainFragment_to_inputFragment"
            app:destination="@id/inputFragment" />
    </fragment>

    <fragment
        android:id="@+id/inputFragment"
        android:name="com.example.medicalinfo.input.InputFragment"
        android:label="fragment_input"
        tools:layout="@layout/fragment_input" />
</navigation>
```
Pada code di atas, kita mengatur aksi perpindahan dari fragmet main ke fragment input yang ditandai oleh tag action. pada tag tersebut kita memberikan id "@+id/action_mainFragment_to_inputFragment" yakni aksi fragment main ke fragment input.
Lalu kita juga memberikan tag argumen dan memberikan nama argumen tersebut "medicalInfoData" dengan type argumen MedicalInfo data class.
Dapat dilihat juga pada navigation tersebut kita menetapkan start destination ke fragment main.

### Change Frame Layout in main Activity
1. Pada activity_main.xml sesuaikan frame layout menjadi Fragment Container view seperti berikut:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".main.MainActivity">

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/fragment_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:name="androidx.navigation.fragment.NavHostFragment"
        app:defaultNavHost = "true"
        app:navGraph = "@navigation/nav_graph"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
Pada fragment container view, kita mendefinisikan nama serta memberikan attribut navGraph dan menetapkan defaultNavHost.

### Update Main Fragment 
1. Pada berkas MainFragment tulis code seperti berikut:
```kotlin
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
    //menginisialisasi recylerView, fab & adapter
    private lateinit var rvMedicalInfo : RecyclerView
    private lateinit var fabAddData : FloatingActionButton
    private lateinit var adapter : MedicalInfoAdapter

    //menghubungkan layout fragment_main ke MainFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //memberikan nilai ke variabel
        initViews()
        //mengatur layout adapter & memberikan data ke adapter
        initMedicalInfoList()
        //mengambil data dari input fragment
        getDataFromInputFragment()
        //menuju input fragment serta mengirimkan data
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

    //setting layout & give data to adapter
    private fun initMedicalInfoList(){
        rvMedicalInfo?.layoutManager = LinearLayoutManager(context)
        rvMedicalInfo?.adapter = adapter
        adapter.setData(generateDummyData())
    }

    //create dummy data for adapter
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

    //override function from listener and give intent dial
    override fun onPhoneNumberClicked(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${phoneNumber}")
        startActivity(intent)
    }
}
```
Penjelasan code di atas:
1. Pada MainFragment kita menginisialiasi beberapa variabel yakni, rvMedicalInfo, fabAddData & adapter.
2. Pada onCreateView kita menghubungkan layout fragment_main ke MainFragment
3. Pada onViewCreated kita memanggil fungsi - fungsi yakni:
- initViews - fungsi ini memberikan nilai ke variabel yang telah kita buat sebelumnya.
- initMedicalInfoList - fungsi ini akan mengatur layout dari adapter serta memberikan data dummy ke adapter
- getDataFromInputFragment - fungsi ini akan mengambil data dari InputFragment yang kita beri nama "resultKey", nama ini juga kita definisikan di InputFragment. Data yang telah diperoleh kita masukan ke adapter dengan fungsi setData.
Selain itu kita juga memberikan setOnClickListener pada fabAddData, pada listener tersebut kita mengirimkan bundle berupa data currentList dari adapter. Bundle tersebut kita berikan nama sesuai argumen yang kita buat di nav_graph.
4. Terdapat function generateDummyData untuk mengenerate dummy data medicalInfo yang kita berikan ke adapter.
5. Pada fungsi listener yakni onPhoneNumberClicked kita berikan Intent action dial, sehingga ketika nomor hp diklik maka akan melakukan aksi phone dial. 

### Update Input Fragment 
1. Pada berkas InputFragment tulis code seperti berikut:
```kotlin
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

    //menghubungkan views ke variabel
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
        //memberikan fungsionalitas btnDiscard
        btnDiscard?.setOnClickListener {
            discardData()
        }
        //memberikan fungsionalitas btnSave
        btnSave?.setOnClickListener {
            if(isEntryValid()){
                saveData()
            } else {
                showToast("Mohon lengkapi data terlebih dahulu")
            }
        }
        //memberikan fungsionalitas btnBack
        btnBack?.setOnClickListener {
            backToMainFragment()
        }
        //menghandle back pressed android
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

    //menghapus input dari masing-masing editText
    private fun discardData() {
        inputNameField?.setText("")
        inputAddressField?.setText("")
        inputPhoneField?.setText("")
    }

    //menyimpan data dari editText dan menambahkan data ke medicalInfoData
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

    //melakukan pengecekan agar tidak ada input yang kosong
    private fun isEntryValid(): Boolean {
        return !(inputNameField?.text.toString().isBlank() ||
                inputAddressField?.text.toString().isBlank() ||
                inputPhoneField?.text.toString().isBlank())
    }

    //memunculkan pesan toast
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
```
Penjelasan code di atas:
1. Pada Input Fragment kita menghubungkan view dan memberikan nilai ke variabel.
2. Pada onCreate kita mengambil data argumen yang dikirimkan oleh MainFragment.
3. Pada onCreateView kita menghubungkan layout dari fragment_input ke InputFragment
4. Pada onViewCreated kita melakukan beberapa hal yakni:
- Memberikan fungsi discardData pada btnDiscard
- Memberikan fungsi saveData pada btnSave
- Memberikan fungsi backToMainFragment ke btnBack
- Memanggil fungsi handleOnBackPressed
5. Fungsi discardData akan memberikan nilai kosong pada masing-masing EditText Input
6. Fungsi saveData akan menyimpan data input ke MedicalInfo data class kemudian menambahkan nya ke variabel medicalInfoData. Fungsi ini akan dijalankan jika semua input Valid. Yang kita cek dengan fungsi isEntryValid.
7. Fungsi backToMainFragment akan mengirimkan kembali data ke MainFragment yang mana data tersebut kita berikan nama "resultKey". Selain itu fungsi akan menavigasikan kita kembali ke MainFragment.
8. Fungsi showToast akan menampilkan pesan Toast sesuai dengan message yang kita berikan.
