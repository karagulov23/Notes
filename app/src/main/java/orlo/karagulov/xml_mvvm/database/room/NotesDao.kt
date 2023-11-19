package orlo.karagulov.xml_mvvm.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import orlo.karagulov.xml_mvvm.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table")
     fun getAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

}