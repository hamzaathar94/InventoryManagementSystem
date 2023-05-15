package com.example.inventorymanagementsystem.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.inventorymanagementsystem.AppDatabase
import com.example.inventorymanagementsystem.databinding.FragmentAddProductBinding
import com.example.inventorymanagementsystem.model.Product

import kotlinx.coroutines.launch

class AddProductFragment : Fragment() {

    private var binding:FragmentAddProductBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentAddProductBinding.inflate(LayoutInflater.from(context),container,false)
        binding?.addproduct?.setOnClickListener {
            addProduct()
            findNavController().navigateUp()
            //val id=arguments?.getString("userId")
            //Log.d("ooo",id.toString())
        }
        return binding?.root
    }

    fun addProduct(){

        val name=binding?.edtxtpname?.text.toString()
        val price=binding?.edtxtpprice?.text.toString()
        val qty=binding?.edtxtpqty?.text.toString()
        val shopkepid=arguments?.getString("userId")
        //Log.d("ooo",shopkepid.toString())

        lifecycleScope.launch {

            val product=Product(productName = name, productPrice = price.toInt(),
                productQty = qty.toInt(), shopkeeperId = shopkepid!!.toInt())
            AppDatabase(requireContext()).getProductdao().addProduct(product)

            binding?.edtxtpname?.text?.clear()
            binding?.edtxtpprice?.text?.clear()
            binding?.edtxtpqty?.text?.clear()


        }


    }
}