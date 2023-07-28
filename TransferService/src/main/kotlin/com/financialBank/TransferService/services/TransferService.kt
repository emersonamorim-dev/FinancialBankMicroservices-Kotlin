package com.financialBank.TransferService.services

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.bson.Document
import com.mongodb.client.MongoClients
import com.google.gson.Gson
import com.financialBank.TransferService.model.Transaction
import java.util.Arrays
import java.util.Properties
import java.time.Duration


fun main() {
    val props = Properties()
    props["bootstrap.servers"] = "localhost:9092"
    props["group.id"] = "transfer"
    props["enable.auto.commit"] = "true"
    props["auto.commit.interval.ms"] = "1000"
    props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    val consumer = KafkaConsumer<String, String>(props)
    consumer.subscribe(Arrays.asList("transfer-transactions"))

    val mongoClient = MongoClients.create()
    val database = mongoClient.getDatabase("transfer")
    val collection = database.getCollection("transactions")

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))
        for (record in records) {
            val transaction = parseTransaction(record.value())
            collection.insertOne(Document.parse(Gson().toJson(transaction)))
        }
    }
}

fun parseTransaction(data: String): Transaction {
    return Gson().fromJson(data, Transaction::class.java)
}

