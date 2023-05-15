package com.example.inventorymanagementsystem.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.inventorymanagementsystem.AppDatabase
import com.example.inventorymanagementsystem.R
import com.example.inventorymanagementsystem.databinding.FragmentSignupBinding
import com.example.inventorymanagementsystem.model.User
import kotlinx.coroutines.launch

class SignupFragment : Fragment() {

    private var binding:FragmentSignupBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignupBinding.inflate(LayoutInflater.from(context),container,false)
        binding?.btnsignup?.setOnClickListener {
            addUser()
        }
        return binding?.root
    }

    fun addUser(){
        if (binding?.edtxtsignname?.text!!.isEmpty() || binding?.edtxtsigemail?.text!!.isEmpty()
            || binding?.edtxtsigpass?.text!!.isEmpty()){
            Toast.makeText(requireContext(),"Fields are Empty...",Toast.LENGTH_SHORT).show()

        }else{
            if (binding?.rdbtnshopk!!.isChecked){
                val rdbtntext="ShopKeeper"
                val name=binding?.edtxtsignname?.text.toString()
                val email=binding?.edtxtsigemail?.text.toString()
                val pass=binding?.edtxtsigpass?.text.toString()
                lifecycleScope.launch {
                    val user=User(name=name, email = email, password = pass, usertype = rdbtntext)
                    AppDatabase(requireContext()).getProductdao().addUser(user)

                    binding?.edtxtsignname?.text?.clear()
                    binding?.edtxtsigemail?.text?.clear()
                    binding?.edtxtsigpass?.text?.clear()
                    binding?.radiogroup?.clearCheck()
                }


            }else{
                val rdbtntext="Customer"
                val name=binding?.edtxtsignname?.text.toString()
                val email=binding?.edtxtsigemail?.text.toString()
                val pass=binding?.edtxtsigpass?.text.toString()
                lifecycleScope.launch {
                    val user=User(name=name, email = email, password = pass, usertype = rdbtntext)
                    AppDatabase(requireContext()).getProductdao().addUser(user)

                    binding?.edtxtsignname?.text?.clear()
                    binding?.edtxtsigemail?.text?.clear()
                    binding?.edtxtsigpass?.text?.clear()
                    binding?.radiogroup?.clearCheck()
                }
                //findNavController().navigate(R.id.shopKeeperFragment)

            }
            findNavController().navigate(R.id.loginFragment)
            //Toast.makeText(requireContext(),"Fields are not Empty...",Toast.LENGTH_SHORT).show()
        }
    }

}