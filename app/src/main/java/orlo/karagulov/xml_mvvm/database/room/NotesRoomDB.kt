package orlo.karagulov.xml_mvvm.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import orlo.karagulov.xml_mvvm.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesRoomDB:RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var instance: RoomDatabase?= null

        @Synchronized
        fun getInstance(context: Context): NotesRoomDB{
            return if (instance == null){
                instance = Room.databaseBuilder(
                    context,
                    NotesRoomDB::class.java,
                    "notes_database"
                ).build()
                instance as NotesRoomDB
            } else instance as NotesRoomDB
        }
    }


}