package com.mysub2e.capstoneproject2.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.mysub2e.capstoneproject2.R
import com.mysub2e.capstoneproject2.databinding.ActivityMainBinding
import com.mysub2e.capstoneproject2.ui.home.HomeFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private fun instantiateFragment(): Fragment? {
        return try {
            Class.forName("com.capstoneproject2.favorite.FavoriteFragment")
                .newInstance() as Fragment
        } catch (e: Exception) {
            null
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_home -> {
                fragment = HomeFragment()
            }
            R.id.nav_favorite -> {
                fragment = instantiateFragment()
            }
        }
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        } else {
            Toast.makeText(this, R.string.no_Fragment_Found, Toast.LENGTH_SHORT).show()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }
}