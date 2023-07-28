package com.financialBank.CreditService.repositories

import com.financialBank.CreditService.model.Transaction
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditTransactionRepository : MongoRepository<Transaction, String>{
}
