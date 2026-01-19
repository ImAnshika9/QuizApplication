import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class ResultPage extends JFrame {

    public ResultPage(int score, int total) {

        setTitle("Assessment Result");
        setSize(750, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 245, 255));

        int percentage = (score * 100) / total;
        boolean isPass = score >= 5;
        String status = isPass ? "PASS" : "FAIL";
        String emoji = isPass ? "🎉" : "❌";

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setBackground(new Color(50, 90, 160));
        header.setBounds(0, 0, 750, 70);
        header.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JLabel title = new JLabel("Test Result Summary " + emoji);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title);
        add(header);

        // ===== USER INFO PANEL =====
        JPanel userBox = new JPanel();
        userBox.setBounds(60, 90, 630, 110);
        userBox.setBackground(Color.WHITE);
        userBox.setBorder(BorderFactory.createLineBorder(new Color(120,120,160), 1));
        userBox.setLayout(null);
        add(userBox);

        JLabel nameLbl = new JLabel("Name: " + UserData.name);
        nameLbl.setBounds(30, 20, 300, 25);
        nameLbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userBox.add(nameLbl);

        JLabel emailLbl = new JLabel("Email: " + UserData.email);
        emailLbl.setBounds(30, 50, 300, 25);
        emailLbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userBox.add(emailLbl);

        JLabel rollLbl = new JLabel("Roll: " + UserData.roll);
        rollLbl.setBounds(30, 80, 300, 25);
        rollLbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userBox.add(rollLbl);

        // ===== RESULT BOX =====
        JPanel resultBox = new JPanel();
        resultBox.setBounds(60, 220, 630, 160);
        resultBox.setBackground(Color.WHITE);
        resultBox.setBorder(BorderFactory.createLineBorder(new Color(120,120,160), 1));
        resultBox.setLayout(null);
        add(resultBox);

        JLabel scoreLbl = new JLabel("Score: " + score + "/" + total);
        scoreLbl.setBounds(30, 25, 500, 30);
        scoreLbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        resultBox.add(scoreLbl);

        JLabel percentLbl = new JLabel("Percentage: " + percentage + "%");
        percentLbl.setBounds(30, 65, 500, 30);
        percentLbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        resultBox.add(percentLbl);

        JLabel statusLbl = new JLabel("Status: " + status + " " + emoji);
        statusLbl.setBounds(30, 105, 500, 30);
        statusLbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        statusLbl.setForeground(isPass ? new Color(0,150,0) : Color.RED);
        resultBox.add(statusLbl);   

        // ===== BUTTONS =====

        JButton retakeBtn = new JButton("RETAKE TEST");
        retakeBtn.setBounds(140, 420, 150, 45);
        styleButton(retakeBtn);
        retakeBtn.addActionListener(e -> {
            new HomePage();
            dispose();
        });
        add(retakeBtn);

        JButton saveBtn = new JButton("SAVE REPORT");
        saveBtn.setBounds(310, 420, 150, 45);
        styleButton(saveBtn);
        saveBtn.addActionListener(e -> saveReport(score, total, percentage, status));
        add(saveBtn);

        JButton exitBtn = new JButton("CLOSE");
        exitBtn.setBounds(480, 420, 150, 45);
        styleButton(exitBtn);
        exitBtn.addActionListener(e -> System.exit(0));
        add(exitBtn);

        setVisible(true);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(50, 90, 160));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBorder(BorderFactory.createEmptyBorder());
    }

    private void saveReport(int score, int total, int percent, String status) {
        try (FileWriter fw = new FileWriter("result_report.txt", true)) {
            fw.write("===== TEST RESULT REPORT =====\n");
            fw.write("Name: " + UserData.name + "\n");
            fw.write("Email: " + UserData.email + "\n");
            fw.write("Roll: " + UserData.roll + "\n\n");
            fw.write("Score: " + score + "/" + total + "\n");
            fw.write("Percentage: " + percent + "%\n");
            fw.write("Status: " + status + "\n");
            fw.write("Date: " + java.time.LocalDateTime.now() + "\n");
            fw.write("--------------------------------------\n\n");

            JOptionPane.showMessageDialog(this, "Report saved as result_report.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
