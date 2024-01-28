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
import androidx.navigation.fragment.findNavController
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

        // Pobierz aktualnie zalogowanego użytkownika
        val currentUser = FirebaseAuth.getInstance().currentUser

        // Sprawdź, czy użytkownik jest zalogowany
        if (currentUser != null) {
            // Wyświetl adres e-mail użytkownika w TextView
            binding.useremail.text = "${currentUser.email}"
        }

        binding.logoutBtn.setOnClickListener {
            // Wyloguj użytkownika
            FirebaseAuth.getInstance().signOut()

            // Przenieś użytkownika do ekranu logowania po wylogowaniu
            navControl = Navigation.findNavController(view)
            navControl.navigate(R.id.action_profileFragment_to_signInFragment)
        }
    }
}
