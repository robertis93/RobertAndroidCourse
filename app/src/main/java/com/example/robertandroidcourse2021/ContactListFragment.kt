 package com.example.robertandroidcourse2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.robertandroidcourse2021.databinding.FragmentContactListBinding
import kotlinx.android.synthetic.main.fragment_contact_list.*


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

    companion object{
        fun getNewInstance() =
            ContactListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service = (activity as?  ServiceProvider)?.getService()!!
        val contacts = service.getContactList()
        val contact = contacts[0]
        contact_image.setImageResource(contact.imageResId)
        binding.name.text = contact.name
        binding.contactNumber.text = contact.numberOne
        binding.contactLayout.setOnClickListener {
            (activity as? MainActivity)?.onContactSelected(contact)
        }
    }
}
