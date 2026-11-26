package com.example.aldo_tie_pertemuan3.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aldo_tie_pertemuan3.data.AppDatabase
import com.example.aldo_tie_pertemuan3.data.entity.NoteEntity
import com.example.aldo_tie_pertemuan3.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    /** Variabel database & Adapter **/
    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase
    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = "Note"

        /** Inisialisasi AppDatabase & Adapter **/
        db = AppDatabase.getInstance(requireContext())
        // adapter = NoteAdapter(notes)
        adapter = NoteAdapter(notes, this) // tambah parameter this

        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        /** Tambah ini sebagai garis pemisah **/
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.rvNotes.addItemDecoration(dividerItemDecoration)

        fetchNotes()

        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(requireContext(), NoteFormActivity::class.java))
        }
    }

    private fun fetchNotes() {
        viewLifecycleOwner.lifecycleScope.launch {
            val data = db.noteDao().getAll() // pemanggilan query
            notes.clear()
            notes.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    // Dipanggil lagi setelah NoteFormActivity ditutup, sehingga data terbaru tampil
    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    fun deleteNote(note: NoteEntity) {
        lifecycleScope.launch {
            db.noteDao().delete(note) //Hapus Note
            fetchNotes()              //Fetch lagi data notes terbaru
        }
    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }