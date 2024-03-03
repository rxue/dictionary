package rx.dictionary.websocket;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws/chat")
public class ChatBot {

    private Session session;

    @OnOpen
    public void setSession(Session session) {
        System.out.println(":::::::::::::::::::::::::websocket endpoint instance hashcode: " + this.hashCode());
        System.out.println(":::::::::::::::::::::::::open websocket connection with session: " + session.getId());
        this.session = session;
    }

    @OnClose
    public void close(Session session) {
        System.out.println(":::::::::::::::::::::::::CLOSE websocket connection!!!!!!!!!!!!!!");
    }
}
