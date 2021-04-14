package com.example.robertandroidcourse2021

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder


class ContactService : Service() {

    private val binder = ContactServiceBinder()


    override fun onBind(intent: Intent): IBinder {
        return binder

    }

    inner class ContactServiceBinder : Binder() {
        suspend fun getService(): ContactService = this@ContactService

    }

    fun getContactList(): List<ContactModel> {
        return getFakeContacts()
    }

    fun getContactDetails(id: Int): ContactModel {
        return getFakeContacts().find { it.id == id }!!
    }
}

