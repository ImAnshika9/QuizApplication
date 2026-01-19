import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    JTextField nameField, emailField, rollField;
    JButton startBtn;

    public HomePage() {

        setTitle("Quiz Application - Welcome");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== TOP HEADER =====
        JPanel header = new JPanel();
        header.setBackground(new Color(50, 90, 160));
        header.setPreferredSize(new Dimension(700, 70));

        JLabel title = new JLabel("Welcome to Online Assessment System");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title);

        add(header, BorderLayout.NORTH);

        // ===== CENTER PANEL =====
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(240, 245, 255));

        JLabel subtitle = new JLabel("Enter Your Details", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        subtitle.setBounds(200, 20, 300, 40);
        formPanel.add(subtitle);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(170, 90, 120, 30);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(300, 90, 220, 30);
        addTextFieldUI(nameField);
        formPanel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(170, 150, 120, 30);
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(300, 150, 220, 30);
        addTextFieldUI(emailField);
        formPanel.add(emailField);

        JLabel rollLabel = new JLabel("Roll No:");
        rollLabel.setBounds(170, 210, 120, 30);
        rollLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(rollLabel);

        rollField = new JTextField();
        rollField.setBounds(300, 210, 220, 30);
        addTextFieldUI(rollField);
        formPanel.add(rollField);

        // ===== BUTTON =====
        startBtn = new JButton("START TEST");
        startBtn.setBounds(270, 290, 160, 45);
        styleButton(startBtn);
        startBtn.addActionListener(e -> validateInput());
        formPanel.add(startBtn);

        add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addTextFieldUI(JTextField field) {
        field.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 160), 1));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(50, 90, 160));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBorder(BorderFactory.createEmptyBorder());
    }

    private void validateInput() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String roll = rollField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || roll.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Invalid Email Format!");
            return;
        }

        UserData.name = name;
        UserData.email = email;
        UserData.roll = roll;

        new InstructionsPage();
        dispose();
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
