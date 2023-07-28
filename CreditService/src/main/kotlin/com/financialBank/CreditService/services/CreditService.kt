package com.financialBank.CreditService.services

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.bson.Document
import com.mongodb.client.MongoClients
import com.google.gson.Gson
import com.financialBank.CreditService.data.Transaction
import java.util.Arrays
import java.util.Properties
import java.time.Duration



fun main() {
    val props = Properties()
    props["bootstrap.servers"] = "localhost:9092"
    props["group.id"] = "credit"
    props["enable.auto.commit"] = "true"
    props["auto.commit.interval.ms"] = "1000"
    props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    val consumer = KafkaConsumer<String, String>(props)
    consumer.subscribe(Arrays.asList("credit-transactions"))

    val mongoClient = MongoClients.create()
    val database = mongoClient.getDatabase("credit")
    val collection = database.getCollection("transactions")

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))
        for (record in records) {
            val transaction = parseCreditTransactionFromJson(record.value())
            collection.insertOne(Document.parse(Gson().toJson(transaction)))
        }
    }
}

fun parseCreditTransactionFromJson(data: String): Transaction {
    return Gson().fromJson(data, Transaction::class.java)
}

