package obj.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-05-18T19:40:00.857-0300")
@StaticMetamodel(movimiento.class)
public class movimiento_ {
	public static volatile SingularAttribute<movimiento, String> guid;
	public static volatile SingularAttribute<movimiento, Timestamp> timestamp;
	public static volatile SingularAttribute<movimiento, Float> mount;
	public static volatile SingularAttribute<movimiento, String> paypalguid;
	public static volatile SingularAttribute<movimiento, String> moneda;
	public static volatile SingularAttribute<movimiento, cliente> cliente;
	public static volatile SingularAttribute<movimiento, Boolean> isValido;
}
