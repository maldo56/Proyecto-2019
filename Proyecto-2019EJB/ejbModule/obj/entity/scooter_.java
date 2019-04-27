package obj.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-25T20:20:13.439-0300")
@StaticMetamodel(scooter.class)
public class scooter_ {
	public static volatile SingularAttribute<scooter, String> guid;
	public static volatile SingularAttribute<scooter, Double> bateryLevel;
	public static volatile SingularAttribute<scooter, Boolean> isRented;
	public static volatile SingularAttribute<scooter, Boolean> isAvailable;
}
