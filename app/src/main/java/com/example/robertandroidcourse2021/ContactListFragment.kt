package com.example.robertandroidcourse2021

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.robertandroidcourse2021.databinding.FragmentContactListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ContactListFragment : Fragment() {
    lateinit var binding: FragmentContactListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun getNewInstance() = ContactListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isBound = (activity as ServiceProvider).getBound()
        if (isBound) {
            lifecycleScope.launch(IO) {
                val service = (activity as? ServiceProvider)?.getService()
                val contacts = service?.getContactList()
                val contact = contacts?.get(0) ?: return@launch
                withContext(Main) {
                    with(binding.contactImNaPh) {
                        contactImage.setImageResource(contact.imageResId)
                        name.text = contact.name
                        contactNumber.text = contact.numberOne
                    }
                    binding.contactImNaPh.root.setOnClickListener {
                        (activity as? MainActivity)?.onContactSelected(contact)
                    }
                }
            }
        }
    }
}

