import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

//创建计算器的jframe,菜单。布局
public class CalculatorJFrame extends JFrame implements ActionListener {
    int flag = 0;
    int flag1 = 0;
    int flag2 = 0;
    String ab = "";
    //菜单项
    private JMenuItem jm1_history,jm2_take,jm3_help;
    private JMenu jm1,jm2,jm3;
    private JMenuBar jMenuBar;//菜单栏
    private JPanel jPanel1,jPanel2,jPanel3,jPanel4,jPanel5;
    private JTextField jTextField,jTextField2;//文本框
    private JButton button_k1,button_k2,button_k3,button_ac,button_c;//第一行
    private JButton button_7,button_8,button_9,button_sqrt,button_t1;//第二行
    private JButton button_4,button_5,button_6,button_x,button_t2;//第三行
    private JButton button_1,button_2,button_3,button_t3,button_t4;//第四行
    private JButton button_0,button_t5,button_t6,button_t7,button_t8;//第五行
    private char a;
    public CalculatorJFrame(){
        //题目
        super("科学计算器");
        //设置位置大小
        this.setBounds(400,300,600,500);
        //关闭方式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //添加菜单，自定义菜单方法
        this.setMyMenu();
        //网格布局
        this.setLayout(new GridLayout(8,1));
        //添加textfiled2 等式
        jTextField2 = new JTextField(30);
        this.add(jTextField2);
        jTextField2.setEditable(false);
        //添加textfiled 显示答案
        jTextField = new JTextField(30);
        this.add(jTextField);
        jTextField.setEditable(false);
        //添加第一行按钮 自定义方法
        this.setButtonLine1();
        this.setButtonLine2();
        this.setButtonLine3();
        this.setButtonLine4();
        this.setButtonLine5();
        this.setVisible(true);
    }
    public void setMyMenu(){
        //菜单栏
        jm1 = new JMenu("查看(V)");
        jm2 = new JMenu("编辑(E)");
        jm3 = new JMenu("帮助(H)");
        jm1_history = new JMenuItem("历史");
        jm2_take = new JMenuItem("保存");
        jm3_help = new JMenuItem("使用说明");
        jMenuBar = new JMenuBar();
        jMenuBar.add(jm1);
        jMenuBar.add(jm2);
        jMenuBar.add(jm3);
        jm1.add(jm1_history);
        jm2.add(jm2_take);
        jm3.add(jm3_help);
        jm3_help.addActionListener(this);
        jm2_take.addActionListener(this);
        jm1_history.addActionListener(this);
        jMenuBar.setSize(400,20);
        this.add(jMenuBar);
    }
    public void setButtonLine1(){
        jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(1,5,4,4));
        this.add(jPanel1);
        button_k1 = new JButton("(");
        button_k1.addActionListener(this);
        button_k2 = new JButton(")");
        button_k2.addActionListener(this);
        button_k3 = new JButton("%");
        button_k3.addActionListener(this);
        button_ac = new JButton("AC");
        button_ac.addActionListener(this);
        button_c = new JButton("C");
        button_c.addActionListener(this);
        jPanel1.add(button_k1);
        jPanel1.add(button_k2);
        jPanel1.add(button_k3);
        jPanel1.add(button_ac);
        jPanel1.add(button_c);
    }
    public void setButtonLine2(){
        jPanel2 = new JPanel();
        jPanel2.setLayout(new GridLayout(1,5,4,4));
        this.add(jPanel2);
        button_7 = new JButton("7");
        button_7.addActionListener(this);
        button_8 = new JButton("8");
        button_8.addActionListener(this);
        button_9 = new JButton("9");
        button_9.addActionListener(this);
        button_sqrt = new JButton("sqrt");
        button_sqrt.addActionListener(this);
        button_t1 = new JButton("÷");
        button_t1.addActionListener(this);
        jPanel2.add(button_7);
        jPanel2.add(button_8);
        jPanel2.add(button_9);
        jPanel2.add(button_sqrt);
        jPanel2.add(button_t1);

    }
    public void setButtonLine3(){
        jPanel3 = new JPanel();
        jPanel3.setLayout(new GridLayout(1,5,4,4));
        this.add(jPanel3);
        button_4 = new JButton("4");
        button_4.addActionListener(this);
        button_5 = new JButton("5");
        button_5.addActionListener(this);
        button_6 = new JButton("6");
        button_6.addActionListener(this);
        button_x = new JButton("x²");
        button_x.addActionListener(this);
        button_t2 = new JButton("×");
        button_t2.addActionListener(this);
        jPanel3.add(button_4);
        jPanel3.add(button_5);
        jPanel3.add(button_6);
        jPanel3.add(button_x);
        jPanel3.add(button_t2);
    }
    public void setButtonLine4(){
        jPanel4 = new JPanel();
        jPanel4.setLayout(new GridLayout(1,5,4,4));
        this.add(jPanel4);
        button_1 = new JButton("1");
        button_1.addActionListener(this);
        button_2 = new JButton("2");
        button_2.addActionListener(this);
        button_3 = new JButton("3");
        button_3.addActionListener(this);
        button_t3 = new JButton("e");
        button_t3.addActionListener(this);
        button_t4 = new JButton("-");
        button_t4.addActionListener(this);
        jPanel4.add(button_1);
        jPanel4.add(button_2);
        jPanel4.add(button_3);
        jPanel4.add(button_t3);
        jPanel4.add(button_t4);

    }
    public void setButtonLine5(){
        jPanel5 = new JPanel();
        jPanel5.setLayout(new GridLayout(1,5,4,4));
        this.add(jPanel5);
        button_0 = new JButton("0");
        button_0.addActionListener(this);
        button_t5 = new JButton(".");
        button_t5.addActionListener(this);
        button_t6 = new JButton("=");
        button_t6.addActionListener(this);
        button_t7 = new JButton("π");
        button_t7.addActionListener(this);
        button_t8 = new JButton("+");
        button_t8.addActionListener(this);
        jPanel5.add(button_0);
        jPanel5.add(button_t5);
        jPanel5.add(button_t6);
        jPanel5.add(button_t7);
        jPanel5.add(button_t8);
    }
    //保存到文件下
    public void setFIle() throws IOException {
        String path = "/Users/Yan/Desktop/calculator.txt";
        File file = new File(path);
        if (!file.exists())
            file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(ab);
        bw.flush();
        bw.close();
        fw.close();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("使用说明"))
            JOptionPane.showMessageDialog(this,"sqrt x² e π %输入相应的值，不可输入其他的运算符，直接出结果。C为退格，AC为清屏");
        if (e.getActionCommand().equals("保存")) {
            try {
                this.setFIle();
                JOptionPane.showMessageDialog(this,"保存成功至/Users/Yan/Desktop/calculator.txt");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,"保存失败！！");
            }
        }
        if (e.getActionCommand().equals("历史")){
            JOptionPane.showMessageDialog(this,ab);
        }
        if (flag2 == 1&&!e.getActionCommand().equals("+")&&!e.getActionCommand().equals("-")&&!e.getActionCommand().equals("÷")&&!e.getActionCommand().equals("×")&&!e.getActionCommand().equals("π")&&!e.getActionCommand().equals("e"))
            jTextField.setText("");
        flag2 = 0;
        String abc = jTextField.getText();
        if (jTextField.getText().length()!=0) {
            a = jTextField.getText().charAt(jTextField.getText().length() - 1);
        }
        //一到九输入
        if ((e.getActionCommand().equals("1")||e.getActionCommand().equals("2")||e.getActionCommand().equals("3")||e.getActionCommand().equals("4")||e.getActionCommand().equals("5")||e.getActionCommand().equals("6")||e.getActionCommand().equals("7")||e.getActionCommand().equals("8")||e.getActionCommand().equals("9"))&&a!=')')
            jTextField.setText(jTextField.getText()+String.format(e.getActionCommand()));
        //if (e.getActionCommand().equals("*")||e.getActionCommand().equals("+")||e.getActionCommand().equals("-")||e.getActionCommand().equals("÷")||)
        //退位
        if (e.getActionCommand().equals("C")) {
            if (abc.length() != 0) {
                try {
                    jTextField.setText(jTextField.getText(0,(int)jTextField.getText().length()-1));
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }
        //清零
        if(e.getActionCommand().equals("AC")&&jTextField.getText()!="") {
            jTextField.setText("");
            jTextField2.setText("");
        }
        /*
        * 输入（ 的限制条件
        * 1.前面一个有运算符可以输出
        * 开头可以输出
        * */
        if (e.getActionCommand().equals("(")){
            if (!abc.equals("")) {
                a = jTextField.getText().charAt(jTextField.getText().length()-1);
                if (a == '+' || a == '-' || a == '×' || a == '÷') {
                    jTextField.setText(jTextField.getText() + "(");
                    flag++;
                }
            }else {
                jTextField.setText(jTextField.getText() + "(");
                flag++;
            }
    }
        /*
         * 输入")"的限制条件
         * 前面必须有孤独的"("
         * 前面不能有运算符
         * 开头不可以输出*/
        if (e.getActionCommand().equals(")")){

            if (flag>0&&a!='+'&&a!='-'&&a!='×'&&a!='÷'&&a!='('){
                jTextField.setText(jTextField.getText()+")");
                flag--;
            }
        }
        /*运算符规则
        * 前一个必须是数字或者是 ）
        * 只能有一个运算符，不能出现多余运算符
        * */
        if (e.getActionCommand().equals("+"))
            if (jTextField.getText().length()!=0)
            if (a!='×'&&a!='÷'&&a!='-'&&a!='('&&a!='+') {
                jTextField.setText(jTextField.getText() + "+");
                flag1 = 0;
            }
        if (e.getActionCommand().equals("-"))
            if (jTextField.getText().length()!=0)
            if (a!='×'&&a!='÷'&&a!='+'&&a!='('&&a!='-') {
                jTextField.setText(jTextField.getText() + "-");
                flag1 = 0;
            }
        if (e.getActionCommand().equals("×"))
            if (jTextField.getText().length()!=0)
            if (a!='+'&&a!='÷'&&a!='-'&&a!='('&&a!='×') {
                jTextField.setText(jTextField.getText() + "×");
                flag1 = 0;
            }
        if (e.getActionCommand().equals("÷"))
            if (jTextField.getText().length()!=0)
            if (a!='×'&&a!='+'&&a!='-'&&a!='('&&a!='÷') {
                jTextField.setText(jTextField.getText() + "÷");
                flag1 = 0;
            }
        //0，小数点，sqrt，x²，e, 百分号 的处理
        if (e.getActionCommand().equals("0"))
            if (abc!="")
                jTextField.setText(jTextField.getText()+"0");

        if (e.getActionCommand().equals(".")) {

            if ((a == '1' || a == '2' || a == '3' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8' || a == '9' || a == '0') && flag1 == 0) {
                flag1++;
                jTextField.setText(jTextField.getText() + ".");
            }
        }
        if (e.getActionCommand().equals("sqrt")){
            jTextField2.setText("sqrt("+jTextField.getText()+")=");
            if (jTextField.getText().length()!=0) {
                jTextField.setText(String.valueOf(Math.sqrt(Double.valueOf(jTextField.getText()))));
                ab = ab +"\n"+ jTextField2.getText()+jTextField.getText();
            }
            flag2 = 1;
        }
        if (e.getActionCommand().equals("%")){
            jTextField2.setText(jTextField.getText()+"%=");
            if (jTextField.getText().length()!=0) {
                jTextField.setText(String.valueOf(Double.valueOf(jTextField.getText())*0.01));
                ab = ab +"\n"+ jTextField2.getText()+jTextField.getText();
            }
            flag2 = 1;
        }
        if (e.getActionCommand().equals("π")) {
            jTextField2.setText(jTextField.getText()+"π=");
            if (jTextField.getText().length()!=0)
            jTextField.setText(String.format("%.4f",Double.valueOf(jTextField.getText())*Math.PI));

            if (jTextField.getText().length() == 0)
                jTextField.setText(String.format("%.4f",Math.PI));
            ab = ab +"\n"+ jTextField2.getText()+jTextField.getText();
            flag2 = 1;
        }
        if (e.getActionCommand().equals("e")) {
            jTextField2.setText(jTextField.getText()+"e=");
            if (jTextField.getText().length()!=0)
                jTextField.setText(String.format("%.4f",Double.valueOf(jTextField.getText())*Math.E));
            if (jTextField.getText().length() == 0)
                jTextField.setText(String.format("%.4f",Math.E));
            ab = ab +"\n"+ jTextField2.getText()+jTextField.getText();
            flag2 = 1;
        }
        if (e.getActionCommand().equals("x²")){
            jTextField2.setText(jTextField.getText()+"²");
            if (jTextField.getText().length()!=0){
            jTextField.setText(String.valueOf((Double.valueOf(jTextField.getText()))*(Double.valueOf(jTextField.getText()))));
            ab = ab +"\n"+ jTextField2.getText()+jTextField.getText();
            }
            flag2 = 1;
        }
        if (e.getActionCommand().equals("=")&&jTextField.getText().length()!=0){
            String s = jTextField.getText();
            jTextField2.setText(s+"=");
            if (IStack.compute(s)!=-1) {
                jTextField.setText(String.format("%.4f", IStack.compute(s)));
                ab = ab + "\n" + jTextField2.getText() + jTextField.getText();
            }else
                jTextField.setText("错误！");
            flag2 = 1;
        }
}
}

