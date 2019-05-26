package notificaciones.api;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.push.Push;
import javax.inject.Inject;
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
@ServerEndpoint(value = "/notifications", configurator = MyServerEndpointConfigurator.class)
public class APINotificacionesBean {

	static private Set<Session> Sessions = new HashSet<>();
	
	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/ServicioCtrlBean!servicios.business.ServicioCtrlBeanLocal")
	private ServicioCtrlBeanLocal buissnes;
	
	
	@OnOpen
    public void open(Session session, EndpointConfig endpointConfig) {
        System.out.println("WebSocket: Nueva sesion abierta ==> " + session.getId());
        
        String userAgent = (String) endpointConfig.getUserProperties().get("user-agent");
        
        Sessions.add(session);
    }
	
	static public void sendNotification(String message) throws IOException {
		
		for (Session s : Sessions) {
			s.getBasicRemote().sendText(message);
		}
		
	}
	
	
	@OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        System.out.println("WebSocket: Nuevo mensaje ==> " + message.toString());

        
        
    }
 
    @OnClose
    public void close(Session session) {
        System.out.println("Session closed ==>");
        Sessions.remove(session);
    }
 
    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

}
