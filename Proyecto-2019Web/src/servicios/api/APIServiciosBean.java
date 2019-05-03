package servicios.api;

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

	private Set<Session> sessions = new HashSet<>();
	
	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/ServicioCtrlBean!servicios.business.ServicioCtrlBeanLocal")
	private ServicioCtrlBeanLocal buissnes;
	
	
	@OnOpen
    public void open(Session session, EndpointConfig endpointConfig) {
        System.out.println("WebSocket: Nueva sesion abierta ==> " + session.getId());
        
        String userAgent = (String) endpointConfig.getUserProperties().get("user-agent");
        System.out.println("UserAgent: " + userAgent);
        
        sessions.add(session);
    }
	
	
	@OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("WebSocket: Nuevo mensaje ==> " + message);
        try {
        	
        	String scooterGuid = "";
        	float x;
        	float y;
        	
        	JsonReader jsonReader = Json.createReader(new StringReader(message));
			JsonObject object = jsonReader.readObject();
			
			
			scooterGuid = object.getString("guid");
			
			
			JsonNumber aux = object.getJsonNumber("x");
			x = (float) aux.doubleValue();
			
			aux = object.getJsonNumber("y");
			y = (float) aux.doubleValue();
			
        	buissnes.addPoint(scooterGuid, x, y);
			
			
//        	session.getBasicRemote().sendText("" + count);
        	
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
 
    @OnClose
    public void close(Session session) {
        System.out.println("Session closed ==>");
        sessions.remove(session);
    }
 
    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

}
