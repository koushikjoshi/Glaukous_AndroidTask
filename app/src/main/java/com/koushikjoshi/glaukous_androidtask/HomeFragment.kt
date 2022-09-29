package com.koushikjoshi.glaukous_androidtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    declaring top cardview variables

    private lateinit var batchText: TextView
    private lateinit var ofText: TextView
    private lateinit var itemsPickedText: TextView
    private lateinit var itemsRemainingText: TextView
    private lateinit var zoneText: TextView
    private lateinit var skuText: TextView

//    declaring second cardview variables

    private lateinit var itemCodeText: TextView
    private lateinit var locationText: TextView
    private lateinit var quantityText: TextView
    private lateinit var sequenceNumText: TextView
    private lateinit var descriptionText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        Initialize all views from CardViewTop

        batchText = requireView().findViewById(R.id.batchText)
        ofText = requireView().findViewById(R.id.ofTextView)
        itemsPickedText = requireView().findViewById(R.id.itemsPickedTextView)
        itemsRemainingText = requireView().findViewById(R.id.itemsRemainingTextView)
        zoneText = requireView().findViewById(R.id.zoneTextView)
        skuText = requireView().findViewById(R.id.skuTextView)

//         Initialize all views from CardViewBottom

        itemCodeText = requireView().findViewById(R.id.itemCodeTextView)
        locationText = requireView().findViewById(R.id.locationTextView)
        quantityText = requireView().findViewById(R.id.qToPickTextView)
        sequenceNumText = requireView().findViewById(R.id.seqTextView)
        descriptionText = requireView().findViewById(R.id.descriptionTextView)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}