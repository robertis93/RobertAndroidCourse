package com.example.robertandroidcourse2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), ContactListFragment.onContactSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.root_layout, ContactListFragment.newInstance(), "dogList")
                    .commit()
        }
    }

    override fun onDogSelected(contactModel: ContactModel) {
        val detailsFragment =
                ContactDetailsFragment.newInstance(contactModel)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_layout, detailsFragment, "dogDetails")
                .addToBackStack(null)
                .commit()
    }

}