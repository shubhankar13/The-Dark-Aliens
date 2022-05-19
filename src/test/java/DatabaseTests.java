import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatabaseTests {
  public static final String databaseName = "dark-aliens";
  private static final String uri = String.format("mongodb+srv://woyong:os7GyeIr0jyF5T8T@grape-upsell.fl7fn.mongodb.net/%s?retryWrites=true&w=majority", databaseName);
  public static final MongoClient mongoClient = MongoClients.create(uri);
  MongoDatabase database = mongoClient.getDatabase(databaseName);

  @Test
  @DisplayName("Should assert that creation of user was acknowledged.")
  void shouldSuccessfullyCreateUser() {
    MongoCollection<Document> collection = database.getCollection("users");
    Document document = new Document();
    InsertOneResult result = collection.insertOne(document
                                                    .append("first_name", "John")
                                                    .append("last_name", "Doe")
                                                    .append("email", "johndoe@gmail.com")
                                                    .append("token", "123455")
                                                    .append("refresh_token", "123456")
                                                    .append("uid", "123454")
    );

    Assertions.assertTrue(result.wasAcknowledged());
  }

  @Test
  @DisplayName("Should assert that purchase of a ticket was acknowledged.")
  void shouldSuccessfullyPurchaseATicket() {
    MongoCollection<Document> collection = database.getCollection("tickets");
    Document ticketPurchaseDocument = new Document();
    InsertOneResult result = collection.insertOne(ticketPurchaseDocument
                                                    .append("ticket_id", new ObjectId())
                                                    .append("uid", new ObjectId()));

    Assertions.assertTrue(result.wasAcknowledged());
  }
}
