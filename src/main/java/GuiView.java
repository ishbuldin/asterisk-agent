import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GuiView {
    private final GuiController controller;
    private JFrame frame = new JFrame("Агент АТС");
    private JTextArea queues = new JTextArea(10, 30);
    private JTextArea information = new JTextArea(10, 50);

    public GuiView(GuiController controller) {
        this.controller = controller;
        initView();
    }

    private void initView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        queues.setEditable(false);
        information.setEditable(false);
        frame.getContentPane().add(new JScrollPane(queues), BorderLayout.WEST);
        frame.getContentPane().add(new JScrollPane(information), BorderLayout.EAST);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public String getUsername() {
        return JOptionPane.showInputDialog(
                frame,
                "Введите свой логин:",
                "Конфигурация клиента",
                JOptionPane.QUESTION_MESSAGE);
    }

    public String getPassword() {
        return JOptionPane.showInputDialog(
                frame,
                "Введите пароль:",
                "Конфигурация клиента",
                JOptionPane.QUESTION_MESSAGE);
    }

    public String getExtension() {
        return JOptionPane.showInputDialog(
                frame,
                "Введите ваш внутренний номер:",
                "Конфигурация клиента",
                JOptionPane.QUESTION_MESSAGE);
    }

    public String getHost() {
        return JOptionPane.showInputDialog(
                frame,
                "Введите IP адрес АТС:",
                "Конфигурация клиента",
                JOptionPane.QUESTION_MESSAGE);
    }

    public void refreshQueues() {
        queues.setText("");
        for (Map.Entry<String, String> pair : controller.getModel().getQueueMap().entrySet()) {
            queues.append(pair.getKey() + " " + pair.getValue());
            queues.append("\n");
        }

    }

    public void refreshMessages(String info) {
        information.setText("");
        information.append(info);
    }

}