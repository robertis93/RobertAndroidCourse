package com.example.robertandroidcourse2021

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity


interface ServiceProvider {
    fun getService(): ContactService
}

class MainActivity : AppCompatActivity(), ServiceProvider {

    companion object {
        var mBound: Boolean = false
    }

    override fun getService() = ContactService()

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as ContactService.ContactServiceBinder
            binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragment = ContactListFragment.getNewInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, fragment, "List")
                .commit()

            val intent = Intent(this, ContactService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        mBound = false
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