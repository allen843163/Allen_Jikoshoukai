package com.example.allen_jikoshoukai

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.allen_jikoshoukai.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.app_bar_main.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding : ActivityMainBinding

    private val mainViewModel : MainViewModel by viewModel()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainVM = mainViewModel

        setSupportActionBar(binding.includeContentGlobal.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_self_introduction, R.id.nav_skills, R.id.nav_background,
                R.id.nav_education, R.id.nav_hobbies, R.id.nav_interests
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navView.setupWithNavController(navController)

        mainViewModel.actionBarVisibilityController.observe(this, Observer {
            if(it) {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

                binding.includeContentGlobal.toolbar.visibility = View.VISIBLE

                binding.includeContentGlobal.layout_fab_main.visibility = View.VISIBLE
            }
            else {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

                binding.includeContentGlobal.toolbar.visibility = View.GONE

                binding.includeContentGlobal.layout_fab_main.visibility = View.GONE
            }
        })

        binding.includeContentGlobal.fab_child_home.setOnClickListener{
            navController.navigate(R.id.nav_self_introduction)
        }


        binding.includeContentGlobal.fab_child_language.setOnClickListener{
            navController.navigate(R.id.nav_language_select)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(ev?.action == MotionEvent.ACTION_DOWN) {
            binding.layoutMain.progress = 0.0F

            binding.ivGlobalRipple.x = ev.x - binding.ivGlobalRipple.width/2

            binding.ivGlobalRipple.y = ev.y - binding.ivGlobalRipple.height/2

            binding.layoutMain.transitionToEnd()
        }
        else if(ev?.action == MotionEvent.ACTION_MOVE) {
            binding.ivGlobalRipple.x = ev.x - binding.ivGlobalRipple.width/2

            binding.ivGlobalRipple.y = ev.y - binding.ivGlobalRipple.height/2
        }

        return super.dispatchTouchEvent(ev)
    }
}
