package bean.database;

import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.postgis.Geometry;
import org.postgis.LineString;
import org.postgis.Point;

import bean.scooterclient.database.MongoBeanLocal;
import obj.dto.DtoAdmin;
import obj.dto.DtoAlquiler;
import obj.dto.DtoClient;
import obj.dto.DtoLocation;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoScooter;
import obj.dto.DtoUsuario;
import obj.entity.administrador;
import obj.entity.alquiler;
import obj.entity.cliente;
import obj.entity.movimiento;
import obj.entity.parametro;
import obj.entity.scooter;
import obj.entity.usuario;
import utils.Utils;

import javax.ejb.TransactionManagementType;
import javax.ejb.TransactionAttributeType;


@Stateless
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PostgresBean implements PostgresBeanLocal {
	
	
	@PersistenceContext(unitName = "proyecto")
    private EntityManager em;
	
	@Resource
	UserTransaction transaction;
	
    public PostgresBean() {
        // TODO Auto-generated constructor stub
    }
    
    public DtoUsuario login(String username, String password) {
    	System.out.println(username + " -- " + password);
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto");
		EntityManager em = emf.createEntityManager();

		DtoUsuario user = null;
		
		try {
			Query q = em.createQuery("select p from usuario p where p.username = :username and p.password = :password");
			q.setParameter("username", username);
			q.setParameter("password", password);
			
			List<usuario> l = q.getResultList();
			
			if ( !l.isEmpty() ) {
				
				System.out.println("No vacio");
				
				usuario aux = l.get(0);
				
				if ( aux instanceof cliente ) {
					user = new DtoClient(aux.getUsername(), "", aux.getEmail(), ((cliente) aux).getName(), ((cliente) aux).getSurname(), 
							((cliente) aux).getUrlphoto(), ((cliente) aux).getCellphone(), ((cliente) aux).getSaldo());			
				} else {
					user = new DtoAdmin(aux.getUsername(), "", aux.getEmail(), ((administrador) aux).getIsSuperuser());
				}
			} else {
				System.out.println("Vacio");
			}
			
		} catch( Exception e ) {
			System.out.println(e.getMessage());
		}
    	
    	return user;
    }
    
    
  //--------------------------------  ABM  --------------------------------------------------------------//
    
    public Boolean ABScooter(char operation, String guid) {

		scooter entity;
		
		try {
			transaction.begin();
			
			if ( operation == 'A' ) {
				entity = new scooter();
				
				Point location = new Point();
				location.setX(4);
				location.setY(7);
//				entity.setLocation(location);
				
				
				em.persist(entity);
				
				
			} else if( operation == 'B' ) {
				entity = em.find(scooter.class, guid);
				
				if ( entity != null ) {
					em.remove(entity);
				}
					
			}

			transaction.commit();

			return true;
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			
			return false;
		}	
    }
    
    public Boolean ABMClient(char operation, DtoClient client) {

		cliente entity;
		
		try {
			transaction.begin();
			
			if ( operation == 'A' ) {
				entity = new cliente();
				entity.setUsername(client.getUsername());
				entity.setPassword(client.getPassword());
				entity.setEmail(client.getEmail());
				entity.setName(client.getName());
				entity.setSurname(client.getSurname());
				entity.setUrlphoto(client.getUrlphoto());
				entity.setCellphone(client.getCellphone());
				entity.setSaldo(client.getSaldo());
				
				em.persist(entity);
				
			} else if( operation == 'B' ) {
				entity = em.find(cliente.class, client.getUsername());
				
				if ( entity != null ) {
					em.remove(entity);
				}
					
			} else if( operation == 'M' ) {
				
				entity = em.find(cliente.class, client.getUsername());
				
				entity.setPassword(client.getPassword());
				entity.setEmail(client.getEmail());
				entity.setName(client.getName());
				entity.setSurname(client.getSurname());
				entity.setUrlphoto(client.getUrlphoto());
				entity.setCellphone(client.getCellphone());
				entity.setSaldo(client.getSaldo());
				
			} else {
				transaction.rollback();
				
				return false;
			}
			
			transaction.commit();

			return true;
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			
			return false;
		}
    }

    public Boolean ABMAdmin(char operation, DtoAdmin admin) {

		administrador entity;
		
		try {
			transaction.begin();
			
			if ( operation == 'A' ) {
				entity = new administrador();
				entity.setUsername(admin.getUsername());
				entity.setPassword(admin.getPassword());
				entity.setEmail(admin.getEmail());
				entity.setIsSuperuser(admin.getIsSuperuser());
				
				em.persist(entity);
			} else if( operation == 'B' ) {
				entity = em.find(administrador.class, admin.getUsername());
				
				if ( entity != null ) {
					em.remove(entity);
				}
					
			} else if( operation == 'M' ) {
				
				entity = em.find(administrador.class, admin.getUsername());
				
				entity.setPassword(admin.getPassword());
				entity.setEmail(admin.getEmail());
				entity.setIsSuperuser(admin.getIsSuperuser());
				
			} else {
				
				return false;
			}
			
			transaction.commit();
			
			return true;
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			
			return false;
		}
    }
    
    public Boolean MScooter(String campo, String guid, String value) {
    	
		scooter entity;
		
		try {
			transaction.begin();
			
			if ( campo.equals("bateryLevel") ) {
				
				entity = em.find(scooter.class, guid);
				entity.setBateryLevel(Float.valueOf(value.trim()).floatValue());
				
			} else if( campo.equals("isRented") ) {
				
				entity = em.find(scooter.class, guid);
				entity.setIsRented(Boolean.valueOf(value));
				
			} else if( campo.equals("isAvailable") ) {
				
				entity = em.find(scooter.class, guid);
				entity.setIsAvailable(Boolean.valueOf(value));
			}

			transaction.commit();

			return true;
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			
			return false;
		}
    }
    
    public Boolean ABMParametro(char operation, DtoParm parm) {
    	
		parametro entity;
		
		try {
			transaction.begin();
			
			if ( operation == 'A' ) {
				entity = new parametro();
				entity.setCode(parm.getCode());
				entity.setUnit(parm.getUnit());
				entity.setValue(parm.getValue());
				
				em.persist(entity);
			} else if( operation == 'B' ) {
				entity = em.find(parametro.class, parm.getCode());
				
				if ( entity != null ) {
					em.remove(entity);
				}
					
			}else if( operation == 'M' ) {
				
				entity = em.find(parametro.class, parm.getCode());
				entity.setUnit(parm.getUnit());
				entity.setValue(parm.getValue());
				
			} else {
				transaction.rollback();
				
				return false;
			}

			transaction.commit();

			return true;
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			
			return false;
		}
    }
    
    public Boolean createMovimiento(DtoMovimiento movimiento) {
    	
		movimiento entity = new movimiento();
		entity.setTimestamp(movimiento.getTimestamp());
		entity.setPaypalguid(movimiento.getPaypalguid());
		entity.setMoneda(movimiento.getMoneda());
		entity.setMount(movimiento.getMount());
		
		try {
			transaction.begin();
			
			cliente cliente = em.find(cliente.class, movimiento.getUsername());
			
			if ( cliente != null ) {
				entity.setCliente(cliente);
				em.persist(entity);
			}
			
			transaction.commit();

			return true;
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			
			return false;
		}
    }
    
    public String altaAlquiler(DtoAlquiler alquiler) {
    	
    	alquiler entity = new alquiler();
    	
    	Date date= new Date();
		long time = date.getTime();
    	
    	entity.setTimestamp(new Timestamp(time));
    	entity.setPrice(-1);
    	
    	
    	cliente cliente = null;
    	scooter scooter = null;
    	parametro parametro = null;
    	
		try {

			cliente = em.find(obj.entity.cliente.class, alquiler.getCliente());
			parametro = em.find(obj.entity.parametro.class, "tarifa-actual");
			entity.setCliente(cliente);
			entity.setTarifa(Float.valueOf(parametro.getValue().trim()).floatValue());
			
			transaction.begin();
			
			scooter = em.find(obj.entity.scooter.class, alquiler.getGuidscooter());
			scooter.setIsRented(true);
			entity.setScooter(scooter);
			
			em.persist(entity);
			transaction.commit();

			return entity.getGuid();
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			
			return "";
		}
    	
    	
    }
    
    public Boolean terminarAlquiler(DtoAlquiler alquiler, List<DtoLocation> ubicaciones) {
    	
    	alquiler entity;
    	scooter scooter;
    	parametro parametro;
    	Time dateInicio;
    	Time dateFinal = java.sql.Time.valueOf(LocalTime.now());
    	
    	long diferencia;
    	
    	Time time;
    	
    	String kml = Utils.kmlLinestring(ubicaciones);
    	
    	System.out.println(kml);
    	
		try {
			
			parametro = em.find(obj.entity.parametro.class, "tarifa-actual");
			transaction.begin();
			
			scooter = em.find(scooter.class, alquiler.getGuidscooter());
			scooter.setIsRented(false);
			
			entity = em.find(alquiler.class, alquiler.getGuid());
			
			dateInicio = new Time(entity.getTimestamp().getHours(), entity.getTimestamp().getMinutes(), entity.getTimestamp().getSeconds());
			diferencia = dateFinal.getTime() - dateInicio.getTime();
			time = new Time(diferencia);
			
			if ( dateInicio.getMinutes() >  dateFinal.getMinutes()) {
				time.setHours( (dateFinal.getHours() -  dateInicio.getHours() - 1) );
			} else {
				time.setHours( (dateFinal.getHours() -  dateInicio.getHours()) );
			}
			
			entity.setDuration( time );
			
			entity.setPrice( (diferencia/1000) * ( Float.valueOf(parametro.getValue().trim()).floatValue() ) );
			
			transaction.commit();

			
			
			transaction.begin();
			
			em.createNativeQuery("UPDATE alquiler p SET recorrido = ST_GeomFromText('" + kml + "', 4326) WHERE guid = \'" + alquiler.getGuid() + "\'").executeUpdate();
			
			transaction.commit();
			return true;
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			
			return false;
		}
		
    }
    
    //--------------------------------  GET  --------------------------------------------------------------//
    
    public List<DtoMovimiento> obtenerMovimientos(String cliente) {

		List<DtoMovimiento> movimientos = new ArrayList<DtoMovimiento>();
		
		try {
			Query q;
			
			if ( cliente.equals("") || cliente == null ) {
				q = em.createQuery("select p from movimiento p");
			} else {
				q = em.createQuery("select p from movimiento p where p.cliente.username = :username");
				q.setParameter("username", cliente);
			}
			
			DtoMovimiento aux;
			
			List<movimiento> l = q.getResultList();
			
			for (movimiento mov : l) {

				aux = new DtoMovimiento();
				aux.setTimestamp(mov.getTimestamp());
				aux.setMount(mov.getMount());
				aux.setPaypalguid(mov.getPaypalguid());
				aux.setMoneda(mov.getMoneda());
				aux.setUsername(mov.getCliente().getUsername());
				
				movimientos.add(aux);
			}
			
			if (l.isEmpty()) {
				System.out.println("Result set movimientos is empty");
			}
			
		} catch( Exception e ) {
			System.out.println(e.getMessage());
		}
    	
    	return movimientos;
    }
    
    public DtoClient obtenerCliente(String username) {
    	
    	try {
    		cliente entity = em.find(cliente.class, username);
    		
    		DtoClient cliente = new DtoClient();
    		
    		cliente.setUsername(username);
    		cliente.setName(entity.getName());
    		cliente.setSurname(entity.getSurname());
    		cliente.setEmail(entity.getEmail());
    		cliente.setCellphone(entity.getCellphone());
    		cliente.setUrlphoto(entity.getUrlphoto());
    		cliente.setSaldo(entity.getSaldo());
    		
    		return cliente;
    	} catch ( Exception e ) {
    		System.out.println(e.getMessage());
    	}
    	 
    	return null;
    }
    
    public DtoParm obtenerParametro(String key) {
    	
    	try {
    		parametro entity = em.find(parametro.class, key);
    		
    		DtoParm param = new DtoParm();
    		param.setCode(entity.getCode());
    		param.setUnit(entity.getUnit());
    		param.setValue(entity.getValue());
    		
    		return param;
    	} catch ( Exception e ) {
    		System.out.println(e.getMessage());
    	}
    	 
    	return null;
    }
    
    public List<DtoAlquiler> obtenerAlquileres(String username) {
    	
    	List<DtoAlquiler> alquileres = new ArrayList<DtoAlquiler>();
		List<String> strgeometrias;
		try {
			
			
			String srtquery = "select st_astext(a.recorrido) "
					+ "from alquiler as a "
					+ "where a.cliente_username='" + username + "'";
			
			Query q = em.createNativeQuery(srtquery);
			
			strgeometrias = q.getResultList();
			
			
			q = em.createQuery("select p from alquiler p where p.cliente.username = :username");
			q.setParameter("username", username);
			
			DtoAlquiler aux;
			int index = 0;
			
			List<alquiler> l = q.getResultList();
			
			String kml;
		
			for (alquiler alq : l) {

				aux = new DtoAlquiler();
				aux.setGuid(alq.getGuid());
				aux.setCliente(username);
				aux.setDuration(alq.getDuration());
				aux.setGuidscooter(alq.getScooter().getGuid());
				aux.setPrice(alq.getPrice());
				aux.setTimestamp(alq.getTimestamp());
				
				kml = strgeometrias.get(index);
				
				aux.setGeometria(Utils.kmltoGeometria(kml));
				
				alquileres.add(aux);
				index ++;
			}
			
			if (l.isEmpty()) {
				System.out.println("Result set movimientos is empty");
			}
			
		} catch( Exception e ) {
			System.out.println(e.getMessage());
		}
    	
    	return alquileres;
    	
    }
    
    public List<DtoScooter> scootersDisponibles() {
    	
    	List<DtoScooter> scooters = new ArrayList<DtoScooter>();
    	List<String> strgeometrias;
    	
		try {
			
			String srtquery = "select st_astext(s.location) "
							+ "from scooter as s "
							+ "where s.isRented = false and s.isAvailable = true";
			
			Query q = em.createNativeQuery(srtquery);
			strgeometrias = q.getResultList();
			
			
			q = em.createQuery("select p from scooter p where p.isRented = false and p.isAvailable = true");
			
			DtoScooter aux;
			
			List<scooter> l = q.getResultList();
			
			int index = 0;
			String kml;
		
			for (scooter alq : l) {
				
				aux = new DtoScooter();
				aux.setGuid(alq.getGuid());
				aux.setBateryLevel(alq.getBateryLevel());
				aux.setIsAvailable(alq.getIsAvailable());
				aux.setIsRented(alq.getIsRented());
				
				
				kml = strgeometrias.get(index);
				
				aux.setGeometria(Utils.kmltoGeometria(kml));
				
				scooters.add(aux);
				index ++;
			}
			
			if (l.isEmpty()) {
				System.out.println("Result set movimientos is empty");
			}
			
		} catch( Exception e ) {
			System.out.println(e.getMessage());
		}
    	
    	return scooters;
    	
    }

    public float obtenerTiempoDisponible(String username) {
    	
    	parametro parametro = null;
    	Float tarifa;
    	
    	try {
    		cliente entity = em.find(cliente.class, username);
    		parametro = em.find(obj.entity.parametro.class, "tarifa-actual");
    		
    		tarifa = Float.valueOf(parametro.getValue().trim()).floatValue();
    		
    		return ( entity.getSaldo() / tarifa );
    		
    	} catch( Exception e ) {
    		System.out.println(e.getMessage());
    	}
    	
    	return (float) 0;
    }

}
