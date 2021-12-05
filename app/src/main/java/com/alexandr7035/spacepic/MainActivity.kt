package com.alexandr7035.spacepic

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.alexandr7035.spacepic.core.Navigation
import com.alexandr7035.spacepic.ui.apods_list.ApodsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigation {

    private lateinit var startDestination: Fragment
    private val hostFragmentId = R.id.nav_host_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.navigationBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.TRANSPARENT
        
        // Initialize hostFragment
        startDestination = ApodsListFragment()
        navigateStart()
    }

    override fun navigateStart() {
        replace(startDestination)
    }

    override fun add(fragment: Fragment, addToBackStack: Boolean) {
        if (addToBackStack) {
            supportFragmentManager.beginTransaction()
                .add(hostFragmentId, fragment)
                .addToBackStack(null)
                .commit()
        }
        else {
            supportFragmentManager.beginTransaction()
                .add(hostFragmentId, fragment)
                .commit()
        }
    }

    override fun replace(fragment: Fragment, addToBackStack: Boolean) {
        if (addToBackStack) {
            supportFragmentManager.beginTransaction()
                .replace(hostFragmentId, fragment)
                .addToBackStack(null)
                .commit()
        }
        else {
            supportFragmentManager.beginTransaction()
                .replace(hostFragmentId, fragment)
                .commit()
        }
    }

    override fun navigateBack() {
        supportFragmentManager.popBackStack()
    }

    override fun checkIfBackStackNotEmpty(): Boolean {
        return supportFragmentManager.backStackEntryCount > 0
    }
}