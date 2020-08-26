package service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author shishaolong
 * @datatime 2020/5/6 11:23
 */
public class ToolService {

    /**
     * 将普通SQL转成 StringBuffer包裹的SQL
     *
     * @param srcSql
     * @return
     */
    public String sql2sbConvert(String srcSql) {
        StringBuffer resultSql = new StringBuffer();
        String[] lines = srcSql.split(System.lineSeparator());
        if (lines.length > 0) {
            // 首行创建StringBuffer
            resultSql.append("StringBuffer sb = new StringBuffer();");
            resultSql.append(System.lineSeparator());
            // 取到最长行的长度
            Integer maxLength = Stream.of(lines).map(line -> line.length()).max(Integer::compareTo).orElse(0);
            String lenSpace = lenSpace(maxLength);
            // 构造每一行的内容
            String sbLine = Stream.of(lines).filter(line -> line != null && line.length() > 0)
                    .map(line -> {
                        // 格式化sb.append(" ** ")包裹的形式 用空格填充，让每一行都和最长行，长度一致,
                        StringBuffer lineTempSb = new StringBuffer();
                        lineTempSb.append("sb.append(\" ");
                        lineTempSb.append(line + lenSpace.substring(line.length() - 1));
                        lineTempSb.append(" \");");
                        return lineTempSb.toString();
                    }).collect(Collectors.joining(System.lineSeparator()));
            resultSql.append(sbLine);
        }
        return resultSql.toString();
    }

    /**
     * 将StringBuffer包裹的SQL 转成普通SQL
     *
     * @param srcSql
     * @return
     */
    public String sb2sqlConvert(String srcSql) {
        StringBuffer resultSql = new StringBuffer();
        String[] lines = srcSql.split(System.lineSeparator());
        if (lines.length > 0) {
            // 1. 首位如果是空格去掉  2.结尾的空格全部trim掉  3.换行还是要加的
            String sbLine = Stream.of(lines)
                    .filter(line -> line.contains("append")) // 必须含有append语句
                    .map(line -> {
                        // 去掉首部的 append("
                        String spaceStr = "append(\" ";
                        String noSpaceStr = "append(\"";
                        int spaceIndex = line.indexOf(spaceStr);
                        int noSpaceIndex = line.indexOf(noSpaceStr);
                        int startIndex = (spaceIndex != -1 ? spaceIndex + spaceStr.length() : noSpaceIndex + noSpaceStr.length());
                        // 去掉结尾的 "); 及空格
                        int lastIndex = line.lastIndexOf("\");");
                        String sqlLine = line.substring(startIndex, lastIndex);
                        return sqlLine;
                    }).collect(Collectors.joining(System.lineSeparator()));
            resultSql.append(sbLine);
        }
        return resultSql.toString();
    }

    /**
     * 填充固定个数空格
     *
     * @param len
     * @return
     */
    private String lenSpace(int len) {
        StringBuffer sb = new StringBuffer();
        IntStream.range(0, len).forEach(i -> sb.append(" "));
        return sb.toString();
    }
}
