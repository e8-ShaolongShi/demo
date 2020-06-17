package cn.juxiewl.websocket;

import cn.juxiewl.websocket.model.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author shishaolong
 * @datatime 2020/5/12 17:36
 */
@Component
@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecode.class, encoders = MessageEncode.class)
public class ChatEndpoint {

    private Session session;
    private static Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {
        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);

        Message message = new Message();
        message.setFrom(username).setContent("Connected!");
        broadcast(message);

    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        message.setFrom(users.get(session.getId()));
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!!!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        //
        System.out.println("error！！！");
    }

    private static void broadcast(Message message) throws IOException, EncodeException{
        chatEndpoints.forEach(endpoint -> {
            try {
                endpoint.session.getBasicRemote().sendObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        });
    }
}
