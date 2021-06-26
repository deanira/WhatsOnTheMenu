package com.dea.whatsonthemenu.ui.pizza

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dea.whatsonthemenu.R
import com.dea.whatsonthemenu.core.data.Resource
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.ui.GridMenuAdapter
import com.dea.whatsonthemenu.databinding.FragmentPizzaBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PizzaFragment : Fragment() {

    private val viewModel: PizzaViewModel by viewModels()
    private lateinit var binding: FragmentPizzaBinding
    private lateinit var menuAdapter: GridMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPizzaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPizzas().observe(viewLifecycleOwner, menuObserver)
        menuAdapter = GridMenuAdapter()

        with(binding.rvPizza) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = menuAdapter
        }
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