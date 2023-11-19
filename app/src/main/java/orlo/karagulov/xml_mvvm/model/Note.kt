package orlo.karagulov.xml_mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo val title: String = "",
    @ColumnInfo val text: String = "",
    val idFirebase:String = ""
): Serializable