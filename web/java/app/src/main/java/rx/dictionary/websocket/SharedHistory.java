package rx.dictionary.websocket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Reception;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import rx.dictionary.SearchKeyword;

import java.io.IOException;

@ServerEndpoint("/ws/sharedhistory")
public class SharedHistory {

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
