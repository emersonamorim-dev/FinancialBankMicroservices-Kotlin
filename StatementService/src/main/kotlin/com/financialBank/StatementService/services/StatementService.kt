package com.financialBank.StatementService.services

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.bson.Document
import com.mongodb.client.MongoClients
import com.google.gson.Gson
import com.financialBank.StatementService.data.StatementRequest


import java.util.Arrays
import java.util.Properties
import java.time.Duration



fun main() {
    val props = Properties()
    props["bootstrap.servers"] = "localhost:9092"
    props["group.id"] = "statement"
    props["enable.auto.commit"] = "true"
    props["auto.commit.interval.ms"] = "1000"
    props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    val consumer = KafkaConsumer<String, String>(props)
    consumer.subscribe(Arrays.asList("statement-requests"))

    val mongoClient = MongoClients.create()
    val database = mongoClient.getDatabase("statement")
    val collection = database.getCollection("requests")

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))
        for (record in records) {
            val statementRequest = parseStatementRequest(record.value())
            collection.insertOne(Document.parse(Gson().toJson(statementRequest)))
        }
    }
}

fun parseStatementRequest(data: String): StatementRequest {
    return Gson().fromJson(data, StatementRequest::class.java)
}

