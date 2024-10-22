package hu.ait.cardtransactions.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import hu.ait.cardtransactions.data.CreditCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCardDialog(
    onAddCard: (CreditCard) -> Unit,
    onCancel: () -> Unit
) {
    var holderName by remember { mutableStateOf("John Doe") }
    var cardNumber by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val issueDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    // Making XXXX-XXXX-XXXX-XXXX string.
    val creditCardOffsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }

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
                    "Add Todo",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    label = { Text("Holder name") },
                    value = "$holderName",
                    onValueChange = { holderName = it })
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    label = { Text("Issue date") },
                    value = "$issueDate",
                    onValueChange = { },
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = !showDatePicker }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select date"
                            )
                        }
                    }

                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Card number") },
                    value = cardNumber,
                    onValueChange = {
                        cardNumber = it.take(16)
                    },
                    visualTransformation = { text ->
                        val trimmed =
                            if (text.text.length >= 16) text.text.substring(0..15) else text.text
                        var out = ""

                        for (i in trimmed.indices) {
                            out += trimmed[i]
                            if (i % 4 == 3 && i != 15) out += "-"
                        }
                        TransformedText(
                            AnnotatedString(out),
                            creditCardOffsetMapping
                        )
                    }
                )


                if (showDatePicker) {
                    Dialog(
                        onDismissRequest = { showDatePicker = false }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            DatePicker(
                                state = datePickerState,
                                showModeToggle = false
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        onAddCard(
                            CreditCard(null,
                                holderName, issueDate, cardNumber
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
