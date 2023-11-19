package orlo.karagulov.xml_mvvm.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import orlo.karagulov.xml_mvvm.utils.REPOSITORY

class MainViewModel(application: Application): AndroidViewModel(application) {

    val allNotes = REPOSITORY.allNotes

    fun signOut(){
        REPOSITORY.signOut()
    }

}