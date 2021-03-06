package com.dea.whatsonthemenu.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dea.whatsonthemenu.R
import com.dea.whatsonthemenu.core.data.Resource
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.ui.GridMenuAdapter
import com.dea.whatsonthemenu.databinding.ActivityMainBinding
import com.dea.whatsonthemenu.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var menuAdapter: GridMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        viewModel.getPizzas().observe(this, menuObserver)
        menuAdapter = GridMenuAdapter()

        with(binding.rvPizza) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
            adapter = menuAdapter
        }

        menuAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MENU, selectedData)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_fav) {
            val intent = Intent(this, Class.forName("com.dea.whatsonthemenu.menufavorite.FavoriteActivity"))
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private val menuObserver = Observer<Resource<List<Menu>>> { pizza ->
        if (pizza != null) {
            when (pizza) {
                is Resource.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressCircular.visibility = View.GONE
                    menuAdapter.setData(pizza.data)
                    Log.d("cek data", pizza.data.toString())
                }
                is Resource.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    pizza.message?.let { Log.d("error trace", it) }
                }
            }
        }
    }
}