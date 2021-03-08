package com.example.robertandroidcourse2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.robertandroidcourse2021.ContactModel
import com.example.robertandroidcourse2021.databinding.FragmentContactDetailsBinding


//1
class ContactDetailsFragment : Fragment() {

  //2
  companion object {

    private const val CONTACTMODEL = "model"

    fun newInstance(contactModel: ContactModel): ContactDetailsFragment {
      val args = Bundle()
      args.putSerializable(CONTACTMODEL, contactModel)
      val fragment = ContactDetailsFragment()
      fragment.arguments = args
      return fragment
    }
  }

  //3
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val fragmentDogDetailsBinding =
        FragmentContactDetailsBinding.inflate(inflater, container, false)


    val model = arguments!!.getSerializable(CONTACTMODEL) as ContactModel
    fragmentDogDetailsBinding.contactModel = model
    //model.text = String.format(getString(R.string.description_format), model.description)
    return fragmentDogDetailsBinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

  }

}
