package orlo.karagulov.xml_mvvm.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import orlo.karagulov.xml_mvvm.model.Note
import orlo.karagulov.xml_mvvm.utils.REPOSITORY

class NoteViewModel(application: Application): AndroidViewModel(application) {

    fun delete(note: Note) {
        viewModelScope.launch {
            REPOSITORY.delete(note)
        }
    }

}