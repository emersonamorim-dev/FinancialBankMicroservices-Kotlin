package com.financialBank.BalanceService.services

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.bson.Document
import com.mongodb.client.MongoClients
import com.google.gson.Gson
import com.financialBank.BalanceService.data.BalanceUpdate
import java.util.Arrays
import java.util.Properties
import java.time.Duration


fun main() {
    val props = Properties()
    props["bootstrap.servers"] = "localhost:9092"
    props["group.id"] = "balance"
    props["enable.auto.commit"] = "true"
    props["auto.commit.interval.ms"] = "1000"
    props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    val consumer = KafkaConsumer<String, String>(props)
    consumer.subscribe(Arrays.asList("balance-updates"))

    val mongoClient = MongoClients.create()
    val database = mongoClient.getDatabase("balance")
    val collection = database.getCollection("updates")

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))
        for (record in records) {
            val balanceUpdate = parseBalanceUpdate(record.value())
            collection.insertOne(Document.parse(Gson().toJson(balanceUpdate)))
        }
    }
}

fun parseBalanceUpdate(data: String): BalanceUpdate {
    return Gson().fromJson(data, BalanceUpdate::class.java)
}
