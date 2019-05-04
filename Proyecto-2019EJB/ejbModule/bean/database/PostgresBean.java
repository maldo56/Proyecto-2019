package bean.database;

import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
import org.postgis.Point;

import obj.dto.DtoAdmin;
import obj.dto.DtoAlquiler;
import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoUsuario;
import obj.entity.administrador;
import obj.entity.alquiler;
import obj.entity.cliente;
import obj.entity.movimiento;
import obj.entity.parametro;
import obj.entity.scooter;
import obj.entity.usuario;

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
				entity.setBateryLevel(Double.parseDouble(value));
				
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
    
    public Boolean altaAlquiler(DtoAlquiler alquiler) {
    	
    	alquiler entity = new alquiler();
    	
    	entity.setTimestamp(alquiler.getTimestamp());
    	entity.setPrice(alquiler.getPrice());
    	
    	
    	cliente cliente = null;
    	scooter scooter = null;
    	parametro parametro = null;
    	
		try {
			
			cliente = em.find(obj.entity.cliente.class, alquiler.getCliente());
			scooter = em.find(obj.entity.scooter.class, alquiler.getGuidscooter());
			parametro = em.find(obj.entity.parametro.class, "tarifa-actual");
			
			entity.setCliente(cliente);
			entity.setScooter(scooter);
			entity.setTarifa(Float.valueOf(parametro.getValue().trim()).floatValue());
			
			transaction.begin();
			em.persist(entity);
			transaction.commit();

			return true;
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			
			return false;
		}
    	
    	
    }
    
    public Boolean terminarAlquiler(DtoAlquiler alquiler) {
    	
    	alquiler entity;
    	Date dateInicio;
    	Date dateFinal = new Date();
    	
    	long diferencia;
    	
		try {
			transaction.begin();
				
			entity = em.find(alquiler.class, alquiler.getGuid());
			dateInicio = entity.getTimestamp();
			
			diferencia = dateFinal.getTime() - dateInicio.getTime();
			
			entity.setDuration(new Time(diferencia));

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
    
    
    
}
