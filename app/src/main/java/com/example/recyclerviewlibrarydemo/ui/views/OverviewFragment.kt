package com.example.recyclerviewlibrarydemo.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlibrarydemo.databinding.FragmentOverviewBinding
import com.example.recyclerviewlibrarydemo.domain.entities.Property
import com.example.recyclerviewlibrarydemo.ui.viewmodels.OverviewFragmentViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_overview.view.*

class OverviewFragment : Fragment() {
    private val TAG = "OverviewFragment"
    private lateinit var photosGrid: RecyclerView
    val selection: MutableList<String> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentOverviewBinding.inflate(inflater)
        val chips = listOf<Chip>(binding.chip4, binding.chip5)
        val vm = OverviewFragmentViewModel()
        photosGrid = binding.root.photos_grid
        val properties = mutableListOf<Property>()
        val photosGridAdapter = PhotosGridAdapter(properties)
        photosGrid.adapter = photosGridAdapter
        photosGrid.layoutManager = GridLayoutManager(container?.context, 2)

        chips.forEach {
            it.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    vm.addFilter(it.text.toString())
                else vm.removeFilter(it.text.toString())
            }
        }
        vm.showIndicator.observe(viewLifecycleOwner, {
            Log.d(TAG, "onCreateView: observe showindicator $it")
            binding.progressBar.visibility = if (it && (vm.marsProperties.value == null || vm.marsProperties.value?.isEmpty() == true))
                View.VISIBLE
            else
                View.GONE
        })
        vm.filters.observe(viewLifecycleOwner, {
            binding.textViewNoData.visibility = if (it == null || it.isEmpty()) View.VISIBLE else View.GONE
        })
        vm.marsProperties.observe(viewLifecycleOwner, {
            properties.removeAll(properties)
            properties.addAll(it)
            photosGridAdapter.notifyDataSetChanged()
        })
        vm.getProperties()
        return binding.root
    }
}