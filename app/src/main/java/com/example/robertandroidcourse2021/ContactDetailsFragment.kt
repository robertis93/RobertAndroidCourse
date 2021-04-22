package com.example.robertandroidcourse2021

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.robertandroidcourse2021.databinding.FragmentContactDetailsBinding
import kotlinx.android.synthetic.main.fragment_contact_details.*
import kotlinx.coroutines.launch
import java.util.*


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
        lifecycleScope.launch {
            val service = (activity as? ServiceProvider)?.getService()
            val contact = service?.getContactDetails(id) ?: return@launch
            with(binding) {
                contactImageDetail.setImageResource(contact.imageResId)
                nameDetail.text = contact.name
                birthday.text = "DateOfBirth${contact.dayOfBirth} / ${contact.monthOfBirth}"
                contactNumberOne.text = contact.numberOne
                contactNumberTwo.text = contact.numberOne
                emailFirst.text = contact.emailFirst
                emailSecond.text = contact.emailSecond
                description.text = contact.description

                btn_ntf.setOnClickListener {
                    val alreadySubscribe = false
                    if (alreadySubscribe)
                        removeAlarm(contact)
                    else
                        setAlarm(contact)
                }
            }
        }
    }

    private fun setAlarm(contact: ContactModel) {
        val alarmManager =
            requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val text = "Сегодня день рождение у ${contact.name}"
        val intent = Intent(requireContext(), ContactReceiver::class.java)
        intent.putExtra("text", text)
        intent.putExtra("id", contact.id)

        val alarmIntent = PendingIntent.getBroadcast(requireContext(), contact.id, intent, 0)

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            getDateToExecute(contact).timeInMillis,
            DateUtils.YEAR_IN_MILLIS,
            alarmIntent
        )
    }

    private fun removeAlarm(contact: ContactModel) {


    }

    private fun getDateToExecute(contact: ContactModel): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val year =
            if (currentMonth > contact.monthOfBirth && currentDay > contact.dayOfBirth)
                currentYear + 1
            else
                currentYear
        calendar.set(Calendar.MONTH, contact.monthOfBirth)
        calendar.set(Calendar.DAY_OF_MONTH, contact.dayOfBirth)
        calendar.set(Calendar.YEAR, year)
        return calendar
    }
}
