package org.tours.toursiteback.dao.mongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;

@Getter
public class MongoDBConnector {
    private final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    private final MongoDatabase tours = mongoClient.getDatabase("tours");

    private final MongoCollection<Document> collection = tours.getCollection("tour_card_images");
}
