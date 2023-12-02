import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ArithmeticTestSystem extends JFrame {
    private JLabel questionLabel, resultLabel;
    private JTextField answerField;
    private JButton submitButton;
    private int correctCount, totalCount;
    private BufferedWriter writer;

    public ArithmeticTestSystem() {
        super("算术运算测试与评分系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        questionLabel = new JLabel();
        resultLabel = new JLabel();
        answerField = new JTextField(10);
        submitButton = new JButton("提交");

        // 设置布局
        setLayout(new GridLayout(4, 1));
        add(questionLabel);
        add(answerField);
        add(submitButton);
        add(resultLabel);

        // 生成第一道题目
        generateQuestion();

        // 设置事件监听
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        // 初始化文件写入器
        try {
            writer = new BufferedWriter(new FileWriter("exam_records.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(100);
        int num2 = random.nextInt(100);
        boolean isAddition = random.nextBoolean();

        if (isAddition) {
            questionLabel.setText("题目：" + num1 + " + " + num2);
        } else {
            if (num1 < num2) {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }
            questionLabel.setText("题目：" + num1 + " - " + num2);
        }
        resultLabel.setText("");
        totalCount++;
    }

    private void checkAnswer() {
        String userInput = answerField.getText().trim();

        if (!userInput.isEmpty()) {
            int userAnswer = Integer.parseInt(userInput);
            int correctAnswer = calculateCorrectAnswer();

            if (userAnswer == correctAnswer) {
                resultLabel.setText("回答正确！");
                correctCount++;
            } else {
                resultLabel.setText("回答错误，正确答案是：" + correctAnswer);
            }

            // 保存考试记录
            saveExamRecord(userAnswer, correctAnswer);

            // 生成下一道题目
            generateQuestion();

            // 清空输入框
            answerField.setText("");
        } else {
            resultLabel.setText("请输入答案！");
        }
    }

    private int calculateCorrectAnswer() {
        String[] parts = questionLabel.getText().split(" ");
        int num1 = Integer.parseInt(parts[1]);
        int num2 = Integer.parseInt(parts[3]);

        if (questionLabel.getText().contains("+")) {
            return num1 + num2;
        } else {
            return num1 - num2;
        }
    }

    private void saveExamRecord(int userAnswer, int correctAnswer) {
        try {
            writer.write("题目：" + questionLabel.getText() + "，用户答案：" + userAnswer + "，正确答案：" + correctAnswer + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArithmeticTestSystem().setVisible(true);
            }
        });
    }
}
