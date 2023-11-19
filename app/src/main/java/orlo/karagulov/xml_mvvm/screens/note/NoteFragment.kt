package orlo.karagulov.xml_mvvm.screens.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import orlo.karagulov.xml_mvvm.R
import orlo.karagulov.xml_mvvm.databinding.FragmentNoteBinding
import orlo.karagulov.xml_mvvm.model.Note


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val mBinind get() = _binding!!
    private lateinit var mCurrentNote: Note
    private lateinit var mViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(layoutInflater,container,false)
        mCurrentNote = arguments?.getSerializable("note") as Note
        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        return mBinind.root
    }

    override fun onStart() {
        setHasOptionsMenu(true)
        mBinind.tvNoteName.text = mCurrentNote.title
        mBinind.tvNoteText.text = mCurrentNote.text
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnDelete -> {
                mViewModel.delete(mCurrentNote)
                findNavController().navigate(R.id.action_noteFragment_to_mainFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}