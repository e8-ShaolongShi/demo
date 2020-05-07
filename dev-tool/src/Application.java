import service.ToolService;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.Objects;

/**
 * @author shishaolong
 * @datatime 2020/4/30 16:53
 */
public class Application {
    private JPanel panel1;
    private JEditorPane result_content_jep;
    private JButton convert_btn;
    private JEditorPane sql_content_jep;
    private JRadioButton sql2sb_radio;
    private JRadioButton sb2sql_radio;
    private ToolService service = new ToolService();

    public Application() {
        // 转化按钮点击
        convert_btn.addActionListener(e -> {
            try {
                // 检验文本编辑器中是否有内容
                String srcStr = sql_content_jep.getText();
                if (Objects.isNull(srcStr) || "".equals(srcStr)){
                    JOptionPane.showMessageDialog(null, "请输入需要转换的语句");
                    return;
                }
                // 校验是否 勾选了
                boolean sql2sbSelected = sql2sb_radio.isSelected();
                boolean sb2sqlSelected = sb2sql_radio.isSelected();
                String srcSql = sql_content_jep.getText();
                String sbSql;
                if (sql2sbSelected) { // SQL 转成sb 包裹的形式
                    sbSql = service.sql2sbConvert(srcSql);
                } else if (sb2sqlSelected){ // sb形式 转SQL
                    sbSql = service.sb2sqlConvert(srcSql);
                }else {
                    JOptionPane.showMessageDialog(null, "不支持的转换方式");
                    return;
                }
                result_content_jep.setText(sbSql);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "系统错误");
                return;
            }
        });
    }

    // --- 组件处理逻辑 开始 ---
    public void lockUI(JTextComponent textComponent) {
        textComponent.setEditable(false);
    }

    public void unLockUI(JTextComponent textComponent) {
        textComponent.setEditable(true);
    }

    // --- 组件处理逻辑 结束 ---

    public static void main(String[] args) {
        JFrame frame = new JFrame("SQL转化");
        frame.setContentPane(new Application().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 窗口设置成最大化
        frame.setSize(800, 1000);
        frame.pack();
        frame.setVisible(true);
    }
}
