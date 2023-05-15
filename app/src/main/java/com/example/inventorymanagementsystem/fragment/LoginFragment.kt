package com.example.inventorymanagementsystem.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.inventorymanagementsystem.AppDatabase
import com.example.inventorymanagementsystem.R
import com.example.inventorymanagementsystem.databinding.FragmentLoginBinding
import com.example.inventorymanagementsystem.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private var binding:FragmentLoginBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(LayoutInflater.from(context),container,false)

        binding?.txtsignup?.setOnClickListener {
            findNavController().navigate(R.id.signupFragment)
        }

        binding?.getlogin?.setOnClickListener { loginUser() }
        return binding?.root
    }

    fun loginUser(){
        if (binding?.edtxtlogemail?.text!!.isEmpty() || binding?.edtxtlogpass?.text!!.isEmpty()){
            Toast.makeText(requireContext(),"Fields are Empty...",Toast.LENGTH_SHORT).show()
        }else{

            if (binding?.rbtnlogshopkep!!.isChecked){
                val email=binding?.edtxtlogemail?.text.toString()
                val pass=binding?.edtxtlogpass?.text.toString()
                val type="ShopKeeper"
                GlobalScope.launch {
                    val data=AppDatabase(requireContext()).getProductdao().getOneData(type?:return@launch)
                    display(data,email,pass?:return@launch)

                }


               // findNavController().navigate(R.id.shopKeeperFragment)
            }else if (binding?.rbtnlogcust!!.isChecked){
                val email=binding?.edtxtlogemail?.text.toString()
                val pass=binding?.edtxtlogpass?.text.toString()
                val type="Customer"
                GlobalScope.launch {
                    val data=AppDatabase(requireContext()).getProductdao().getOneData(type?:return@launch)
                    display(data,email,pass?:return@launch)
                }

              //  findNavController().navigate(R.id.customerFragment)
            }
            else{
                Toast.makeText(requireContext(), "Select atleast one radio button", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private suspend fun display(user: User,email:String,pass:String){
        withContext(Dispatchers.Main){
            if (user!=null) {
                if (user.email.equals(email)) {
                    if (user.password.equals(pass)) {
                       // Log.d("ooo",user.name)
                        val bundle = Bundle()
                        bundle.putString("name", user.name)
                        bundle.putString("email", user.email)
                        bundle.putString("pass", user.password)
                        bundle.putString("userId", user.id.toString())
                        if(user.usertype=="ShopKeeper"){
                            findNavController().navigate(R.id.showProductFragment,bundle)
                        }
                        else{
                            findNavController().navigate(R.id.customerFragment)
                        }

                    } else {
                        Toast.makeText(requireContext(), "Wrong Password", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Wrong Email And Password", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }
}