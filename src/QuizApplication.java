import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class QuizApplication extends JFrame {
    
    // === QUIZ DATA === //
	public String[][] questions = {
        {"What is Java?", "Programming Language", "Operating System", "Database", "Browser"},
        {"Which company developed Java?", "Microsoft", "Apple", "Sun Microsystems", "IBM"},
        {"Which keyword is used to inherit a class in Java?", "this", "super", "extends", "implements"},
        {"Which of these is a SQL command?", "SELECT", "MOVE", "FETCH", "PUSH"},
        {"Which layer of OSI handles routing?", "Transport", "Network", "Session", "Application"},
        {"What is the capital of Japan?", "Beijing", "Seoul", "Tokyo", "Bangkok"},
        {"8 + 4 / 2 * 3 = ?", "18", "14", "10", "20"},
        {"Which is not an OOP concept?", "Encapsulation", "Polymorphism", "Abstraction", "Compilation"},
        {"RAM is a _________", "Permanent Memory", "Temporary Memory", "Input Device", "Output Device"},
        {"Which protocol is used for web browsing?", "FTP", "SMTP", "HTTP", "SSH"}
    };

	public String[] answers = {
        "Programming Language",
        "Sun Microsystems",
        "extends",
        "SELECT",
        "Network",
        "Tokyo",
        "14",
        "Compilation",
        "Temporary Memory",
        "HTTP"
    };
    
	public String[] userAnswers = new String[questions.length];


    // TRACKING VARIABLES
    
    boolean lifelineUsed = false;
    int time = 15;
    Timer timer;

    int index = 0;
    int score = 0;
    boolean answered = false;

    // UI references
    JButton lifeline5050;
    JLabel timerLabel;
    JButton skipButton;
    JLabel questionLabel;
    JButton opt1, opt2, opt3, opt4, confirmBtn;

    String selectedAnswer = "";

    public QuizApplication() {

        setTitle("Assessment System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        getContentPane().setBackground(new Color(230, 240, 255));

        addTopBar();    
        addCenterPanel(); 

        setVisible(true);
    }

    // ================= TOP BAR SECTION ================= //
    private void addTopBar() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screen.width;

        JPanel topBar = new JPanel();
        topBar.setLayout(null);
        topBar.setBounds(0, 0, width, 60);
        topBar.setBackground(new Color(50, 70, 120));
        add(topBar);

        timerLabel = new JLabel("Time: 15");
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        timerLabel.setBounds(width - 150, 15, 120, 30);
        topBar.add(timerLabel);

        lifeline5050 = new JButton("50-50");
        lifeline5050.setBounds(width - 250, 15, 80, 30);
        topBar.add(lifeline5050);

        skipButton = new JButton("Skip");
        skipButton.setBounds(width - 340, 15, 80, 30);
        topBar.add(skipButton);
        
        skipButton.addActionListener(e -> {
            timer.stop();

            if (index == questions.length - 1) {
                JOptionPane.showMessageDialog(this, "Last question cannot be skipped!");
                startTimer(); // restart timer for last question
                return;
            }

            autoNext();
        });

        
        lifeline5050.addActionListener(e -> {
            if (lifelineUsed) {
                JOptionPane.showMessageDialog(this, "50-50 already used!");
                return;
            }

            lifelineUsed = true;

            // Disable 2 wrong options
            int correctIndex = -1;
            for (int i = 1; i <= 4; i++) {
                if (questions[index][i].equals(answers[index])) {
                    correctIndex = i;
                    break;
                }
            }

            // Disable two random wrong options
            JButton[] btns = {opt1, opt2, opt3, opt4};
            int disabled = 0;

            for (int i = 0; i < 4; i++) {
                if (disabled < 2 && (i + 1) != correctIndex) {
                    btns[i].setEnabled(false);
                    disabled++;
                }
            }
        });


    }

    // ================= CENTER PANEL SECTION ================= //
    private void addCenterPanel() {

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screen.width;
        int height = screen.height;

        int panelWidth = 900;
        int panelHeight = 600;

        int x = (width - panelWidth) / 2;
        int y = (height - panelHeight) / 2;

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBounds(x, y, panelWidth, panelHeight);
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(centerPanel);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setBounds(40, 40, 800, 40);
        centerPanel.add(questionLabel);

        opt1 = new JButton();
        opt2 = new JButton();
        opt3 = new JButton();
        opt4 = new JButton();
        
        JButton[] options = {opt1, opt2, opt3, opt4};

        for (JButton b : options) {
            b.setBackground(new Color(235, 242, 255));  // light soft color
            b.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            b.setFocusPainted(false);  // removes blue outline
            b.setBorder(BorderFactory.createLineBorder(new Color(180,180,200))); 
        }


        opt1.setBounds(100, 150, 700, 40);
        opt2.setBounds(100, 220, 700, 40);
        opt3.setBounds(100, 290, 700, 40);
        opt4.setBounds(100, 360, 700, 40);

        opt1.addActionListener(e -> { selectedAnswer = opt1.getText(); highlightOption(opt1); });
        opt2.addActionListener(e -> { selectedAnswer = opt2.getText(); highlightOption(opt2); });
        opt3.addActionListener(e -> { selectedAnswer = opt3.getText(); highlightOption(opt3); });
        opt4.addActionListener(e -> { selectedAnswer = opt4.getText(); highlightOption(opt4); });

        centerPanel.add(opt1);
        centerPanel.add(opt2);
        centerPanel.add(opt3);
        centerPanel.add(opt4);

        confirmBtn = new JButton("CONFIRM");
        confirmBtn.setBackground(new Color(255, 100, 140));
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        confirmBtn.setBounds((panelWidth - 200) / 2, 450, 200, 45);
        centerPanel.add(confirmBtn);

        // CONFIRM/NEXT LOGIC
        confirmBtn.addActionListener(e -> {

            if (!answered) {

                if (selectedAnswer.equals("")) {
                    JOptionPane.showMessageDialog(this, "Please select an option first!");
                    return;
                }

                if (selectedAnswer.equals(answers[index])) {
                    score++;
                }
                userAnswers[index] = "Skipped";


                answered = true;
                confirmBtn.setText("NEXT");
            }
            else {
                autoNext();
            }
        });

        loadQuestion();
        startTimer();

    }

    private void loadQuestion() {
        questionLabel.setText("Q" + (index + 1) + ": " + questions[index][0]);
        opt1.setText(questions[index][1]);
        opt2.setText(questions[index][2]);
        opt3.setText(questions[index][3]);
        opt4.setText(questions[index][4]);
    }

    private void resetButtonsColor() {
        JButton[] btns = {opt1, opt2, opt3, opt4};
        for (JButton b : btns) {
            b.setBackground(Color.WHITE);
        }
    }

    private void highlightOption(JButton selected) {
        resetButtonsColor();
        selected.setBackground(new Color(180, 220, 255));
    }

    private void showResult() {
        timer.stop();
        saveResultToFile(score, questions.length);
        new ResultPage(score, questions.length);
        dispose();
    }

    
    private void startTimer() {
        time = 15;
        timerLabel.setText("Time: " + time);

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(1000, e -> {
            time--;
            timerLabel.setText("Time: " + time);

            if (time == 0) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "Time's up! Moving to next question.");
                autoNext();
            }

        });

        timer.start();
    }
    
    private void autoNext() {
    	userAnswers[index] = "Skipped";
        index++;

        if (index == questions.length) {
            showResult();
            return;
        }

        selectedAnswer = "";
        answered = false;
        confirmBtn.setText("CONFIRM");
        resetButtonsColor();
        loadQuestion();
        startTimer();
        
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);

    }
    
    private void saveResultToFile(int score, int total) {
        try (FileWriter fw = new FileWriter("results.txt", true)) {
            int percentage = (score * 100) / total;

            fw.write("Name: " + UserData.name + "\n");
            fw.write("Email: " + UserData.email + "\n");
            fw.write("Roll: " + UserData.roll + "\n");
            fw.write("Score: " + score + "/" + total + "\n");
            fw.write("Percentage: " + percentage + "%\n");
            fw.write("Status: " + (score >= 5 ? "PASS" : "FAIL") + "\n");
            fw.write("Date: " + java.time.LocalDateTime.now() + "\n");
            fw.write("-------------------------------------\n\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new QuizApplication();
    }
}
