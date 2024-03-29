package com.lalee.level5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lalee.level5task1.R
import com.lalee.level5task1.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_notepad.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NotepadFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notepad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddNoteResult()
    }

    private fun observeAddNoteResult() {
        viewModel.note.observe(viewLifecycleOwner, { note ->
            note?.let {
                tvNoteTitle.text = it.title
                tvLastUpdated.text = String.format("Last updated: %s", it.lastUpdated.toString())
                tvNoteText.text = it.text
            }
        })
    }
}