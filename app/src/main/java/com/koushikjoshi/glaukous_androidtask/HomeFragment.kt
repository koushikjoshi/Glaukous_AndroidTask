package com.koushikjoshi.glaukous_androidtask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.koushikjoshi.glaukous_androidtask.databinding.FragmentHomeBinding
import retrofit2.HttpException
import java.io.IOException

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
    private lateinit var waveText: TextView
    private lateinit var percentText: TextView

//    declaring second cardview variables

    private lateinit var itemCodeText: TextView
    private lateinit var locationText: TextView
    private lateinit var quantityText: TextView
    private lateinit var sequenceNumText: TextView
    private lateinit var descriptionText: TextView

//     declaring todoAdapter and Binding
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: FragmentHomeBinding

    private var currentProgress = 0
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

//    method that gets called when View is getting created
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

//    Method that gets called after view is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//         Initialize all views from CardViewTop

        batchText = requireView().findViewById(R.id.batchText)
        ofText = requireView().findViewById(R.id.ofTextView)
        itemsPickedText = requireView().findViewById(R.id.itemsPickedTextView)
        itemsRemainingText = requireView().findViewById(R.id.itemsRemainingTextView)
        zoneText = requireView().findViewById(R.id.zoneTextView)
        skuText = requireView().findViewById(R.id.skuTextView)
        waveText = requireView().findViewById(R.id.waveNumberTextView)
        percentText = requireView().findViewById(R.id.percentageTextView)

        ofText.visibility = View.GONE

//         Initialize all views from CardViewBottom

        itemCodeText = requireView().findViewById(R.id.itemCodeTextView)
        locationText = requireView().findViewById(R.id.locationTextView)
        quantityText = requireView().findViewById(R.id.qToPickTextView)
        sequenceNumText = requireView().findViewById(R.id.seqTextView)
        descriptionText = requireView().findViewById(R.id.descriptionTextView)

        progressBar = requireView().findViewById(R.id.progressBar)

//        Calling method to add elements to recyclerView
        setupRecyclerView()

//        Launching coroutine to make API request
        lifecycleScope.launchWhenCreated {

            var BatchNum = RetrofitInstance.api.getBatchNum().body()?.batchNumber

            var firstResponse = RetrofitInstance.api.getBatchNum()

//            Chaning textViews as per response from API
            batchText.text = "Batch "+BatchNum.toString()
            zoneText.text = firstResponse.body()?.zone.toString()
            skuText.text = firstResponse.body()?.numberOfSKUs.toString()
            waveText.text = firstResponse.body()?.waveNumber.toString()


//          Storing response
            var response = try {
                RetrofitInstance.api.getTodos(BatchNum.toString())
            } catch (e: IOException){

                Log.e("HomeFragment", "IOException")
                return@launchWhenCreated

            }catch (e: HttpException){

                Log.e("HomeFragment", "HttpException")
                return@launchWhenCreated

            }

            if(response.isSuccessful && response.body() != null){

//                Sending data to recyclerview
                todoAdapter.todos = response.body()!!.data.items!!

//                Changing textView as per response
                itemsPickedText.text = response.body()!!.data.totalQuantityPicked.toString()
                itemsRemainingText.text = response.body()!!.data.totalQuantityToBePicked.toString()

//                Calculating Percentage
                var percentage = 100 * Integer.valueOf(response.body()!!.data.totalQuantityPicked) / (Integer.valueOf(response.body()!!.data.totalQuantityPicked) + Integer.valueOf(response.body()!!.data.totalQuantityToBePicked))
                percentText.text = percentage.toString()+"%"

//                Changing progressBar value as per the percentage
                currentProgress = currentProgress+percentage
                progressBar.setProgress(currentProgress)
                progressBar.max = 100

//                Changing bottom cardView as per the response
                itemCodeText.text = response.body()!!.data.items[0].itemCode.toString()
                locationText.text = response.body()!!.data.items[0].locationID.toString()
                quantityText.text = response.body()!!.data.items[0].quantityToBePicked.toString()
                sequenceNumText.text = response.body()!!.data.items[0].sequenceID.toString()
                descriptionText.text = response.body()!!.data.items[0].itemDescription.toString()

            }
            else{
                Log.e("HomeFragment", "Unsuccesful Response")
            }

        }

    }


//    Function to add items in recyclerview

    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(view?.context)
    }


// Boilerplate code

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