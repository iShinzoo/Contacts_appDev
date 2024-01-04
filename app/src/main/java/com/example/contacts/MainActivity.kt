package com.example.contacts

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Contacts
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
// declaring Global variable
    private lateinit var rv : RecyclerView
    private lateinit var fb : FloatingActionButton
    private lateinit var EdtName : EditText
    private lateinit var EdtPhone : EditText
    private lateinit var ImagePreview : ImageView
    private lateinit var btnAddContact : Button
    private lateinit var btnChooseImage : Button
    private lateinit var Adapter : ContactAdapter
    private lateinit var dialog: Dialog
    val ContactListOf = mutableListOf<ContactList>() // creating list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv)
        fb = findViewById(R.id.fb)
        // creating Adapter
        Adapter = ContactAdapter(ContactListOf)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = Adapter



        fb.setOnClickListener(){
            showDialog()
        }
    }


private fun showDialog(){
    dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setContentView(R.layout.custom_layout)

    EdtName = dialog.findViewById(R.id.edt1)
    EdtPhone = dialog.findViewById(R.id.edt2)
    ImagePreview = dialog.findViewById(R.id.image_preview)
    btnAddContact = dialog.findViewById(R.id.btn_add_contact)
    btnChooseImage = dialog.findViewById(R.id.btn_choose_image)

    btnChooseImage.setOnClickListener(){
        val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent,101)
    }
    dialog.show()
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK){
            ImagePreview.visibility = View.VISIBLE
            ImagePreview.setImageURI(data?.data)


            btnAddContact.setOnClickListener(){
                val Edtname = EdtName.text.toString()
                val Edtphone = EdtPhone.text.toString()
                val imagePreview = data?.data

                val contact = ContactList(
                    Heading = Edtname,
                    SubHeading = Edtphone,
                    ImageRes = imagePreview!!
                )
                ContactListOf.add(contact)
                Adapter.notifyDataSetChanged()
                dialog.dismiss()
        }
        }
    }
}