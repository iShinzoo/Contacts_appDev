package com.example.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val ContactListOf : List<ContactList>
) : RecyclerView.Adapter<ContactAdapter.ContactViewholder>(){


    class ContactViewholder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val Image = itemView.findViewById<ImageView>(R.id.image)
        val heading = itemView.findViewById<TextView>(R.id.heading)
        val subHeading = itemView.findViewById<TextView>(R.id.sub_heading)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_res,parent,false)
        return ContactViewholder(view)
    }

    override fun getItemCount(): Int {
        return ContactListOf.size
    }

    override fun onBindViewHolder(holder: ContactViewholder, position: Int) {
        holder.Image.setImageURI(ContactListOf[position].ImageRes)
        holder.heading.text = ContactListOf[position].Heading
        holder.subHeading.text = ContactListOf[position].SubHeading
    }
}