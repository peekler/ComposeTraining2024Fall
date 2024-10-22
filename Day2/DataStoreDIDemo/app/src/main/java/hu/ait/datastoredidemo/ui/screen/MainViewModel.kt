package hu.ait.datastoredidemo.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.datastoredidemo.data.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val session: Session
) : ViewModel() {

    fun getUserName(): Flow<String> {
        return session.getUserName()
    }



    fun updateUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            session.setUserName(userName)
        }
    }
}