package com.example.robertandroidcourse2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.robertandroidcourse2021.databinding.FragmentContactListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
        CoroutineScope(Dispatchers.IO).launch {
            val service = (activity as? ServiceProvider)?.getService()
            val contacts = service?.getContactList()
            withContext(Dispatchers.Main) {
                val contact = contacts?.get(0)
                with(binding.contactImNaPh) {
                    if (contact != null) {
                        contactImage.setImageResource(contact.imageResId)
                    }
                    if (contact != null) {
                        name.text = contact.name
                    }
                    if (contact != null) {
                        contactNumber.text = contact.numberOne
                    }
                }
                binding.contactLayout.setOnClickListener {
                    if (contact != null) {
                        (activity as? MainActivity)?.onContactSelected(contact)
                    }
                }
            }
        }


    }
}
