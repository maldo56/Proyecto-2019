package obj.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-05-05T10:44:30.535-0300")
@StaticMetamodel(cliente.class)
public class cliente_ extends usuario_ {
	public static volatile SingularAttribute<cliente, String> name;
	public static volatile SingularAttribute<cliente, String> surname;
	public static volatile SingularAttribute<cliente, String> urlphoto;
	public static volatile SingularAttribute<cliente, String> cellphone;
	public static volatile SingularAttribute<cliente, Float> saldo;
}
