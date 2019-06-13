package bean.database.mongo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import obj.dto.DtoHistorialTarifa;
import obj.dto.DtoLocation;
import obj.dto.DtoParm;


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
    
    public Boolean addRegistroParametros(DtoParm parm, String oldValue, String admin) throws Exception {
    	
    	try {
    		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        	MongoDatabase database = mongoClient.getDatabase("Proyecto-2019");
        	MongoCollection<Document> collection = database.getCollection("RegistroParametros");
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss zzz");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
			
			System.out.println("Forat GTM: : " + sdf.format(new Timestamp(System.currentTimeMillis())));
			
			Date parsedDate = sdf.parse(sdf.format(new Timestamp(System.currentTimeMillis())));
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        	
        	
            Document d = new Document()
    	           .append("parametro", parm.getCode())
    	           .append("admin", admin)
    	           .append("oldValue", oldValue)
    	           .append("newValue", parm.getValue())
    	           .append("timestamp", timestamp);
               
            collection.insertOne(d);
            
            return true;
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
    
    
    public List<DtoLocation> ultimosNPuntos(int cant, String scooterGuid) throws Exception {
    	
    	try {
    		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        	MongoDatabase database = mongoClient.getDatabase("Proyecto-2019");
        	MongoCollection<Document> collection = database.getCollection("ScootersUbicaciones");
        	
        	BasicDBObject inQuery = new BasicDBObject();
        	inQuery.put("scooterGuid", scooterGuid);
        	
        	FindIterable<Document> puntos = collection.find(inQuery);
        	MongoCursor<Document> it = puntos.iterator();
        	
        	List<DtoLocation> list = new ArrayList<DtoLocation>();
        	DtoLocation location;
        	Document document;
        	
        	int count = 0;
        	
        	while ( it.hasNext() ) {
        		if ( count < 5 ) {
        			document = it.next();
            		
            		location = new DtoLocation();
            		location.setAlquilerGuid(document.getString("alquilerGuid"));
            		location.setScooterGuid(document.getString("scooterGuid"));
            		location.setLat(document.getDouble("lat").floatValue());
            		location.setLng(document.getDouble("lng").floatValue());
            		
            		list.add(location);
        		} else {
        			break;
        		}
        	}
        	
        	return list;
    	} catch ( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    }
    
    
    public List<DtoHistorialTarifa> historialParametro(String parmCode, Timestamp inicio, Timestamp fin) throws Exception {

    	try {
    		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        	MongoDatabase database = mongoClient.getDatabase("Proyecto-2019");
        	MongoCollection<Document> collection = database.getCollection("RegistroParametros");
        	
        	FindIterable<Document> puntos = collection.find(Filters.and(
                    Filters.gte("timestamp_field", inicio),
                    Filters.lte("timestamp_field", fin)));
        	
        	MongoCursor<Document> it = puntos.iterator();
        	
        	List<DtoHistorialTarifa> list = new ArrayList<DtoHistorialTarifa>();
        	DtoHistorialTarifa historial;
        	Document document;
        	
        	while ( it.hasNext() ) {
        		document = it.next();
        		
        		historial = new DtoHistorialTarifa();
        		historial.setParametro(document.getString("parametro"));
        		historial.setAdmin(document.getString("admin"));
        		historial.setOldValue(document.getString("oldValue"));
        		historial.setNewValue(document.getString("newValue"));
        		
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss zzz");
				sdf.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
				
				Date parsedDate = sdf.parse(sdf.format(document.getDate("timestamp")));
        		historial.setTimestamp(sdf.format(document.getDate("timestamp")));
        		
        		list.add(historial);
        	}
        	
        	return list;
    	} catch ( Exception e ) {
    		System.out.println(e.getMessage());
    		
    		throw new Exception("Ha ocurrido un error");
    	}
    }
}
