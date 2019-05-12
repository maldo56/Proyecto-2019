package bean.scooterclient.database;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import obj.dto.DtoLocation;


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
    
    public Boolean mongo() throws Exception {
    	
    	try {
    		
    		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        	MongoDatabase database = mongoClient.getDatabase("mydb");
        	MongoCollection<Document> collection = database.getCollection("test");
        	
        	
                Document d = new Document().append("id", "Id")
                   .append("Campo1", "Algo");
                   
                collection.insertOne(d);
                
                return true;
    	} catch ( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    }
    

    public void servicioAddPunto(String guid, String alquilerGuid, float x, float y) throws Exception {
    	
    	try {
    		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        	MongoDatabase database = mongoClient.getDatabase("Proyecto-2019");
        	MongoCollection<Document> collection = database.getCollection("ScootersUbicaciones");
        	
            Document d = new Document()
    	           .append("scooterGuid", guid)
    	           .append("alquilerGuid", alquilerGuid)
    	           .append("lat", x)
    	           .append("lng", y);
               
            collection.insertOne(d);
    	} catch(Exception e) {
    		throw new Exception("Ha ocurrido un error");
    	}
    	
    }

    public List<DtoLocation> obtenerPuntos(String alquilerGuid) throws Exception {
    	
    	
    	try {
    		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        	MongoDatabase database = mongoClient.getDatabase("Proyecto-2019");
        	MongoCollection<Document> collection = database.getCollection("ScootersUbicaciones");
        	
        	BasicDBObject inQuery = new BasicDBObject();
        	inQuery.put("alquilerGuid", alquilerGuid);
        	
        	FindIterable<Document> puntos = collection.find(inQuery);
        	MongoCursor<Document> it = puntos.iterator();
        	
        	List<DtoLocation> list = new ArrayList<DtoLocation>();
        	DtoLocation location;
        	Document document;
        	
        	while ( it.hasNext() ) {
        		document = it.next();
        		
        		location = new DtoLocation();
        		location.setAlquilerGuid(document.getString("alquilerGuid"));
        		location.setScooterGuid(document.getString("scooterGuid"));
        		location.setLat(document.getDouble("lat").floatValue());
        		location.setLng(document.getDouble("lng").floatValue());
        		
        		list.add(location);
        	}
        	
        	return list;
    	} catch ( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    }
}
