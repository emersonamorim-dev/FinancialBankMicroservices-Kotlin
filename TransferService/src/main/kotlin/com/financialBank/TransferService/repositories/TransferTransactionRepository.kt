package com.financialBank.TransferService.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import com.financialBank.TransferService.model.Transaction



@Repository
interface TransferTransactionRepository : MongoRepository<Transaction, String> {
}
