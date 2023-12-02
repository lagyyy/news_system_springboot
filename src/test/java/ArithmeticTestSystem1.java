import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArithmeticTestSystem1 extends JFrame {
    private List<JLabel> questionLabel, resultLabel;
    private List<JTextField> answerField;
    private JButton submitButton;
    private String[] strings = {"+","-"};

    public void ArithmeticTestSystem1(){
        setTitle("算术运算测试与评分系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        setLocationRelativeTo(null);
        setLayout(new GridLayout(11,3));

        questionLabel = new ArrayList<>();
        resultLabel = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int num1 = random.nextInt(100);
            int num2 = random.nextInt(100);
            JLabel jLabel = new JLabel();


//            随机生成+或者-
            int i1 = random.nextInt(2);
            String string = strings[i1];


            jLabel.setText("题目:"+num1+string+num2+"=");
            add(jLabel);
            JLabel jLabel1 = new JLabel();
            if (string.equals("+")){
                jLabel1.setText(num1+num2+"");
            }else {
                jLabel1.setText(num1-num2+"");
            }
            add(jLabel1);
            JTextField jTextField = new JTextField();
            add(jTextField);


        }


    }

    public static void main(String[] args) {
        new ArithmeticTestSystem1().setVisible(true);
    }
}
