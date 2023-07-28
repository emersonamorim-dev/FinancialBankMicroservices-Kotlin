package com.financialBank.BalanceService.controller

import org.springframework.web.bind.annotation.*
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import com.financialBank.BalanceService.repositories.BalanceUpdateRepository
import com.financialBank.BalanceService.data.BalanceUpdate




@RestController
@RequestMapping("/balance-updates")
class BalanceUpdateController(private val balanceUpdateService: BalanceUpdateService) {

    @GetMapping
    fun getAllBalanceUpdates(): List<BalanceUpdate> {
        return balanceUpdateService.getAllBalanceUpdates()
    }

    @GetMapping("/{id}")
    fun getBalanceUpdateById(@PathVariable id: String): BalanceUpdate {
        return balanceUpdateService.getBalanceUpdateById(id)
    }
}

@Service
class BalanceUpdateService(private val balanceUpdateRepository: BalanceUpdateRepository) {

    fun getAllBalanceUpdates(): List<BalanceUpdate> {
        return balanceUpdateRepository.findAll()
    }

    fun getBalanceUpdateById(id: String): BalanceUpdate {
        return balanceUpdateRepository.findById(id).orElseThrow { Exception("Atualização de saldo não encontrada") }
    }
}


