import org.junit.jupiter.api.Test;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shishaolong
 * @datatime 2020/8/5 10:41
 */
public class MessagePackTest {

    @Test
    public void test01() throws IOException {
        // Create serialize object
        List<String> src = new ArrayList<>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("fakjdk");

        MessagePack messagePack = new MessagePack();
        // serialize
        byte[] raw = messagePack.write(src);

        // 反序列化
        List<String> dstl = messagePack.read(raw, Templates.tList(Templates.TString));
        System.out.println(dstl);
    }
}
