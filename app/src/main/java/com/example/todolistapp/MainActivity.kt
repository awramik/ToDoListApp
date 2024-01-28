package com.example.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todolistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id) {
                R.id.homeFragment, R.id.profileFragment, R.id.weatherFragment ->{
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                else ->{
                    binding.bottomNavigationView.visibility = View.GONE
                }

            }            }
        }





//
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        setupWithNavController(bottomNavigationView, navController)
//
//        bottomNavigationView.setOnNavigationItemSelectedListener { it ->
//            when(it.itemId) {
//                R.id.splashFragment -> {
//                    bottomNavigationView.visibility = View.GONE
//                    true
//                }
//                R.id.signInFragment -> {
//                    bottomNavigationView.visibility = View.GONE
//                    true
//                }
//                else -> false
//
//            }}
//        }


//        binding.bottomNavigationView.setOnItemSelectedListener {
//
//            when(it.itemId){
//                R.id.homeFragment -> replaceFragment(HomeFragment())
//                R.id.notesFragment -> replaceFragment(NotesFragment())
//                R.id.weatherFragment -> replaceFragment(WeatherFragment())
//
//                else ->{
//
//                }
//
//            }
//            true
//
//        }
//    }
//
//    private fun replaceFragment(fragment : Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
//        fragmentTransaction.commit()

//
//        when(fragment){
//            is HomeFragment, is NotesFragment, is WeatherFragment ->{
//                binding.bottomNavigationView.visibility = View.VISIBLE
//            }
//            else ->{
//                binding.bottomNavigationView.visibility = View.GONE
//            }
//        }
//
//
//    }
}