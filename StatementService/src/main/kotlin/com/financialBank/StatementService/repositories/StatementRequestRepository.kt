package com.financialBank.StatementService.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import com.financialBank.StatementService.data.StatementRequest




@Repository
interface StatementRequestRepository : MongoRepository<StatementRequest, String> {
}
