package com.example.todolistapp

import WeatherFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.example.todolistapp.databinding.ActivityMainBinding
import com.example.todolistapp.fragments.HomeFragment
import com.example.todolistapp.fragments.NotesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())


        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.notes -> replaceFragment(NotesFragment())
                R.id.weather -> replaceFragment(WeatherFragment())

                else ->{

                }

            }
            true

        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()

//        when(fragment){
//            is HomeFragment, is NotesFragment, is WeatherFragment ->{
//                binding.bottomNavigationView.visibility = View.VISIBLE
//            }
//            else ->{
//                binding.bottomNavigationView.visibility = View.GONE
//            }
//        }


    }
}