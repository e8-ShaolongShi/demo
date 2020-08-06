package netty.message_pack.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * 编码：只的是将对象、字符串等 转成 二进制
 * @author shishaolong
 * @datatime 2020/8/5 11:07
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
        MessagePack msgpack = new MessagePack();
        byte[] write = msgpack.write(msg);
        byteBuf.writeBytes(write);
    }
}
