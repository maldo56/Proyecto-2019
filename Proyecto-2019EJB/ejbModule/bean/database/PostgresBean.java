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
import javax.security.sasl.AuthenticationException;
import javax.transaction.UserTransaction;

import org.postgis.Geometry;
import org.postgis.LineString;
import org.postgis.Point;

import bean.scooterclient.database.MongoBeanLocal;
import exceptions.DateTimeException;
import exceptions.MovimientoException;
import obj.dto.DtoAdmin;
import obj.dto.DtoAlquiler;
import obj.dto.DtoClient;
import obj.dto.DtoGeometria;
import obj.dto.DtoInfoScooters;
import obj.dto.DtoLocation;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoRakingUsuarios;
import obj.dto.DtoScooter;
import obj.dto.DtoUsuario;
import obj.entity.administrador;
import obj.entity.alquiler;
import obj.entity.cliente;
import obj.entity.movimiento;
import obj.entity.parametro;
import obj.entity.scooter;
import obj.entity.usuario;
import obj.entity.zona;
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
    
    public DtoUsuario login(String username, String password) throws Exception {
    	

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
				throw new AuthenticationException("Usuario o contraseña incorrecto.");
			}
			
		} catch( Exception e ) {
			
			if ( e instanceof AuthenticationException ) {
				throw new AuthenticationException("Usuario o contraseña incorrecto.");
			} else {
				throw new Exception("Ha ocurrido un error");
			}
			
		}
    	
    	return user;
    }
    
    
  //--------------------------------  ABM  --------------------------------------------------------------//
    
    public Boolean ABScooter(char operation, String guid) throws Exception {

		scooter entity;
		
		try {
			transaction.begin();
			
			if ( operation == 'A' ) {
				entity = new scooter();
				
				Point location = new Point();
				location.setX(4);
				location.setY(7);
				
				entity.setGuid(guid);
				
				em.persist(entity);
				transaction.commit();
				
				
				transaction.begin();
				
				String query = "UPDATE scooter "
						+ "SET location = ST_GeomFromText('POINT(-71.060316 48.432044)', 4326) "
						+ "WHERE scooter.guid = '" + entity.getGuid() + "'";
				
				em.createNativeQuery(query).executeUpdate();
				
				
			} else if( operation == 'B' ) {
				entity = em.find(scooter.class, guid);
				
				if ( entity != null ) {
					em.remove(entity);
				}
					
			}
			
			transaction.commit();

			return true;
			
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}	
    }
    
    public Boolean ABMClient(char operation, DtoClient client) throws Exception {

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
				
				if ( client.getPassword() != null && !client.getPassword().isEmpty() )
					entity.setPassword(client.getPassword());
				
				if ( client.getEmail() != null && !client.getEmail().isEmpty() )
					entity.setEmail(client.getEmail());
				
				if ( client.getName() != null && !client.getName().isEmpty() )
					entity.setName(client.getName());
				
				if ( client.getSurname() != null && !client.getSurname().isEmpty() )
					entity.setSurname(client.getSurname());
				
				if ( client.getUrlphoto() != null && !client.getUrlphoto().isEmpty() )
					entity.setUrlphoto(client.getUrlphoto());
				
				if ( client.getCellphone() != null && !client.getCellphone().isEmpty() )
					entity.setCellphone(client.getCellphone());
				
				
			} else {
				transaction.rollback();
				
				return false;
			}
			
			transaction.commit();

			return true;
			
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}
    }

    public Boolean ABMAdmin(char operation, DtoAdmin admin) throws Exception {

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
				
				if ( admin.getPassword() != null && !admin.getPassword().isEmpty() )
					entity.setPassword(admin.getPassword());
				
				if ( admin.getEmail() != null && !admin.getEmail().isEmpty() )
					entity.setEmail(admin.getEmail());
				
				if ( admin.getIsSuperuser() != null )
					entity.setIsSuperuser(admin.getIsSuperuser());
				
			} else {
				
				return false;
			}
			
			transaction.commit();
			
			return true;
			
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}
    }
    
    public Boolean MScooter(String campo, String guid, String value) throws Exception {
    	
		scooter entity;
		
		if (value == null || value.isEmpty()) {
			return false;
		}
		
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
			throw new Exception("Ha ocurrido un error");
		}
    }
    
    public Boolean ABMParametro(char operation, DtoParm parm) throws Exception {
    	
		parametro entity;
		
		if ( parm.getCode() == null || parm.getCode().isEmpty() ) {
			return false;
		}
		
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
				
				if ( parm.getUnit() != null && !parm.getUnit().isEmpty() )
					entity.setUnit(parm.getUnit());
				
				if ( parm.getValue() != null && !parm.getValue().isEmpty() )
					entity.setValue(parm.getValue());
				
			} else {
				transaction.rollback();
				
				return false;
			}

			transaction.commit();

			return true;
			
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}
    }
    
    public Boolean createMovimiento(DtoMovimiento movimiento) throws Exception {
    	
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
			throw new Exception("Ha ocurrido un error");
		}
    }
    
    public DtoAlquiler altaAlquiler(DtoAlquiler alquiler) throws Exception {
    	
    	alquiler entity = new alquiler();
    	
    	Date date= new Date();
		long time = date.getTime();
    	
    	entity.setTimestamp(new Timestamp(time));
    	entity.setPrice(-1);
    	entity.setDuration(null);
    	
    	cliente cliente = null;
    	scooter scooter = null;
    	parametro parametro = null;
    	
		try {
			scooter = em.find(obj.entity.scooter.class, alquiler.getGuidscooter());
			
			if ( !scooter.getIsRented() ) {
				
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

				alquiler.setGuid(entity.getGuid());
				alquiler.setTimestamp(entity.getTimestamp());
				
				return alquiler;
			}
			
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}
		
		return null;
    }
    
    public DtoAlquiler terminarAlquiler(DtoAlquiler alquiler, List<DtoLocation> ubicaciones) throws Exception {
    	
    	boolean DateTimeException = false;
    	boolean MovimientoException = false;
    	
    	alquiler entity;
    	scooter scooter;
    	parametro parametro;
    	cliente cliente;
    	movimiento movimiento = null;
    	
    	Time dateInicio;
    	Time dateFinal = java.sql.Time.valueOf(LocalTime.now());
    	Time time = null;
    	
    	long diferencia = 0;
    	float monto = 0;
    	float saldoCliente = 0;
    	String kml = Utils.kmlLinestring(ubicaciones);
    	
		try {
			
			parametro = em.find(obj.entity.parametro.class, "tarifa-actual");
			String value = parametro.getValue();
			
			transaction.begin();
			
			entity = em.find(alquiler.class, alquiler.getGuid());
			cliente = em.find(obj.entity.cliente.class, alquiler.getCliente());
			
			// Calculo de duracion y precio
			if ( entity.getDuration() == null ) {
				
				// Cambio de estado del scooter
				scooter = em.find(scooter.class, alquiler.getGuidscooter());
				scooter.setIsRented(false);
				
				try {

					dateInicio = new Time(entity.getTimestamp().getHours(), entity.getTimestamp().getMinutes(), entity.getTimestamp().getSeconds());
					diferencia = dateFinal.getTime() - dateInicio.getTime();

					//cargar monto
					monto = (diferencia/1000) * ( Float.valueOf(value).floatValue() );

				} catch( Exception e ) {
					DateTimeException = true;
				}
				
				
				// Cobrarle al cliente
				saldoCliente = cliente.getSaldo() - monto;
				cliente.setSaldo(saldoCliente);
				
				// Asignar movimiento
				try {
					movimiento = new movimiento();
					movimiento.setCliente(cliente);
					movimiento.setMoneda("USD");
					movimiento.setMount(monto);
					movimiento.setPaypalguid("");
					movimiento.setTimestamp(new Timestamp(System.currentTimeMillis()));
					
					em.persist(movimiento);
					
				} catch( Exception e) {
					MovimientoException = true;
				}
				
				// Cargar datos 
				
				entity.setMovimiento(movimiento);
				entity.setDuration(new Time(diferencia));
				entity.setPrice(monto);
				
				transaction.commit();
				
				// Cargar recorrido
				if ( !kml.equals("LINESTRING()") ) {
					
					transaction.begin();
					em.createNativeQuery("UPDATE alquiler p SET recorrido = ST_GeomFromText('" + kml + "', 4326) WHERE guid = \'" + alquiler.getGuid() + "\'").executeUpdate();
					transaction.commit();
				}
				
				alquiler.setDuration(entity.getDuration());			
				alquiler.setGeometria(Utils.kmltoGeometria(kml));
				alquiler.setPrice(entity.getPrice());
				alquiler.setTimestamp(entity.getTimestamp());
				
				
				// CONTROL DE ERRORES
				if ( DateTimeException ) {
					
					DateTimeException ex = new DateTimeException("Error: Ocurrió un error al cargar precio y duracion.");
					ex.setAlquiler(alquiler);
					
					throw ex;
				} else if ( MovimientoException ) {
					MovimientoException ex = new MovimientoException("Error: Ocurrió un error al cargar movimiento.");
					ex.setAlquiler(alquiler);
					
					throw ex;
				}
				
				return alquiler;
			} else {
				transaction.rollback();
			}
			
		} catch (Exception e) {
			
			if (e instanceof DateTimeException) {
				throw e;
			} else {
				
				throw new Exception("Ha ocurrido un error");
			}
			
		}
		
		return null;
    }
    
    public Boolean recargarSaldoCliente(String username, float monto) throws Exception {
    	
    	cliente entity;
    	float montoOld = 0;
    	
    	if ( monto <= 0 ) {
    		return false;
    	}
		
		try {
			
			transaction.begin();
			
			entity = em.find(cliente.class, username);
			montoOld = entity.getSaldo();
			entity.setSaldo( (monto + montoOld) );

			transaction.commit();

			return true;
			
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}
		
    }
    
    public Boolean abArea(char operation, DtoGeometria geometry) throws Exception {
    	
    	try {
    		zona entity = new zona();
    		
    		transaction.begin();
    		em.persist(entity);
    		transaction.commit();
    		
    		String kml = Utils.geometriaToKml(geometry);
    		
    		transaction.begin();
			em.createNativeQuery("UPDATE zona p SET areaPermitida = ST_GeomFromText('" + kml + "',4326) WHERE guid = \'" + entity.getGuid() + "\'").executeUpdate();
			transaction.commit();
    		
			return true;
    	} catch ( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    	
    }
    
    
    //--------------------------------  GET  --------------------------------------------------------------//
    
    public List<DtoMovimiento> obtenerMovimientos(String cliente) throws Exception {

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
			throw new Exception("Ha ocurrido un error");
		}
    	
    	return movimientos;
    }
    
    public DtoClient obtenerCliente(String username) throws Exception {
    	
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
    		throw new Exception("Ha ocurrido un error");
    	}
    	 
    }
    
    public DtoAdmin obtenerAdmin(String username) throws Exception {
    	
    	try {
    		administrador entity = em.find(administrador.class, username);
    		
    		DtoAdmin admin = new DtoAdmin();
    		
    		admin.setUsername(username);
    		admin.setEmail(entity.getEmail());
    		admin.setIsSuperuser(entity.getIsSuperuser());
    		
    		return admin;
    	} catch ( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    }
    
    public DtoParm obtenerParametro(String key) throws Exception {
    	
    	try {
    		parametro entity = em.find(parametro.class, key);
    		
    		DtoParm param = new DtoParm();
    		param.setCode(entity.getCode());
    		param.setUnit(entity.getUnit());
    		param.setValue(entity.getValue());
    		
    		return param;
    	} catch ( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    	 
    }
    
    public List<DtoParm> obtenerParametros() throws Exception {
    	
    	try {
    		
    		String query = "select p from parametro p";
    		
    		Query q = em.createQuery(query);
			List<parametro> entity = (List<parametro>) q.getResultList();
		    
			DtoParm parm;
			List<DtoParm> params = new ArrayList<DtoParm>();
    		
			
			for ( parametro aux : entity ) {
				parm = new DtoParm();
				
				parm.setCode(aux.getCode());
				parm.setUnit(aux.getUnit());
				parm.setValue(aux.getValue());
				
				params.add(parm);
			}
			
    		return params;
    	} catch ( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    	
    }
    
    public List<DtoAlquiler> obtenerAlquileres(String username) throws Exception {
    	
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
			throw new Exception("Ha ocurrido un error");
		}
    	
    	return alquileres;
    	
    }
    
    public List<DtoScooter> scootersDisponibles() throws Exception {
    	
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
			throw new Exception("Ha ocurrido un error");
		}
    	
    	return scooters;
    	
    }

    public float obtenerTiempoDisponible(String username) throws Exception {
    	
    	parametro parametro = null;
    	Float tarifa;
    	
    	try {
    		cliente entity = em.find(cliente.class, username);
    		parametro = em.find(obj.entity.parametro.class, "tarifa-actual");
    		
    		tarifa = Float.valueOf(parametro.getValue().trim()).floatValue();
    		
    		return ( entity.getSaldo() / tarifa );
    		
    	} catch( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    }

    public String scooterEstaAlquilado(String guid) throws Exception {
    	
    	try {
    		scooter entity = em.find(scooter.class, guid);
    		
    		if ( entity.getIsRented() ) {
    			
    			Query q = em.createQuery("select p from alquiler p where p.scooter.guid = :guid order by timestamp desc");
    			q.setParameter("guid", guid);
    			
    			DtoAlquiler aux;
    			int index = 0;
    			
    			alquiler l = (alquiler) q.getResultList().get(0);
    			
    			
    			return l.getGuid();
    		}
    		
    		
    	} catch ( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    	
    	return "false";
    }

    public DtoAlquiler obtenerAlquiler(String guid) throws Exception {
    	
    	try {
    		alquiler entity = em.find(alquiler.class, guid);
    		
    		DtoAlquiler alquiler = new DtoAlquiler();
    		
    		alquiler.setGuid(entity.getGuid());
    		alquiler.setGuidscooter(entity.getScooter().getGuid());
    		alquiler.setPrice(entity.getPrice());
    		alquiler.setDuration(entity.getDuration());
    		alquiler.setTimestamp(entity.getTimestamp());
    		alquiler.setCliente(entity.getCliente().getUsername());
    		
    		String srtquery = "select st_astext(a.recorrido) "
					+ "from alquiler as a "
					+ "where a.guid='" + guid + "'";
			
			Query q = em.createNativeQuery(srtquery);
			
			String strgeometria = (String) q.getResultList().get(0);
    		
			alquiler.setGeometria(Utils.kmltoGeometria(strgeometria));
			
    		return alquiler;
    	} catch ( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    }

    public DtoGeometria obtenerArea() throws Exception {
    	
    	try {
    		DtoGeometria geom = new DtoGeometria();
    		String srtquery = "select st_astext(st_union(st_transform(areapermitida, 4326))) FROM zona;";
			Query q = em.createNativeQuery(srtquery);
			String strgeometria = (String) q.getResultList().get(0);
			geom = Utils.kmlMultiLinestringToGeometryPolygon(strgeometria);
    		return geom;
    	} catch ( Exception e ) {
    		System.out.println(e.getMessage());
    		
    		throw new Exception("Ha ocurrido un error");
    	}
    	
    }
 
    public DtoAlquiler obtenerAlquilerActivo(String username) throws Exception {
    	
    	try {
    		alquiler entity;
    		DtoAlquiler alquiler = new DtoAlquiler();
    		
    		String query = "select p from alquiler p "
		    				+ "where p.cliente.username = :username and p.scooter.isRented "
		    					+ "order by p.timestamp desc";
    		
    		Query q = em.createQuery(query);
			q.setParameter("username", username);
    		
			entity = (obj.entity.alquiler) q.getResultList().get(0);
    		
    		
    		alquiler.setGuid(entity.getGuid());
    		alquiler.setGuidscooter(entity.getScooter().getGuid());
    		alquiler.setPrice(entity.getPrice());
    		alquiler.setDuration(entity.getDuration());
    		alquiler.setTimestamp(entity.getTimestamp());
    		alquiler.setCliente(entity.getCliente().getUsername());
    		
    		return alquiler;
    	} catch ( Exception e ) {
    		throw new Exception("Ha ocurrido un error");
    	}
    	
    }
    
    
    //--------------------------------  REPORTES   --------------------------------------------------------------//
     
    public DtoInfoScooters reporteInfoScooter() throws Exception {
    	
    	
    	try {
    		DtoInfoScooters resp = null;
    		
    		String query = "select " + 
		    				"	sum(case when isavailable = true then 1 else 0 end) as scootersdisponibles," + 
		    				"	sum(case when isavailable = false then 1 else 0 end) as scootersrotos," + 
		    				"	sum(case when isrented = true then 1 else 0 end) as scootersenuso" + 
		    					"	from scooter;";
    		
    		Query q = em.createNativeQuery(query, DtoInfoScooters.class);
    		resp = (DtoInfoScooters) q.getResultList().get(0);
    		
    		
    		return resp;
    	} catch (Exception e) {
    		throw e;
    	}
			
    }

    public float reporteGanancias(Timestamp inicio, Timestamp fin) throws Exception {
    	
    	try {
    		
    		String query = "select sum(mount) from movimiento "
    						+ "where paypalguid != \"\" and timestamp > " + inicio + " and timestamp < " + fin;
    
    		Query q = em.createNativeQuery(query);
    		double resp = (double) q.getResultList().get(0);

    		return (float) resp;
    	} catch ( Exception e ) {
    		System.out.println(e.getMessage());
    		
    		throw new Exception("Ha ocurrido un error");
    	}
    	
    }
    
    public int reportesCantAlquileres(Timestamp inicio, Timestamp fin) throws Exception {
    	
    	try {
    		
    		String query = "select sum(guid) from alquiler "
    						+ "where \"\" and timestamp > " + inicio + " and timestamp < " + fin;
    
    		Query q = em.createNativeQuery(query);
    		double resp = (double) q.getResultList().get(0);

    		return (int) resp;
    	} catch ( Exception e ) {
    		System.out.println(e.getMessage());
    		
    		throw new Exception("Ha ocurrido un error");
    	}
    	
    }
    
    public List<DtoRakingUsuarios> reporteRakingUsuarios() throws Exception {
    	
    	try {
    		
    		String query = "SELECT DISTINCT u.username, u.email, u.name, u.surname, u.urlphoto, u.cellphone, count(a.tarifa) as cantalquileres" + 
    						"	FROM alquiler as a INNER JOIN usuario as u ON a.cliente_username = u.username" + 
    							"	GROUP BY u.username";

    		Query q = em.createNativeQuery(query, DtoRakingUsuarios.class);
    		List<DtoRakingUsuarios> resp = q.getResultList();
    		
    		return resp;
    	} catch ( Exception e ) {
    		System.out.println(e.getMessage());
    		
    		throw new Exception("Ha ocurrido un error");
    	}
    }
    
}
