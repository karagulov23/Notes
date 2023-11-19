package orlo.karagulov.xml_mvvm.screens.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import orlo.karagulov.xml_mvvm.utils.REPOSITORY

class AddNoteViewModel(application: Application): AndroidViewModel(application) {

    fun insert(note: orlo.karagulov.xml_mvvm.model.Note) {
        viewModelScope.launch {
            REPOSITORY.insert(note)
        }
    }

}