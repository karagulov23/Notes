package orlo.karagulov.xml_mvvm.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import orlo.karagulov.xml_mvvm.R
import orlo.karagulov.xml_mvvm.databinding.FragmentMainBinding
import orlo.karagulov.xml_mvvm.model.Note
import orlo.karagulov.xml_mvvm.utils.APP_ACTIVITY
import orlo.karagulov.xml_mvvm.utils.AppPreferences

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinind get() = _binding!!
    private lateinit var mViewModel: MainViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return mBinind.root
    }

    override fun onStart() {
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mAdapter = MainAdapter()
        mRecyclerView = mBinind.rcNote
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mViewModel.allNotes.observe(viewLifecycleOwner) {
            mAdapter.setData(it.asReversed())
        }

        mBinind.fbaAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addNoteFragment)
        }
        super.onStart()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun click(note: Note) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnDelete -> {
                mViewModel.signOut()
                AppPreferences.setInitUser(false)
                findNavController().navigate(R.id.action_mainFragment_to_startFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}