package notificaciones.api;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import dto.WSSession;
import servicios.business.ServicioCtrlBeanLocal;

import javax.websocket.Session;

/**
 * Session Bean implementation class APIServiciosBean
 */
@Stateless
@LocalBean
@ServerEndpoint(value = "/notifications/{rol}/{username}", configurator = MyServerEndpointConfigurator.class)
public class APINotificacionesBean {

	static private List<WSSession> SessionsClientes = new ArrayList<WSSession>();
	static private List<WSSession> SessionsAdmins = new ArrayList<WSSession>();
	
	
	@OnOpen
    public void open(@PathParam("rol") String rol, @PathParam("username") String username, Session session, EndpointConfig endpointConfig) {
        System.out.println("WebSocket: Nueva sesion abierta ==> " + session.getId());
        
//        String userAgent = (String) endpointConfig.getUserProperties().get("user-agent");
        
        WSSession wss = new WSSession(username, session);
        
        if ( rol.equals("client") ) {
        	SessionsClientes.add(wss);
        } else if ( rol.equals("admin") ) {
        	SessionsAdmins.add(wss);
        }
    }
	
	// {"guidScooter":" + guid + ","guidAlquiler":" + guid + ","latitude": + lat + ,"longitude": + lng }
	static public void sendLocation(String guidScooter, String guidAlquiler, float latitude, float longitude) throws IOException {
		
		String msg = "{\"guidScooter\":\"" + guidScooter + "\",\"guidAlquiler\":\"" + guidAlquiler + "\",\"latitude\":" + latitude + ",\"longitude\":" + longitude + "}";
		
//		List<WSSession> sessions = Sessions.stream().filter(s -> s.getRol().equals("admin")).collect(Collectors.toList());
//		WSSession session;
		
		for ( WSSession session : SessionsAdmins ) {
			
			if ( session.getSession().isOpen() )
				session.getSession().getBasicRemote().sendText(msg);
		}
	}
	
	static public void sendNotification(String rol, String username, String message) throws IOException {
		
		if ( rol.equals("admin") ) {
			
			for ( WSSession session : SessionsAdmins ) {
				if ( username.isEmpty() ) {
					if ( session.getSession().isOpen() )
						session.getSession().getBasicRemote().sendText(message);
				} else {
					if ( session.getUsername().equals(username) ) {
						if ( session.getSession().isOpen() )
							session.getSession().getBasicRemote().sendText(message);
					}
				}
			}
			
		} else if ( rol.equals("client") ) {
			
			for ( WSSession session : SessionsClientes ) {
				if ( username.isEmpty() ) {
					if ( session.getSession().isOpen() )
						session.getSession().getBasicRemote().sendText(message);
				} else {
					if ( session.getUsername().equals(username) ) {
						if ( session.getSession().isOpen() )
							session.getSession().getBasicRemote().sendText(message);
					}
				}
			}
		} else if ( rol.isEmpty() ) {
			
			for ( WSSession session : SessionsClientes ) {
				if ( username.isEmpty() ) {
					if ( session.getSession().isOpen() )
						session.getSession().getBasicRemote().sendText(message);
				} else {
					if ( session.getUsername().equals(username) ) {
						if ( session.getSession().isOpen() )
							session.getSession().getBasicRemote().sendText(message);
					}
				}
			}
			
			for ( WSSession session : SessionsAdmins ) {
				if ( username.isEmpty() ) {
					if ( session.getSession().isOpen() )
						session.getSession().getBasicRemote().sendText(message);
				} else {
					if ( session.getUsername().equals(username) ) {
						if ( session.getSession().isOpen() )
							session.getSession().getBasicRemote().sendText(message);
					}
				}
			}
		}		
	}
	
	
	@OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        System.out.println("WebSocket: Nuevo mensaje ==> " + message.toString());

        
    }
 
    @OnClose
    public void close(Session session) {
        System.out.println("Session closed ==>");
//        Sessions.remove(session);
    }
 
    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

}
