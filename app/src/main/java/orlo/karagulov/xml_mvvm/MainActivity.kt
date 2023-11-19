package orlo.karagulov.xml_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import orlo.karagulov.xml_mvvm.databinding.ActivityMainBinding
import orlo.karagulov.xml_mvvm.utils.APP_ACTIVITY
import orlo.karagulov.xml_mvvm.utils.AppPreferences


class MainActivity : AppCompatActivity() {

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    lateinit var navController: NavController
    private var _binding:ActivityMainBinding? = null
    private val mBinding get() = _binding ?: throw IllegalStateException("binding for MainActivityBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        mToolbar = mBinding.toolbar
        navController = Navigation.findNavController(this, R.id.fragmentContainer)
        setSupportActionBar(mToolbar)
        title = "Notes"
        AppPreferences.getPreferences(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}