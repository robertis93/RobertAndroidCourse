package com.example.robertandroidcourse2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_contact_details.*


class ContactDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_contact_details, container,
            false
        )


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments!!.getInt("contactId")
        val contacts = getFakeContacts()
        val contact = contacts.find { it.id == id }!!

        contactImageDetail.setImageResource(contact.imageResId)
        nameDetail.text = contact.name
        contactNumberOne.text = contact.numberOne
        contactNumberTwo.text = contact.numberOne
        emailFirst.text = contact.emailFirst
        emailSecond.text = contact.emailSecond
        description.text = contact.description
    }

}
