package hu.ait.cardtransactions.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.ait.cardtransactions.data.AppDatabase
import hu.ait.cardtransactions.data.CardAndTransactionDao
import hu.ait.cardtransactions.repository.CardsAndTransactionsDbRepository
import hu.ait.cardtransactions.repository.CardsAndTransactionsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideMyModelDao(appDatabase: AppDatabase): CardAndTransactionDao {
        return appDatabase.cardsAndTransactionDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideCardsAndTransactionsRepository(cardsAndTransactionsDao: CardAndTransactionDao): CardsAndTransactionsDbRepository {
        return CardsAndTransactionsDbRepository(cardsAndTransactionsDao)
    }
}