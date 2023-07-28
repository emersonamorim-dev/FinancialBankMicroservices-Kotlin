package com.financialBank.BalanceService.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import com.financialBank.BalanceService.data.BalanceUpdate


@Repository
interface BalanceUpdateRepository : MongoRepository<BalanceUpdate, String> {
}
