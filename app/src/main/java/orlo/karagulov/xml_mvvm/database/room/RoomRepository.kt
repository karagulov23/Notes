package orlo.karagulov.xml_mvvm.database.room

import androidx.lifecycle.LiveData
import orlo.karagulov.xml_mvvm.database.DatabaseRepository
import orlo.karagulov.xml_mvvm.model.Note

class RoomRepository(private val notesDao: NotesDao): DatabaseRepository {

    override val allNotes: LiveData<List<Note>>
        get() = notesDao.getAllNotes()

    override suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    override suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    override fun signOut() {
        super.signOut()
    }

}