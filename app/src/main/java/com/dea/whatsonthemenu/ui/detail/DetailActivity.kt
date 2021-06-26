package com.dea.whatsonthemenu.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dea.whatsonthemenu.R
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.utils.Helper.loadImage
import com.dea.whatsonthemenu.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    companion object {
        const val EXTRA_MENU = "extra_menu"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menu = intent.getParcelableExtra<Menu>(EXTRA_MENU)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        if (menu != null) {
            viewModel.getMenuInformation(menu.id.toString()).observe(this) { menuInfo ->
                if (menuInfo != null) {
                    with(binding) {
                        tvTitle.text = menuInfo.data?.title
                        tvRestaurant.text = menuInfo.data?.restaurant
                        tvPrice.text = menuInfo.data?.price
                        tvCal.text = menuInfo.data?.calories.toString()
                        tvProtein.text = menuInfo.data?.protein.toString()
                        tvCarbs.text = menuInfo.data?.carbs.toString()
                        civMenu.loadImage(menuInfo.data?.image)
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}