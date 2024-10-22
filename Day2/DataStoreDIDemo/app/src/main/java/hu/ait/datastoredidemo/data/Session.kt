package hu.ait.datastoredidemo.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Session @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        const val DATA = "DataDs"
        private const val NAME = "Name"
        val name = stringPreferencesKey(NAME)
    }

    fun getUserName(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[name] ?: ""
        }
    }

    suspend fun setUserName(userName: String) {
        dataStore.edit { preference ->
            preference[name] = userName
        }
    }
}