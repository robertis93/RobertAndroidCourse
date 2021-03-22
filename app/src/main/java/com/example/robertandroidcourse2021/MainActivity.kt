package com.example.robertandroidcourse2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ContactListFragment.getNewInstance()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, fragment, "List")
                .commit()
        }
    }

    fun onContactSelected(contact: ContactModel) {
        val fragment = ContactDetailsFragment.getNewInstance(contact.id)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, fragment, "Details")
            .addToBackStack(null)
            .commit()
    }

}