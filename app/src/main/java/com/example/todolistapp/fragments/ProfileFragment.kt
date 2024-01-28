package com.example.todolistapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todolistapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.example.todolistapp.R


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var navControl: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            binding.useremail.text = "${currentUser.email}"
        }

        binding.logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            navControl = Navigation.findNavController(view)
            navControl.navigate(R.id.action_profileFragment_to_signInFragment)
        }
    }
}
