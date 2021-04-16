package com.example.robertandroidcourse2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.robertandroidcourse2021.databinding.FragmentContactDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
                arguments = bundleOf(Pair("contactId", id))
                }
            }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments!!.getInt("contactId")

        CoroutineScope(IO).launch {
            val service = (activity as? ServiceProvider)?.getService()!!
            val contact = service.getContactDetails(id)
            withContext(Main) {

                with(binding) {
                    if (contact != null) {
                        contactImageDetail.setImageResource(contact.imageResId)
                    }
                    if (contact != null) {
                        nameDetail.text = contact.name
                    }
                    if (contact != null) {
                        contactNumberOne.text = contact.numberOne
                    }
                    if (contact != null) {
                        contactNumberTwo.text = contact.numberOne
                    }
                    if (contact != null) {
                        emailFirst.text = contact.emailFirst
                    }
                    if (contact != null) {
                        emailSecond.text = contact.emailSecond
                    }
                    if (contact != null) {
                        description.text = contact.description
                    }

                }
            }
        }

    }

}
