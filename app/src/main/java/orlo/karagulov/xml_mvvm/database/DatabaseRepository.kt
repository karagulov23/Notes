package orlo.karagulov.xml_mvvm.database

import androidx.lifecycle.LiveData
import orlo.karagulov.xml_mvvm.model.Note

interface DatabaseRepository {

    val allNotes: LiveData<List<Note>>

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)

    // For Firebase
    fun connectToDatabase(){}
    fun signOut(){}

}