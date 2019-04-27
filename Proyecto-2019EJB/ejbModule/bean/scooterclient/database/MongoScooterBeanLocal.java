package bean.scooterclient.database;

import javax.ejb.Local;

@Local
public interface MongoScooterBeanLocal {

	String add(String user);
}
