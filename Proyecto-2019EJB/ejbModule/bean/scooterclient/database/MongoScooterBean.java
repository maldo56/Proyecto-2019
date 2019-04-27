package bean.scooterclient.database;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


/**
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
    

}
