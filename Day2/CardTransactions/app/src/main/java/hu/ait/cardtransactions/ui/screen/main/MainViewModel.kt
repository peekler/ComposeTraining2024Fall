package hu.ait.cardtransactions.ui.screen.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.cardtransactions.data.CreditCard
import hu.ait.cardtransactions.data.Transaction
import hu.ait.cardtransactions.repository.CardsAndTransactionsDbRepository
import hu.ait.cardtransactions.repository.CardsAndTransactionsRepository
import hu.ait.cardtransactions.ui.screen.main.MainScreenUiState.SuccessCards
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cardsAndTransactionsDbRepository: CardsAndTransactionsDbRepository
) : ViewModel() {

    val uiState: StateFlow<MainScreenUiState> = cardsAndTransactionsDbRepository
        .cards.map<List<CreditCard>, MainScreenUiState>(::SuccessCards)
        .catch { emit(MainScreenUiState.Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MainScreenUiState.Loading)

    /*val uiState: StateFlow<MainScreenUiState> = cardsAndTransactionsDbRepository
        .cards.map<List<Pair<CreditCard, List<Transaction>>>, MainScreenUiState>(::SuccessCards)
        .catch { emit(MainScreenUiState.Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MainScreenUiState.Loading)*/

    fun addCreditCard(creditCard: CreditCard) {
        viewModelScope.launch {
            cardsAndTransactionsDbRepository.addCard(creditCard)
        }
    }

    fun deleteAllCard() {
        viewModelScope.launch {
            cardsAndTransactionsDbRepository.deleteAllCards()
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            cardsAndTransactionsDbRepository.addTransaction(transaction)
        }
    }

    fun deleteAllTransactions() {
        viewModelScope.launch {
            cardsAndTransactionsDbRepository.deleteAllTransactions()
        }
    }
}

sealed interface MainScreenUiState {
    object Loading : MainScreenUiState
    data class Error(val throwable: Throwable) : MainScreenUiState
    //data class SuccessCards(val data: List<Pair<CreditCard, List<Transaction>>>) : MainScreenUiState
    data class SuccessCards(val data: List<CreditCard>) : MainScreenUiState
}