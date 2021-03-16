package com.example.robertandroidcourse2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.robertandroidcourse2021.databinding.FragmentContactListBinding
import kotlinx.android.synthetic.main.fragment_contact_list.*


class ContactListFragment : Fragment() {

    private var fragmentDemoBinding: FragmentContactListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(
            R.layout.fragment_contact_list, container,
            false
        )
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentContactListBinding.bind(view)
        fragmentDemoBinding = binding
        val contacts = getFakeContacts()
        val contact = contacts[0]
        contact_image.setImageResource(contact.imageResId)
        binding.name.text = contact.name
        binding.contactNumber.text = contact.numberOne
        binding.contactLayout.setOnClickListener {
            (activity as? MainActivity)?.onContactSelected(contact)
        }
    }

    override fun onDestroyView() {
        fragmentDemoBinding = null
        super.onDestroyView()
    }


}
