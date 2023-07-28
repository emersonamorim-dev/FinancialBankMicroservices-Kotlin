package com.financialBank.DebitService.repositories

import com.financialBank.DebitService.model.Transaction
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : MongoRepository<Transaction, String> {
}