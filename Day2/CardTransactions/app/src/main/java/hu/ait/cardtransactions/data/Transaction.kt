package hu.ait.cardtransactions.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactiontable")
data class Transaction(
    @PrimaryKey(autoGenerate = true) var transactionId: Int? = 0,
    @ColumnInfo var cardId: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "amount") var amount: String
)