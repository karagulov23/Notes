package orlo.karagulov.xml_mvvm.screens.start

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import orlo.karagulov.xml_mvvm.database.firebase.FirebaseRepository
import orlo.karagulov.xml_mvvm.database.room.NotesRoomDB
import orlo.karagulov.xml_mvvm.database.room.RoomRepository
import orlo.karagulov.xml_mvvm.utils.REPOSITORY
import orlo.karagulov.xml_mvvm.utils.TYPE_FIREBASE
import orlo.karagulov.xml_mvvm.utils.TYPE_ROOM

class StartViewModel(application: Application): AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase(type: String){
        when(type) {
            TYPE_ROOM -> {
                val notesDao = NotesRoomDB.getInstance(mContext).notesDao()
                REPOSITORY = RoomRepository(notesDao)
            }

            TYPE_FIREBASE -> {
                REPOSITORY = FirebaseRepository()
                REPOSITORY.connectToDatabase()
            }
        }
    }

}