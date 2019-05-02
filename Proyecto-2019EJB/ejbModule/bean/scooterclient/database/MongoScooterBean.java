package bean.scooterclient.database;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**probaste subir la clase al wildfly 
 * 
 * Agarra todas las librerias del web derecho 
 * no se que le pasa
 * 
 * Session Bean implementation class MongoScooterBean
 */
@Stateless
@LocalBean
public class MongoScooterBean implements MongoScooterBeanLocal {

    /**
     * Default constructor. 
     */
    public MongoScooterBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String add(String user) {
    	
    //	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//    	
//    	MongoDatabase database = mongoClient.getDatabase("mydb");
//    	MongoCollection<Object> collection = database.getCollection("test");
//    	
    	
    	
    	return "Mongo! " + user;
    }
    
    public Boolean mongo() {
    	
    
    		
    	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    	MongoDatabase database = mongoClient.getDatabase("mydb");
    	MongoCollection<Document> collection = database.getCollection("test");
    	
    	
            Document d = new Document().append("id", "Id")
               .append("Campo1", "Algo");
               
            collection.insertOne(d);
         
    	
    	return true;
    }
    

}
