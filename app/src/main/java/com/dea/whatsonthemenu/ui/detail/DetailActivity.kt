package com.dea.whatsonthemenu.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
            viewModel.getMenu(menu.id)
            viewModel.menu.observe(this) { data ->
                setFavoriteState(menu.isFavorite)
                binding.tvAddToFav.setOnClickListener {
                    val favoriteStateOpposite = !data.isFavorite
                    viewModel.setFavoriteMenu(menu, favoriteStateOpposite)
                    if (favoriteStateOpposite) Toast.makeText(
                        this,
                        "Added to favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                    else Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
                    setFavoriteState(favoriteStateOpposite)
                }
            }
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            binding.tvAddToFav.text = "Added to Favorite"
            binding.tvAddToFav.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
            binding.tvAddToFav.setCompoundDrawablesWithIntrinsicBounds(
                0, 0,
                R.drawable.ic_baseline_favorite_24, 0
            )
        } else {
            binding.tvAddToFav.text = "Add to Favorite"
            binding.tvAddToFav.setBackgroundColor(ContextCompat.getColor(this, R.color.dark))
            binding.tvAddToFav.setCompoundDrawablesWithIntrinsicBounds(
                0, 0,
                R.drawable.ic_baseline_favorite_border_24, 0
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}