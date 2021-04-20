package com.example.robertandroidcourse2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.robertandroidcourse2021.databinding.FragmentContactListBinding
import kotlinx.coroutines.launch


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
        if (MainActivity.mBound)
        lifecycleScope.launch {
            val service = (activity as? ServiceProvider)?.getService()
            val contacts = service?.getContactList()
            val contact = contacts?.get(0) ?: return@launch
            with(binding.contactImNaPh) {
                contactImage.setImageResource(contact.imageResId)
                name.text = contact.name
                contactNumber.text = contact.numberOne
            }
            binding.contactLayout.setOnClickListener {
                (activity as? MainActivity)?.onContactSelected(contact)
            }
        }
    }
}
