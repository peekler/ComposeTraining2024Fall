package hu.ait.cardtransactions.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CardAndTransactionDao {
    @Insert
    suspend fun insertCreditCard(creditCard: CreditCard)

    @Query("SELECT * FROM cardtable")
    fun getCreditCards(): Flow<List<CreditCard>>

    @Query("DELETE FROM cardtable")
    suspend fun deleteAllCards()

    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactiontable")
    fun getTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM transactiontable WHERE cardId = :cardId")
    fun getTransactionsForCard(cardId: Int): Flow<List<Transaction>>

    @Query("DELETE FROM transactiontable")
    suspend fun deleteAllTransactions()
}