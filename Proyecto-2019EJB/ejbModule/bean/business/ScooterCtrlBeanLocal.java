package bean.business;

import javax.ejb.Local;

@Local
public interface ScooterCtrlBeanLocal {

	Boolean AB(char operation, String guid);
	Boolean M(String campo, String guid, String value);
	
	Boolean mongo();
}
