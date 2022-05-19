package com.darkaliens.mongo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Database {
  public static final String databaseName = "dark-aliens";
  private static final String uri = String.format("mongodb+srv://woyong:os7GyeIr0jyF5T8T@grape-upsell.fl7fn.mongodb.net/%s?retryWrites=true&w=majority", databaseName);
  public static final MongoClient mongoClient = MongoClients.create(uri);

  public static void connect() {
    MongoDatabase database = mongoClient.getDatabase(databaseName);

    try {
      Bson command = new BsonDocument("ping", new BsonInt64(1));
      Document commandResult = database.runCommand(command);
      System.out.println("Connected successfully to database server.");
    } catch (MongoException me) {
      System.err.println("An error occurred while attempting to run a command: " + me);
    }
  }

  public static MongoCollection<Document> getFlightsCollection () {
    return getCollection("flights");
  }

  public static MongoCollection<Document> getUsersCollection () {
    return getCollection("users");
  }

  public static MongoCollection<Document> getTestCollection() {
    return getCollection("test");
  }

  public static MongoCollection<Document> getTicketPurchasesCollection () {
    return getCollection("ticket_purchases");
  }

  public static MongoCollection<Document> getCollection (String collectionName) {
    MongoDatabase database = Database.mongoClient.getDatabase(databaseName);

    return database.getCollection(collectionName);
  }
}
