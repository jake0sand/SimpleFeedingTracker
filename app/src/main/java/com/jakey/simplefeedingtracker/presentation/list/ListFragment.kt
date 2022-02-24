package com.jakey.simplefeedingtracker.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakey.simplefeedingtracker.R
import com.jakey.simplefeedingtracker.databinding.FragmentListBinding
import com.jakey.simplefeedingtracker.data.viewmodel.FeedingsViewModel


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var feedingsViewModel: FeedingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = FeedingsListAdapter()
        val recyclerView = binding.rvFeeding
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        feedingsViewModel = ViewModelProvider(this).get(FeedingsViewModel::class.java)
        feedingsViewModel.readAllFeedings.observe(viewLifecycleOwner) { feeding ->
            adapter.setData(feeding)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}