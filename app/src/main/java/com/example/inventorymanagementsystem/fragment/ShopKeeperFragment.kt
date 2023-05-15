package com.example.inventorymanagementsystem.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.inventorymanagementsystem.R
import com.example.inventorymanagementsystem.databinding.FragmentShopKeeperBinding

class ShopKeeperFragment : Fragment() {

    private var binding:FragmentShopKeeperBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentShopKeeperBinding.inflate(LayoutInflater.from(context),container,false)
        binding?.cardshowproduct?.setOnClickListener {
            findNavController().navigate(R.id.showProductFragment)
        }
        binding?.cardaddproduct?.setOnClickListener {
            findNavController().navigate(R.id.addProductFragment)
        }
        return binding?.root
    }
}