package com.example.robertandroidcourse2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.robertandroidcourse2021.databinding.FragmentContactDetailsBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ContactDetailsFragment : Fragment() {

    private lateinit var binding: FragmentContactDetailsBinding
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
                arguments = bundleOf("contactId" to id)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments!!.getInt("contactId")
        lifecycleScope.launch(IO) {
            val isBound = (activity as ServiceProvider).getBound()
            if (isBound) {
                val service = (activity as? ServiceProvider)?.getService()
                val contact = service?.getContactDetails(id) ?: return@launch
                withContext(Main) {
                    with(binding) {
                        contactImageDetail.setImageResource(contact.imageResId)
                        nameDetail.text = contact.name
                        contactNumberOne.text = contact.numberOne
                        contactNumberTwo.text = contact.numberOne
                        emailFirst.text = contact.emailFirst
                        emailSecond.text = contact.emailSecond
                        description.text = contact.description
                    }
                }
            }
        }
    }
}
