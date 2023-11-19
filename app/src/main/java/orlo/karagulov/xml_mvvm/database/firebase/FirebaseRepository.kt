package orlo.karagulov.xml_mvvm.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import orlo.karagulov.xml_mvvm.database.DatabaseRepository
import orlo.karagulov.xml_mvvm.model.Note
import orlo.karagulov.xml_mvvm.utils.AUTH
import orlo.karagulov.xml_mvvm.utils.AppPreferences
import orlo.karagulov.xml_mvvm.utils.CURRENT_ID
import orlo.karagulov.xml_mvvm.utils.EMAIL
import orlo.karagulov.xml_mvvm.utils.ID_FIREBASE
import orlo.karagulov.xml_mvvm.utils.PASSWORD
import orlo.karagulov.xml_mvvm.utils.REF_DATABASE
import orlo.karagulov.xml_mvvm.utils.TEXT
import orlo.karagulov.xml_mvvm.utils.TITLE

class FirebaseRepository: DatabaseRepository {


    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<Note>> = AllNotesLiveData()

    override suspend fun insert(note: Note) {
        Log.d("SULT", "insert scope")
        val idNote = REF_DATABASE.push().key.toString()
        Log.d("SULT", idNote)
        val mapNote = hashMapOf<String,Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[TITLE] = note.title
        mapNote[TEXT] = note.text

        val temp = Note(id = 1, title = "FIREBASE", text = "firebase text")

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener {
            }
            .addOnFailureListener{
            }
    }

    override suspend fun delete(note: Note) {
        REF_DATABASE.child(note.idFirebase).removeValue()
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

    override fun connectToDatabase() {

        if (AppPreferences.getInitUser()){
            initRefs()
        }
        else {
            initRefs()
            AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    Log.d("FIREBASE", "USER AUTHENTICATION")
                }
                .addOnFailureListener{
                    AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnSuccessListener {
                        }
                        .addOnFailureListener {
                        }
                }
        }


    }

    private fun initRefs() {
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(CURRENT_ID)
    }

    override fun signOut() {
        AUTH.signOut()
    }
}