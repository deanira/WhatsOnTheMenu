package com.dea.whatsonthemenu.ui.pasta

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
import com.dea.whatsonthemenu.databinding.FragmentPastaBinding
import com.dea.whatsonthemenu.databinding.FragmentPizzaBinding
import com.dea.whatsonthemenu.ui.pizza.PizzaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PastaFragment : Fragment() {

    private val viewModel: PastaViewModel by viewModels()
    private lateinit var binding: FragmentPastaBinding
    private lateinit var menuAdapter: GridMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPastaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel.getPastas().observe(viewLifecycleOwner, menuObserver)
//        menuAdapter = GridMenuAdapter()
//
//        with(binding.rvPasta) {
//            layoutManager = GridLayoutManager(requireContext(), 2)
//            setHasFixedSize(true)
//            adapter = menuAdapter
//        }
//    }
//
//    private val menuObserver = Observer<Resource<List<Menu>>> { pasta ->
//        if (pasta != null) {
//            when (pasta) {
//                is Resource.Loading -> {
//                    binding.progressCircular.visibility = View.VISIBLE
//                }
//                is Resource.Success -> {
//                    binding.progressCircular.visibility = View.GONE
//                    menuAdapter.setData(pasta.data)
//                }
//                is Resource.Error -> {
//                    binding.progressCircular.visibility = View.GONE
//                    pasta.message?.let { Log.d("error trace", it) }
//                }
//            }
//        }
//    }
}