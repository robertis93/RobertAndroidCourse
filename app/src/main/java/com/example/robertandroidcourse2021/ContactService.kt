package com.example.robertandroidcourse2021

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.delay


class ContactService : Service() {

    private val binder = ContactServiceBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    inner class ContactServiceBinder : Binder() {
        fun getService(): ContactService = this@ContactService
    }

    suspend fun getContactList(): List<ContactModel> {

        return getFakeContacts()
    }

    suspend fun getContactDetails(id: Int): ContactModel? {
        delay(1000)
        return getFakeContacts().find { it.id == id }
    }
}

