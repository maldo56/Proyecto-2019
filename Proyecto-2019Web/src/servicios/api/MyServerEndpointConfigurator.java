package servicios.api;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;


public class MyServerEndpointConfigurator extends ServerEndpointConfig.Configurator {

	@Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        if (request.getHeaders().containsKey("user-agent")) {
            sec.getUserProperties().put("user-agent", request.getHeaders().get("user-agent").get(0)); // lower-case!
        }
    }
	
}
