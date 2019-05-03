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
public class MongoBean implements MongoBeanLocal {

    /**
     * Default constructor. 
     */
    public MongoBean() {
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
    

    public void servicioAddPunto(String guid, float x, float y) {
    	
    	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    	MongoDatabase database = mongoClient.getDatabase("Proyecto-2019");
    	MongoCollection<Document> collection = database.getCollection("ScootersUbicaciones");
    	
    	
        Document d = new Document()
	           .append("ScooterGuid", guid)
	           .append("x", x)
	           .append("y", y);
           
        collection.insertOne(d);
            
    }
}
