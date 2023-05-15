package com.example.inventorymanagementsystem.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventorymanagementsystem.AppDatabase
import com.example.inventorymanagementsystem.R
import com.example.inventorymanagementsystem.adapter.ProductAdapter
import com.example.inventorymanagementsystem.databinding.FragmentShowProductBinding
import kotlinx.coroutines.launch

class ShowProductFragment : Fragment() {

    private var binding:FragmentShowProductBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentShowProductBinding.inflate(LayoutInflater.from(context),container,false)

        binding?.imgadd?.setOnClickListener {
            val bundle= Bundle()
            val id=arguments?.getString("userId")
            bundle.putString("userId",id)
            Log.d("ooo",id.toString())
            findNavController().navigate(R.id.addProductFragment,bundle)
        }

        showProduct()
        return binding?.root
    }

    fun showProduct(){
        lifecycleScope.launch {
            val productList=AppDatabase(requireContext()).getProductdao().getAllProduct()
           //  Log.d("TAG","${productList.size}")
            binding?.recyclerviewshowproduct?.apply {
                layoutManager=LinearLayoutManager(requireContext())
                adapter=ProductAdapter().apply {
                    setData(productList)


                    setOnClickDeleteItem {
                        val builder= AlertDialog.Builder(requireContext())
                        builder.setMessage("Are you sure you want to delete")
                        builder.setPositiveButton("Yes"){p0,p1->
                            lifecycleScope.launch {

                                AppDatabase(requireContext()).getProductdao().deleteProduct(it)


                            }
                            p0.dismiss()
                        }
                        builder.setNegativeButton("No"){p0,p1->
                            p0.dismiss()

                        }
                        val dialog=builder.create()
                        dialog.show()
                        setData(productList)

                    }
                }

            }

        }
    }
}