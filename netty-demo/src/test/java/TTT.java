import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shishaolong
 * @datatime 2020/9/11 17:29
 */
public class TTT {

    @Test
    public void test(){
        String json = "{\n" +
                "    \"success\": true,\n" +
                "    \"message\": \"登录成功\",\n" +
                "    \"code\": 200,\n" +
                "    \"data\": {\n" +
                "        \"resumeList\":[\n" +
                "            {\n" +
                "                \"userName\": \"天虎\", //姓名\n" +
                "                \"resumeId\": \"13445\", //简历ID\n" +
                "                \"headIcon\": \"\",         //头像\n" +
                "                \"jobType\": \"12000\", //求职意向\n" +
                "                \"resumeLabel\": \"2年 | 经验不限 | 6000\", //简历标签\n" +
                "            },\n" +
                "            {\n" +
                "                \"userName\": \"天虎\",\n" +
                "                \"resumeId\": \"13425\", //简历ID\n" +
                "                \"headIcon\": \"\",\n" +
                "                \"jobType\": \"12000\",\n" +
                "                \"resumeLabel\": \"2年 | 经验不限 | 6000\"\n" +
                "            }\n" +
                "\n" +
                "]\n" +
                "    }\n" +
                "}";
        Object parse = JSON.parse(json);
        if (parse instanceof List){

        }else if (parse instanceof Map){
            Map dataMap = (Map) parse;
            Set set = dataMap.entrySet();
            System.out.println(set);
        }

//        System.out.println(parse);
//        System.out.println( );
//        System.out.println(parse instanceof Map);
    }
}
