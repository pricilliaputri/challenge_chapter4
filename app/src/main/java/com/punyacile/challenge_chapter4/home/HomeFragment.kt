package com.punyacile.challenge_chapter4.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.punyacile.challenge_chapter4.R
import com.punyacile.challenge_chapter4.adapter.NotesAdapter
import com.punyacile.challenge_chapter4.data.NoteEntity
import com.punyacile.challenge_chapter4.data.NotePreferences
import com.punyacile.challenge_chapter4.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), NotesAdapter.ItemClickListener {

    private lateinit var notePreferences: NotePreferences
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        notePreferences = NotePreferences(requireContext())
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        if (notePreferences.getString("KEY_FILTER").isNullOrEmpty()) {
            viewModel.getAllNotes()
        } else if (notePreferences.getString("KEY_FILTER").equals("ASCENDING", true)) {
            viewModel.getAllNotesAsc()
        } else if (notePreferences.getString("KEY_FILTER").equals("DESCENDING", true)) {
            viewModel.getAllNotesDesc()
        }
        binding.fabAddData.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        viewModel.notes.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.apply {
                    emptyState.isVisible = false
                    notesRecyclerView.isVisible = true
                    notesRecyclerView.setHasFixedSize(true)
                    notesRecyclerView.adapter = NotesAdapter(it, this@HomeFragment)
                    notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                }
            } else {
                binding.apply {
                    emptyState.isVisible = true
                    notesRecyclerView.isVisible = false
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun edit(noteEntity: NoteEntity) {
        val bundle = Bundle().apply {
            putParcelable("NOTE_ENTITY", noteEntity)
        }
        findNavController().navigate(R.id.action_homeFragment_to_editFragment, bundle)
    }

    override fun delete(noteEntity: NoteEntity) {
        val bundle = Bundle().apply {
            putParcelable("NOTE_ENTITY", noteEntity)
        }
        findNavController().navigate(R.id.action_homeFragment_to_deleteFragment, bundle)
    }
}