package com.dea.whatsonthemenu.menufavorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.ui.GridMenuAdapter
import com.dea.whatsonthemenu.di.DynamicFeatureDependencies
import com.dea.whatsonthemenu.menufavorite.databinding.ActivityFavoriteBinding
import com.dea.whatsonthemenu.menufavorite.di.DaggerDfmDaggerComponent
import com.dea.whatsonthemenu.ui.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: FavoriteViewModel
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var menuAdapter: GridMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        initCoreDependencyInjection()

        menuAdapter = GridMenuAdapter()

        viewModel.getFavoriteMenus().observe(this, menuObserver)

        with(binding.rvPizza) {
            layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
            setHasFixedSize(true)
            adapter = menuAdapter
        }

        menuAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MENU, selectedData)
            startActivity(intent)
        }
    }

    private val menuObserver = Observer<List<Menu>> { pizza ->
        if (pizza.isNotEmpty()) {
            with(binding) {
                binding.progressCircular.visibility = View.GONE
                menuAdapter.setData(pizza)
                tvFavEmpty.visibility = View.VISIBLE
                rvPizza.visibility = View.GONE
            }
        } else {
            with(binding) {
                binding.progressCircular.visibility = View.GONE
                tvFavEmpty.visibility = View.VISIBLE
                rvPizza.visibility = View.GONE
            }
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    private fun initCoreDependencyInjection() {
        DaggerDfmDaggerComponent.factory().create(
            EntryPointAccessors.fromApplication(
                this,
                DynamicFeatureDependencies::class.java
            )
        ).inject(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}