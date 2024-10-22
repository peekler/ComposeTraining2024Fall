package hu.ait.cardtransactions.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cardtable")
data class CreditCard(
    @PrimaryKey(autoGenerate = true) var cardId: Int? = 0,
    @ColumnInfo(name = "holder_name") var holderName: String,
    @ColumnInfo(name = "issue_date") var issueDate: String,
    @ColumnInfo(name = "card_number") var cardNumber: String
)
