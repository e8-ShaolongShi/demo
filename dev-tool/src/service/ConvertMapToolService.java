package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 转换Map定义工具
 *
 * @author shishaolong
 * @datatime 2020/9/11 11:21
 */
public class ConvertMapToolService {

    private static final String SEPLITOR = ":";

    public static String convertMap(String srcStr) {
//        StringBuffer resultMap = new StringBuffer();
        StringBuffer resultMap = new StringBuffer();
        String[] lines = srcStr.split(System.lineSeparator());
        String resultStr = null;
        if (lines.length > 0) {
            String mapDef = "Map<String, Object> itemMap = new HashMap();";
            resultMap.append("Object obj = null;");
            resultMap.append(mapDef);
            resultStr = Arrays.asList(lines).stream()
                    .filter(line -> !(line == null || "".equals(line)))
                    .map(line -> {
                        // 将所有的空格全都替换掉
                        String lineTemp = line.replaceAll("\\s*", "");
                        String key = null;
                        if (lineTemp.contains(SEPLITOR)) {
                            key = lineTemp.substring(lineTemp.indexOf("\""), lineTemp.lastIndexOf("\":"));
                        } else {
                            key = lineTemp.substring(lineTemp.indexOf("\""), lineTemp.lastIndexOf("\""));
                        }
                        String rawKeyValue = "itemMap.put( " + key + ", obj);";
                        return rawKeyValue;
                    }).collect(Collectors.joining(System.lineSeparator()));
        }
        return resultStr;
    }

    public static void main(String[] args) {
        String str = "   \"enterpriseName\": \"天辰公司\",\n" +
                "            \"linkManName\": \"王飞\",\n" +
                "            \"linkPhone\": \"18169501770\",\n" +
                "            \"businessLicense\": \"GEKN4930JG\",\n" +
                "            \"certificateImage\": \"\",\n" +
                "            \"enterpriseNature\": \"\",\n" +
                "            \"belongIndustry\": \"\",\n" +
                "            \"enterpriseWelfare\": \"\",\n" +
                "            \"enterpriseSize\": \"\",\n" +
                "            \"financeType\": \"\",\n" +
                "            \"address\": \"\",\n" +
                "            \"enterpriseDescribe\": \"\",\n" +
                "            \"headIcon\": \"\",\n" +
                "            \"qrcode\": \"\",\n" +
                "            \"loginAccount\": \"\"";
        String result = convertMap(str);
        System.out.println(result);
    }
}
