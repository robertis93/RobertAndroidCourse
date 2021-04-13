package com.example.robertandroidcourse2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.robertandroidcourse2021.databinding.FragmentContactDetailsBinding


class ContactDetailsFragment : Fragment() {

    lateinit var binding: FragmentContactDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun getNewInstance(id: Int) =
            ContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt("contactId", id)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments!!.getInt("contactId")
        val service = (activity as?  ServiceProvider)?.getService()!!
        val contact = service.getContactDetails(id)

        binding.contactImageDetail.setImageResource(contact.imageResId)
        binding.nameDetail.text = contact.name
        binding.contactNumberOne.text = contact.numberOne
        binding.contactNumberTwo.text = contact.numberOne
        binding.emailFirst.text = contact.emailFirst
        binding.emailSecond.text = contact.emailSecond
        binding.description.text = contact.description
    }

}
