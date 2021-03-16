package com.example.robertandroidcourse2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, ContactListFragment(), "List")
                .commit()
        }
    }

    fun onContactSelected(contact: ContactModel) {
        val fragmentDetails = ContactDetailsFragment()
        val bundle = Bundle()
        bundle.putInt("contactId", contact.id)
        fragmentDetails.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, fragmentDetails, "Details")
            .addToBackStack(null)
            .commit()
    }

}