package obj.entity;

import java.sql.Time;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-05-04T18:54:20.008-0300")
@StaticMetamodel(alquiler.class)
public class alquiler_ {
	public static volatile SingularAttribute<alquiler, String> guid;
	public static volatile SingularAttribute<alquiler, Timestamp> timestamp;
	public static volatile SingularAttribute<alquiler, Time> duration;
	public static volatile SingularAttribute<alquiler, Float> price;
	public static volatile SingularAttribute<alquiler, Float> tarifa;
	public static volatile SingularAttribute<alquiler, scooter> scooter;
	public static volatile SingularAttribute<alquiler, cliente> cliente;
	public static volatile SingularAttribute<alquiler, movimiento> movimiento;
}
