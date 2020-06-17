package cn.juxiewl.websocket;

import cn.juxiewl.websocket.model.Message;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author shishaolong
 * @datatime 2020/5/12 17:57
 */
public class MessageEncode implements Encoder.Text<Message> {

    private static Gson gson = new Gson();

    @Override
    public String encode(Message message) throws EncodeException {
        String json = gson.toJson(message);
        return json;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        //
    }

    @Override
    public void destroy() {

    }
}
