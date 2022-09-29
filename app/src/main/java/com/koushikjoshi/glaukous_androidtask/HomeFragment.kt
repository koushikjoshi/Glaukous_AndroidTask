package com.koushikjoshi.glaukous_androidtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

//    declaring second cardview variables

    private lateinit var itemCodeText: TextView
    private lateinit var locationText: TextView
    private lateinit var quantityText: TextView
    private lateinit var sequenceNumText: TextView
    private lateinit var descriptionText: TextView


    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: FragmentHomeBinding


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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//         Initialize all views from CardViewTop

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

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
//            make progressbar visible

            val response = try {
                RetrofitInstance.api.getTodos("6")
            } catch (e: IOException){

            }catch (e: HttpException){

            }

        }

    }

    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(view?.context)
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