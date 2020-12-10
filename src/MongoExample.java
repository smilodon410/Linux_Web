import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClientException;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoExample {

	public static void main(String[] args) {
		mongoConnection("lol", "challenger" );
	}
	
	// 접속 
	// 인자는 db, collections 순서
	private static void mongoConnection(String mydb, String mycol) {
		// 지정 IP mongo db 접속
		MongoClient mongoClient = MongoClients.create(
				MongoClientSettings.builder()
				.applyToClusterSettings(builder ->
				builder.hosts(Arrays.asList(new ServerAddress("192.168.56.100")))).build());

		try {
			// 지정 db 접속
			// db 이름이 없을 경우 새롭게 생성하게 됨
			MongoDatabase database = mongoClient.getDatabase(mydb);
			
			// collection 접근
			// collention 이 없을 경우 새롭게 생성하게 됨 
			MongoCollection<Document> collection = database.getCollection(mycol);
			
			System.out.println("연결 성공");
			
			// 값 뽑기
//			FindIterable<Document> it = collection.find();
//			
//			for(Document doc: it) {
//				System.out.println(doc.get("gameId"));	//	필드는 get으로 
//				
//			}
			
			
		} catch (MongoClientException e) {
			e.printStackTrace();
		} finally {
			try {
				mongoClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
