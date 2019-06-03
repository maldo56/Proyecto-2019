package servicios.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import dto.WSScooterSession;
import servicios.business.ServicioCtrlBeanLocal;

import javax.websocket.Session;

/**
 * Session Bean implementation class APIServiciosBean
 */
@Stateless
@LocalBean
@ServerEndpoint(value = "/servicios/{scooterGuid}", configurator = MyServerEndpointConfigurator.class)
public class APIServiciosBean {

	static private List<WSScooterSession> Sessions = new ArrayList<WSScooterSession>();
	
	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/ServicioCtrlBean!servicios.business.ServicioCtrlBeanLocal")
	static private ServicioCtrlBeanLocal buissnes;
	
	
	@OnOpen
    public void open(@PathParam("scooterGuid") String scooterGuid , Session session, EndpointConfig endpointConfig) {
        
        String userAgent = (String) endpointConfig.getUserProperties().get("user-agent");
        
        WSScooterSession obj = new WSScooterSession();
        obj.setGuidScooter(scooterGuid);
        obj.setSession(session);
        
        Sessions.add(obj);
    }
	
	
//	{guidScooter, isAlquilado, guidAlquiler, Lat,Lng}
	@OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        System.out.println("WebSocket: Nuevo mensaje ==> " + message.toString());

        boolean isAlquilado;
        String guidScooter;
        String guidAlquiler;
        float latitude;
        float longitude;
        
        try {
        	
        	JsonReader jsonReader = Json.createReader(new StringReader(message));
    		JsonObject object = jsonReader.readObject();
    		
    		isAlquilado = object.getBoolean("isAlquilado");
    		guidScooter = object.getString("guidScooter");
    		guidAlquiler = object.getString("guidAlquiler");
    		
    		JsonNumber aux = object.getJsonNumber("Lat");
    		latitude = (float) aux.doubleValue();
    		aux = object.getJsonNumber("Lng");
    		longitude = (float) aux.doubleValue();
            	
    		if ( isAlquilado ) {
    			buissnes.addPoint(guidScooter, guidAlquiler, latitude, longitude);
    		}
            
        } catch ( Exception e ) {
        	System.out.println(e.getMessage());
        }
    }


//	{action,isAlquilado,guidAlquiler}
//	action : ["startTravel","shutdown"]
	static public void sendAction(String action, String guidAlquiler, String username, String scooterGuid) throws IOException {
		
		WSScooterSession session;
		String message = "";
		
		try {
			
			for ( int x = 0; x < Sessions.size(); x ++ ) {
				session = Sessions.get(x);
				
				if ( session.getGuidScooter().equals(scooterGuid) || scooterGuid.isEmpty() ) {
					
					if ( session.getSession().isOpen() ) {
						
						if ( action.equals("startTravel") ) {
							message = "{\"action\":\"" + action + "\",\"username\":\"" + username + "\",\"isAlquilado\":" + true + ",\"guidAlquiler\":\"" + guidAlquiler + "\"}";
						} else {
							message = "{\"action\":\"" + action + "\",\"isAlquilado\":" + false + ",\"guidAlquiler\":\" \"}";
						}
						
						session.getSession().getBasicRemote().sendText(message);
					}
						
				}
			}
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
		}
	}
	
 
    @OnClose
    public void close(Session session) {
        System.out.println("Session closed ==>");

    }
 
    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

}
