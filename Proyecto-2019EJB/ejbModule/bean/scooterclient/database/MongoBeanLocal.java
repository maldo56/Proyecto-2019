package bean.scooterclient.database;

import javax.ejb.Local;

@Local
public interface MongoBeanLocal {

	String add(String user);
	Boolean mongo();
	
	void servicioAddPunto(String guid, float x, float y);
}
