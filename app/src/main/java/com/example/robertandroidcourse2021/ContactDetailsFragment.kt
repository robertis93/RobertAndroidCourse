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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.CoroutineContext


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
        val isBound = (activity as ServiceProvider).getBound()
        if (isBound) {
            val job = CoroutineScope(IO).launch {
                val service = (activity as? ServiceProvider)?.getService()
                val contact = service?.getContactDetails(id) ?: return@launch
                withContext(Main) {
                    with(binding) {
                        contactImageDetail.setImageResource(contact.imageResId)
                        nameDetail.text = contact.name
                        contactNumberOne.text = contact.numberOne
                        contactNumberTwo.text = contact.numberOne
                        birthday.text =
                            "Day of Birthday: ${contact.dayOfBirth} / ${contact.monthOfBirth}"
                        emailFirst.text = contact.emailFirst
                        emailSecond.text = contact.emailSecond
                        description.text = contact.description

                        btnNtf.setOnClickListener {
                            val alreadySubscribe = false
                            if (alreadySubscribe)
                                canselAlarm()
                            else
                                setAlarm()

                        }
                    }
                }
            }
            job.cansel()
        }
    }

    private suspend fun setAlarm(contact: ContactModel) {
        val alarmManager =
            requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val text = "Сегодня дерь рождение у ${contact.name}"
        val intent = Intent(requireContext(), ContactReceiver::class.java)
        intent.putExtra("text", text)
        intent.putExtra("id", contact.id)

        val alarmIntent =
            PendingIntent.getBroadcast(requireContext(), contact.id, intent, 0)

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            getDateToExecute(contact).timeInMillis,
            DateUtils.YEAR_IN_MILLIS,
            alarmIntent
        )
    }

    private suspend fun canselAlarm(contact: ContactModel) {

    }

    private suspend fun getDateToExecute(contact: ContactModel): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val currentyear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val year = if (currentMonth > contact.monthOfBirth && currentDay > contact.dayOfBirth)
            currentyear + 1
        else
            currentyear
        calendar.set(Calendar.MONTH, contact.monthOfBirth)
        calendar.set(Calendar.DAY_OF_MONTH, contact.dayOfBirth)
        calendar.set(Calendar.YEAR, currentyear)
    }
}
