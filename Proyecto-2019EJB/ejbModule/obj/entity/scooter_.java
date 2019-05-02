package obj.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.Geometry;

@Generated(value="Dali", date="2019-05-01T23:24:43.050-0300")
@StaticMetamodel(scooter.class)
public class scooter_ {
	public static volatile SingularAttribute<scooter, String> guid;
	public static volatile SingularAttribute<scooter, Double> bateryLevel;
	public static volatile SingularAttribute<scooter, Boolean> isRented;
	public static volatile SingularAttribute<scooter, Boolean> isAvailable;
	public static volatile SingularAttribute<scooter, Geometry> location;
}
