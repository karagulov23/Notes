package orlo.karagulov.xml_mvvm.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import orlo.karagulov.xml_mvvm.MainActivity
import orlo.karagulov.xml_mvvm.R
import orlo.karagulov.xml_mvvm.databinding.FragmentStartBinding
import orlo.karagulov.xml_mvvm.utils.APP_ACTIVITY
import orlo.karagulov.xml_mvvm.utils.AppPreferences
import orlo.karagulov.xml_mvvm.utils.EMAIL
import orlo.karagulov.xml_mvvm.utils.PASSWORD
import orlo.karagulov.xml_mvvm.utils.TYPE_FIREBASE
import orlo.karagulov.xml_mvvm.utils.TYPE_ROOM

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        mViewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        if(AppPreferences.getInitUser()) {
            mViewModel.initDatabase(AppPreferences.getTypeDatabase())
            findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }
        else {
            init()
        }
    }

    private fun init() {

        mBinding.btnStartRoom.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM)
            AppPreferences.setInitUser(true)
            AppPreferences.setTypeDatabase(TYPE_ROOM)
            findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }

        mBinding.btnStartFirebase.setOnClickListener {
            mBinding.tvEmail.visibility = View.VISIBLE
            mBinding.tvPassword.visibility = View.VISIBLE
            mBinding.etEmail.visibility = View.VISIBLE
            mBinding.etPassword.visibility = View.VISIBLE
            mBinding.btnLogin.visibility = View.VISIBLE
        }

        mBinding.btnLogin.setOnClickListener {
            val inputEmail = mBinding.etEmail.text.toString()
            val inputPassword = mBinding.etPassword.text.toString()

            if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()) {
                EMAIL = inputEmail
                PASSWORD = inputPassword
                mViewModel.initDatabase(TYPE_FIREBASE)
                AppPreferences.setInitUser(true)
                AppPreferences.setTypeDatabase(TYPE_FIREBASE)
                findNavController().navigate(R.id.action_startFragment_to_mainFragment)
            } else {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}