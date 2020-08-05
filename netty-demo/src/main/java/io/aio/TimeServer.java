package io.aio;

/**
 * 用AIO实现 TimeServer服务器
 *
 * @author shishaolong
 * @datatime 2020/8/4 14:55
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length >0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                // 采用默认端口
            }
        }


    }

}
