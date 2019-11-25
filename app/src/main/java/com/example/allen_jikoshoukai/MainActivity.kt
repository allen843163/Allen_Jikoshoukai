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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.allen_jikoshoukai.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.app_bar_main.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding : ActivityMainBinding

    private val mainViewModel : MainViewModel by viewModel()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.includeContentGlobal.toolbar)

        val fab: FloatingActionButton = binding.includeContentGlobal.fab

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val navView: NavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_self_introduction, R.id.nav_skills, R.id.nav_background,
                R.id.nav_education, R.id.nav_hobbies, R.id.nav_interests
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        mainViewModel.actionBarVisibilityController.observe(this, Observer {
            if(it) {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

                binding.includeContentGlobal.toolbar.visibility = View.VISIBLE

                binding.includeContentGlobal.fab.visibility = View.VISIBLE
            }
            else {
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

                binding.includeContentGlobal.toolbar.visibility = View.GONE

                binding.includeContentGlobal.fab.visibility = View.GONE
            }
        })
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
