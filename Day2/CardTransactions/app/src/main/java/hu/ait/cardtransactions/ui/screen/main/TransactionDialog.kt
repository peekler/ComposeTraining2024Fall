package hu.ait.cardtransactions.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import hu.ait.cardtransactions.data.CreditCard
import hu.ait.cardtransactions.data.Transaction


@Composable
fun TransactionDialog(
    selectedCardId: Int,
    onAddTransaction: (Transaction) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf("Item") }
    var date by remember { mutableStateOf("22/10/2024") }
    var amount by remember { mutableStateOf("100") }


    Dialog(onDismissRequest = {
        onCancel()
    }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    "Add Transaction",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    label = { Text("Title name") },
                    value = "$title",
                    onValueChange = { title = it })
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    label = { Text("Date") },
                    value = "$date",
                    onValueChange = { date = it })
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    label = { Text("Amount") },
                    value = "$amount",
                    onValueChange = { amount = it })

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        onAddTransaction(
                            Transaction(
                                null, selectedCardId, title, date, amount
                            )
                        )

                        onCancel()
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}