/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.example.robertandroidcourse2021

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.robertandroidcourse2021.databinding.RecyclerItemContactModelBinding


class ContactListFragment : Fragment() {



    private lateinit var imageResIds: IntArray
    private lateinit var names: Array<String>
    private lateinit var numbers: Array<String>
    private lateinit var numberstwo: Array<String>
    private lateinit var email: Array<String>
    private lateinit var emailtwo: Array<String>
    private lateinit var descriptions: Array<String>

    private lateinit var listener: onContactSelected

    companion object {

        fun newInstance(): ContactListFragment {
            return ContactListFragment()
        }
    }

    override fun onAttach(context: Context) {
        if (context != null) {
            super.onAttach(context)
        }

        if (context is onContactSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnDogSelected.")
        }

        // Get dog names and descriptions.
        val resources = context.resources
        names = resources.getStringArray(R.array.names)
        numbers = resources.getStringArray(R.array.contacts)
        numberstwo = resources.getStringArray(R.array.contacts)
        email = resources.getStringArray(R.array.email)
        emailtwo = resources.getStringArray(R.array.emailtwo)

        descriptions = resources.getStringArray(R.array.descriptions)


        // Get dog images.
        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_contact_list, container,
            false

        )



        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = DogListAdapter(activity)
        return view
    }

    internal inner class DogListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val recyclerDogModelBinding =
                RecyclerItemContactModelBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(recyclerDogModelBinding.root, recyclerDogModelBinding)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val contact = ContactModel(
                imageResIds[position],
                names[position],
                numbers[position],
                numberstwo[position],
                email[position],
                emailtwo[position],
                descriptions[position]
            )
            viewHolder.setData(contact)
            viewHolder.itemView.setOnClickListener { listener.onDogSelected(contact) }
        }

        override fun getItemCount() = names.size
    }

    internal inner class ViewHolder constructor(
        itemView: View,
        private val recyclerItemDogListBinding:
        RecyclerItemContactModelBinding
    ) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(contactModel: ContactModel) {
            recyclerItemDogListBinding.contactModel = contactModel
        }
    }

    interface onContactSelected {
        fun onDogSelected(contactModel: ContactModel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
