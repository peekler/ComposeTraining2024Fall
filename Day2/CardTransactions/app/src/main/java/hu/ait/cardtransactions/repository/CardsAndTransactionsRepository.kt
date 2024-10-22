package hu.ait.cardtransactions.repository

import hu.ait.cardtransactions.data.CardAndTransactionDao
import hu.ait.cardtransactions.data.CreditCard
import hu.ait.cardtransactions.data.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface CardsAndTransactionsRepository {
    val cards: Flow<List<CreditCard>>

    suspend fun addCard(creditCard: CreditCard)
    suspend fun deleteAllCards()
    suspend fun addTransaction(transaction: Transaction)
    suspend fun deleteAllTransactions()
}

class CardsAndTransactionsDbRepository @Inject constructor(
    private val cardAndTransactionDao: CardAndTransactionDao
) : CardsAndTransactionsRepository {

    override val cards: Flow<List<CreditCard>> =
        cardAndTransactionDao.getCreditCards().map { items -> items.map { it } }

    /*override val cards2: Flow<List<Pair<CreditCard, List<Transaction>>>> =
        cardAndTransactionDao.getCreditCards().flatMapLatest { creditCards ->
            combine(
                creditCards.map { creditCard ->
                    // For each credit card, fetch transactions asynchronously using Flow
                    cardAndTransactionDao.getTransactionsForCard(creditCard.cardId!!)
                        .map { transactions -> creditCard to transactions } // Pair credit card with its transactions
                }
            ) { pairs ->
                pairs.toList() // Collect all pairs of credit cards and their transactions
            }
        }*/


    override suspend fun addCard(creditCard: CreditCard) {
        cardAndTransactionDao.insertCreditCard(creditCard)
    }

    override suspend fun deleteAllCards() {
        cardAndTransactionDao.deleteAllCards()
    }

    override suspend fun addTransaction(transaction: Transaction) {
        cardAndTransactionDao.insertTransaction(transaction)
    }

    override suspend fun deleteAllTransactions() {
        cardAndTransactionDao.deleteAllTransactions()
    }
}