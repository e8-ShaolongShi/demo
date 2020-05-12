import javax.swing.*;

/**
 * @author shishaolong
 * @datatime 2020/5/7 18:38
 */
public class ToolsApp {

    private JButton sql_convert_btn;
    private JPanel panel1;
    private static JFrame frame = new JFrame("开发工具集");

    public ToolsApp() {
        sql_convert_btn.addActionListener(e -> {
            new SqlConvertTool();
            return;
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new ToolsApp().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 窗口设置成最大化
        frame.setSize(800, 1000);
        frame.pack();
        frame.setVisible(true);
    }
}
