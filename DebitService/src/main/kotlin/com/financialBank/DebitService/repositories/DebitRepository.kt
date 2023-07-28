package com.financialBank.DebitService.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import com.financialBank.DebitService.model.Transaction


@Repository
interface DebitRepository : MongoRepository<Transaction, String> {
}
