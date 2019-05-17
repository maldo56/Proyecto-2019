package servicios.api;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import servicios.business.ServicioCtrlBeanLocal;

import javax.websocket.Session;

/**
 * Session Bean implementation class APIServiciosBean
 */
@Stateless
@LocalBean
@ServerEndpoint(value = "/servicios", configurator = MyServerEndpointConfigurator.class)
public class APIServiciosBean {

	private Set<Session> scooterSessions = new HashSet<>();
	private Set<Session> usuarioSessions = new HashSet<>();
	
	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/ServicioCtrlBean!servicios.business.ServicioCtrlBeanLocal")
	private ServicioCtrlBeanLocal buissnes;
	
	
	@OnOpen
    public void open(Session session, EndpointConfig endpointConfig) {
        System.out.println("WebSocket: Nueva sesion abierta ==> " + session.getId());
        
        String userAgent = (String) endpointConfig.getUserProperties().get("user-agent");
        System.out.println("UserAgent: " + userAgent);
        
        if ( userAgent == null || userAgent.equals("null") ) {
        	scooterSessions.add(session);
        } else {
        	usuarioSessions.add(session);
        }
        
    }
	
	
	@OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        System.out.println("WebSocket: Nuevo mensaje ==> " + message.toString());


        boolean alquilado;
        String id;
        String alquiler;
        float latitude;
        float longitude;
        
        JsonReader jsonReader = Json.createReader(new StringReader(message));
		JsonObject object = jsonReader.readObject();
		
		alquilado = object.getBoolean("alquilado");
		id = object.getString("id");
		alquiler = object.getString("alquiler");
		
		JsonNumber aux = object.getJsonNumber("latitude");
		latitude = (float) aux.doubleValue();
		
		aux = object.getJsonNumber("longitude");
		longitude = (float) aux.doubleValue();
		
        
        if ( !alquilado ) {
        	System.out.println("esperando");
        	
        	String msg = "";
        	
        	try {
        		msg = buissnes.estaAlquilado(id);
        	} catch ( Exception e ) {
        		
        	}
        	
        	
        	if ( msg.equals("false") ) {
        		
//        		"{\"alquilado\":\"esperando\",\"id\":\"" + scooterGuid + "\",\"alquiler\":\" \",\"latitude\":0,\"longitude\":0}";
        		
        		msg = "{\"alquilado\":false,\"id\":\"" + id + "\",\"alquiler\":\" \",\"latitude\":-1,\"longitude\":-1}";
        		
        	} else {
        		msg = "{\"alquilado\":true,\"id\":\"" + id + "\",\"alquiler\":\"" + msg + "\",\"latitude\":-1,\"longitude\":-1}";
        	}
        	
        	System.out.println("Mandando mensaje");
        	
        	session.getBasicRemote().sendText(msg);
        } else {
        	
        	try {
            	
            	buissnes.addPoint(id, alquiler, latitude, longitude);
    			
            	for ( Session s : this.usuarioSessions) {
            		s.getBasicRemote().sendText("algo");
            	}
            	
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        		
        }
        
    }
 
    @OnClose
    public void close(Session session) {
        System.out.println("Session closed ==>");
        scooterSessions.remove(session);
        usuarioSessions.remove(session);
    }
 
    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

}
