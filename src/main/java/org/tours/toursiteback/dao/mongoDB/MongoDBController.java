package org.tours.toursiteback.dao.mongoDB;

import org.bson.Document;

import static org.tours.toursiteback.manager.ImageManager.getImageAsBytesFromBase64;

public class MongoDBController implements Controller {
    private final MongoDBConnector mongoDBConnector = new MongoDBConnector();

    @Override
    public void addTourCardImage(int id, String base64Image) {
        byte[] imageData = getImageAsBytesFromBase64(base64Image);

        Document document = new Document("id", "image").append(String.valueOf(id), base64Image);

        mongoDBConnector.getCollection().insertOne(document);
    }
}
