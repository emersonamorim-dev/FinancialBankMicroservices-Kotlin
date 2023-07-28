package com.financialBank.StatementService.controller

import org.springframework.web.bind.annotation.*
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import com.financialBank.StatementService.repositories.StatementRequestRepository
import com.financialBank.StatementService.data.StatementRequest


@RestController
@RequestMapping("/statement-requests")
class StatementRequestController(private val statementRequestService: StatementRequestService) {

    @GetMapping
    fun getAllStatementRequests(): List<StatementRequest> {
        return statementRequestService.getAllStatementRequests()
    }

    @GetMapping("/{id}")
    fun getStatementRequestById(@PathVariable id: String): StatementRequest {
        return statementRequestService.getStatementRequestById(id)
    }
}

@Service
class StatementRequestService(private val statementRequestRepository: StatementRequestRepository) {

    fun getAllStatementRequests(): List<StatementRequest> {
        return statementRequestRepository.findAll()
    }

    fun getStatementRequestById(id: String): StatementRequest {
        return statementRequestRepository.findById(id).orElseThrow { Exception("Solicitação de extrato não encontrada") }
    }
}


