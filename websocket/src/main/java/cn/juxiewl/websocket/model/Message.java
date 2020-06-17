package cn.juxiewl.websocket.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shishaolong
 * @datatime 2020/5/12 17:41
 */
@Data
@Accessors(chain = true)
public class Message {
    private String from;
    private String to;
    private String content;
}
