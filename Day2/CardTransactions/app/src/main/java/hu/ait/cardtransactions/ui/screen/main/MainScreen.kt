package hu.ait.cardtransactions.ui.screen.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.ait.cardtransactions.data.CreditCard
import hu.ait.cardtransactions.data.Transaction
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    var showAddCardDialog by remember { mutableStateOf(false) }
    var showAddTransactionDialog by remember { mutableStateOf(false) }
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()


    /*val cards = listOf(
        CreditCard(0, "John Doe", "12/24", "**** **** **** 1234"),
        CreditCard(1, "Jane Smith", "05/25", "**** **** **** 5678"),
        CreditCard(2, "Peter Jones", "08/26", "**** **** **** 9012")
    )*/
    val transactions = listOf(
        Transaction(0, 0,"Grocery Shopping", "2023-11-15", "-$50.00"),
        Transaction(1, 0,"Salary Deposit", "2023-11-10", "+$2000.00"),
        Transaction(2, 0, "Restaurant Bill", "2023-11-05", "-$35.50"),
        Transaction(3, 0, "Restaurant Bill", "2023-11-05", "-$35.50"),
        Transaction(4, 0, "Restaurant Bill", "2023-11-05", "-$35.50"),
        Transaction(5, 0, "Restaurant Bill", "2023-11-05", "-$35.50")
    )

    val snackbarHostState = remember { SnackbarHostState() }
    var selectedCardId by remember { mutableStateOf(1) }
    val coroutineScope = rememberCoroutineScope()



    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Bank App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                actions = {
                    IconButton(onClick = { mainViewModel.deleteAllCard() }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete all")
                    }
                    IconButton(onClick = { showAddCardDialog = true }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add card")
                    }
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (selectedCardId != -1) {
                    showAddTransactionDialog = true
                }
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.zIndex(0f)
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.AccountBox, contentDescription = "Transactions") },
                    selected = true,
                    onClick = { /* Handle Transactions action */ },
                    label = { Text("Transactions") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.MailOutline, contentDescription = "Summary") },
                    selected = false,
                    onClick = { /* Handle Summary action */ },
                    label = { Text("Summary") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Info, contentDescription = "Details") },
                    selected = false,
                    onClick = { /* Handle Details action */ },
                    label = { Text("Details") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is MainScreenUiState.Error -> {
                    Text("Cards not loaded ${(uiState as MainScreenUiState.Error).throwable.message}")
                }

                is MainScreenUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is MainScreenUiState.SuccessCards -> {
                    MainScreenContent(
                        (uiState as MainScreenUiState.SuccessCards).data,
                        transactions,
                        { cardId -> selectedCardId = cardId}
                    )
                }
            }
        }
    }

    if (showAddTransactionDialog) {
        TransactionDialog(
            selectedCardId,
            onAddTransaction = { transaction ->
                mainViewModel.addTransaction(transaction)
            },
            onCancel = {
                showAddTransactionDialog = false
            }
        )
    }

    if (showAddCardDialog) {
        CreditCardDialog(
            onAddCard = { creditCard ->
                mainViewModel.addCreditCard(creditCard)
            },
            onCancel = {
                showAddCardDialog = false
                coroutineScope.launch {
                    val action = snackbarHostState.showSnackbar(
                        message = "Show this Snackbar UI",
                        duration = SnackbarDuration.Short,
                        actionLabel = "UNDO",
                        withDismissAction = true
                    )

                    when (action) {
                        SnackbarResult.Dismissed -> {
                            //
                        }

                        SnackbarResult.ActionPerformed -> {
                            //
                        }
                    }
                }

            }
        )
    }
}

@Composable
private fun MainScreenContent(
    //cards: List<Pair<CreditCard, List<Transaction>>>,
    cards: List<CreditCard>,
    transactions: List<Transaction>,
    onCardChanged: (Int) -> Unit
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = { cards.size })
    LaunchedEffect(pagerState) {
        // Collect from the a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
            // Do something with each page change, for example:
            // viewModel.sendPageSelectedEvent(page)
            Toast.makeText(context, "$page", Toast.LENGTH_SHORT).show()
            if (cards.size>0) {
                onCardChanged(cards[page].cardId!!)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
            ) { page ->
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(
                                    Brush.linearGradient(
                                        listOf(
                                            Color.hsl(123f, 1f, 0.93f), Color.hsl(123f, 1f, 0.5f)
                                        )
                                    )
                                )
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                cards[page].holderName,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                "Issue Date: ${cards[page].issueDate}",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                "Card Number: ${cards[page].cardNumber}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                    }
                }
            }
        }
        PagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(transactions) { transaction ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(transaction.title, style = MaterialTheme.typography.bodyMedium)
                        Text(
                            transaction.date, style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                        Text(transaction.amount, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagerIndicator(
    pagerState: PagerState,
    modifier: Modifier
) {
    val currentPage by rememberUpdatedState(pagerState.currentPage)

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(5.dp)

            )
        }

    }
}
