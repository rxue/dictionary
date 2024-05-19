package rx.dictionary.websocket;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint("/ws/chat")
public class ChatBot {

    private Session session;

    @OnOpen
    public void setSession(Session session) {
        System.out.println(" Web Socket endpoint instance hashcode: " + this.hashCode());
        System.out.println("Open Web Socket connection with session: " + session.getId());
        this.session = session;
    }
    @OnMessage
    public void receivedMessage(String message) {
        System.out.println("web socket service received message: " + message);
    }

    @OnClose
    public void close() throws IOException {
        System.out.println(":::::::::::::::::::::::::CLOSE websocket connection!!!!!!!!!!!!!!");
        session.close();
    }
}
