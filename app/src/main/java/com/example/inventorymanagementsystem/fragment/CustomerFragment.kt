package com.example.inventorymanagementsystem.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventorymanagementsystem.AppDatabase
import com.example.inventorymanagementsystem.R
import com.example.inventorymanagementsystem.adapter.CustomerProductAdapter
import com.example.inventorymanagementsystem.adapter.ProductAdapter
import com.example.inventorymanagementsystem.databinding.FragmentCustomerBinding
import com.example.inventorymanagementsystem.model.Product
import kotlinx.coroutines.launch

class CustomerFragment : Fragment() {

    private var binding: FragmentCustomerBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showProduct()
        findNavController().navigateUp()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomerBinding.inflate(LayoutInflater.from(context), container, false)

        return binding?.root
    }

    private fun showProduct() {
        lifecycleScope.launch {
            val productList = AppDatabase(requireContext()).getProductdao().getAllProduct()
            //  Log.d("TAG","${productList.size}")
            binding?.recuclercustomer?.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = CustomerProductAdapter().apply {
                    setData(productList)
                    productList


                    setOnClickAddItem {
                        val builder = AlertDialog.Builder(requireContext())
                        builder.setMessage("Buy Product")
                        builder.setPositiveButton("Yes") { p0, p1 ->
                            lifecycleScope.launch {

                                // AppDatabase(requireContext()).getProductdao().deleteProduct(it)


                            }
                            p0.dismiss()
                        }
                        builder.setNegativeButton("No") { p0, p1 ->
                            p0.dismiss()

                        }
                        val dialog = builder.create()
                        dialog.show()
                        // setData(productList)

                    }
                }

            }

        }
    }

}