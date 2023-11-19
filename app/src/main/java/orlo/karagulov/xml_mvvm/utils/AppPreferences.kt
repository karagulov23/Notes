package orlo.karagulov.xml_mvvm.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private const val INIT_USER = "init user"
    private const val TYPE_DB = "type_db"
    private const val NAME_PREF = "preferences"

    private lateinit var mPreferences: SharedPreferences

    fun getPreferences(context: Context): SharedPreferences{
        mPreferences = context.getSharedPreferences(NAME_PREF,Context.MODE_PRIVATE)
        return mPreferences
    }

    fun setInitUser(init: Boolean){
        mPreferences.edit()
            .putBoolean(INIT_USER,init)
            .apply()
    }

    fun setTypeDatabase(type: String){
        mPreferences.edit()
            .putString(TYPE_DB,type)
            .apply()
    }

    fun getInitUser(): Boolean {
        return mPreferences.getBoolean(INIT_USER, false)
    }

    fun getTypeDatabase(): String {
        return mPreferences.getString(TYPE_DB, TYPE_ROOM).toString()
    }

}