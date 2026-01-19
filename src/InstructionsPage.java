import javax.swing.*;
import java.awt.*;

public class InstructionsPage extends JFrame {

    public InstructionsPage() {

        setTitle("Test Instructions");
        setSize(700, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setBackground(new Color(50, 90, 160));
        header.setBounds(0, 0, 700, 70);
        header.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JLabel title = new JLabel("Assessment Instructions");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title);
        add(header);

        // ===== CENTER PANEL =====
        JPanel box = new JPanel();
        box.setBackground(Color.WHITE);
        box.setBounds(50, 100, 600, 260);
        box.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 160), 1));
        box.setLayout(null);
        add(box);

        JLabel instr = new JLabel("<html>" +
                "<b>Please read instructions carefully:</b><br><br>" +
                "1. Total Questions: 10<br>" +
                "2. Time per question: <b>15 seconds</b><br>" +
                "3. Skip option available<br>" +
                "4. 50-50 Lifeline available (once)<br>" +
                "5. Score ≥ 5 is considered <b style='color:green;'>PASS</b><br>" +
                "6. Do not close/minimize during test.<br>" +
                "</html>"
        );
        instr.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        instr.setBounds(30, 10, 550, 230);
        box.add(instr);

        // ===== BUTTON =====
        JButton startBtn = new JButton("START TEST");
        startBtn.setBounds(270, 380, 160, 45);
        startBtn.setBackground(new Color(50, 90, 160));
        startBtn.setForeground(Color.WHITE);
        startBtn.setFont(new Font("Segoe UI", Font.BOLD, 17));
        startBtn.setFocusPainted(false);
        add(startBtn);

        startBtn.addActionListener(e -> {
            new QuizApplication();
            dispose();
        });

        setVisible(true);
    }
}
