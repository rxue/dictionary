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

    /*public void onSearch(@Observes(notifyObserver = Reception.IF_EXISTS) final SearchKeyword searchedLexicalItem) throws IOException {
        System.out.println(":::::::::::::::::::::::::On Search in websocket endpoint: observed keyword: " + searchedLexicalItem.getValue());
        session.getBasicRemote().sendText(searchedLexicalItem.getValue());
    }*/
    @OnClose
    public void close(Session session) {
        System.out.println(":::::::::::::::::::::::::CLOSE websocket connection!!!!!!!!!!!!!!");
    }
}
