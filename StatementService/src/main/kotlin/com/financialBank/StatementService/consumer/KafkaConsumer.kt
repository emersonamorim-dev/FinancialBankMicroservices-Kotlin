package com.financialBank.StatementService.consumer

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.bson.Document
import org.json.JSONObject
import com.mongodb.client.MongoClients
import java.util.*
import java.time.Duration
import java.util.Properties

fun main() {
    val props = Properties()
    props["bootstrap.servers"] = "localhost:9092"
    props["group.id"] = "test"
    props["enable.auto.commit"] = "true"
    props["auto.commit.interval.ms"] = "1000"
    props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
    val consumer = KafkaConsumer<String, String>(props)
    consumer.subscribe(listOf("financial-data"))

    val mongoClient = MongoClients.create()
    val database = mongoClient.getDatabase("test")
    val collection = database.getCollection("financialData")

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))
        for (record in records) {
            val financialData = parseFinancialData(record.value())
            collection.insertOne(Document.parse(financialData.toJson()))
        }
    }
}

fun parseFinancialData(data: String): LocalFinancialData {
    val jsonData = JSONObject(data)
    return LocalFinancialData(jsonData) 
}

data class LocalFinancialData(private val jsonData: JSONObject) {
    fun toJson(): String {
        return jsonData.toString()
    }
}
