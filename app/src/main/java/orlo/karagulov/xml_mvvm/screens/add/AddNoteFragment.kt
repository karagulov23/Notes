package orlo.karagulov.xml_mvvm.screens.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import orlo.karagulov.xml_mvvm.R
import orlo.karagulov.xml_mvvm.databinding.FragmentAddNoteBinding
import orlo.karagulov.xml_mvvm.databinding.FragmentMainBinding
import orlo.karagulov.xml_mvvm.model.Note
import orlo.karagulov.xml_mvvm.screens.main.MainViewModel


class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val mBinind get() = _binding!!
    private lateinit var mViewModel: AddNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        return mBinind.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        mViewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        mBinind.btnAddNote.setOnClickListener {
            val title = mBinind.edNoteName.text.toString()
            val text = mBinind.edNoteText.text.toString()
            if (title.isEmpty() || text.isEmpty()) {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
            }
            else{
                mViewModel.insert(Note(title = title, text = text))
                findNavController().navigate(R.id.action_addNoteFragment_to_mainFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}