package obj.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-05-05T10:46:45.437-0300")
@StaticMetamodel(scooter.class)
public class scooter_ {
	public static volatile SingularAttribute<scooter, String> guid;
	public static volatile SingularAttribute<scooter, Float> bateryLevel;
	public static volatile SingularAttribute<scooter, Boolean> isRented;
	public static volatile SingularAttribute<scooter, Boolean> isAvailable;
}
