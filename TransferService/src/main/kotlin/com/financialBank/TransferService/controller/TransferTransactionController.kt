package com.financialBank.TransferService.controller

import org.springframework.web.bind.annotation.*
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import com.financialBank.TransferService.repositories.TransferTransactionRepository
import com.financialBank.TransferService.model.Transaction



@RestController
@RequestMapping("/transfer-transactions")
class TransferTransactionController(private val transferTransactionService: TransferTransactionService) {

    @GetMapping
    fun getAllTransferTransactions(): List<Transaction> {
        return transferTransactionService.getAllTransferTransactions()
    }

    @GetMapping("/{id}")
    fun getTransferTransactionById(@PathVariable id: String): Transaction {
        return transferTransactionService.getTransferTransactionById(id)
    }
}

@Service
class TransferTransactionService(private val transferTransactionRepository: TransferTransactionRepository) {

    fun getAllTransferTransactions(): List<Transaction> {
        return transferTransactionRepository.findAll()
    }

    fun getTransferTransactionById(id: String): Transaction {
        return transferTransactionRepository.findById(id).orElseThrow { Exception("Transação de transferência não encontrada") }
    }
}

