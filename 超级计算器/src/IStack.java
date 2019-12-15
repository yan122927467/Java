import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class IStack {
    //用字符串做参数重载的计算函数，调用下面用字符串数组做方法参数的函数
    public static double compute(String expression) {
        String[] arr = strToExpressionArr(expression);
            return compute(strToExpressionArr(expression));
    }

    //把表达式转换为数组
    private static String[] strToExpressionArr(String str) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                if (str.charAt(i)!='.') {
                    str = str.substring(0, i) +
                            " " + str.charAt(i) + " " +
                            str.substring(i + 1, str.length());
                    i += 2;
                }
            }
        }
        Scanner sc = new Scanner(str);
        while (sc.hasNext())
            list.add(sc.next());

        return list.toArray(new String[list.size()]);
    }

    //计算算术表达式
    private static double compute(String[] expression) {
        Stack<Double> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();

        for (int i = 0; i < expression.length; i++) {
            String ele = expression[i];

            //如果是数字，进入数字栈
            if (isNumber(ele)) {
                numStack.push(new Double(ele));
            }
            //以下是符号
            //遇到加减号，把栈顶的符号都处理完，直到遇到（左括号和栈空情况，最后把提取到的符号压入栈
            else if (ele.charAt(0) == '+' || ele.charAt(0) == '-') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    double b = numStack.pop();
                    double a = numStack.pop();
                    numStack.push(getAnswer(a, opStack.pop(), b));
                }

                if (!opStack.isEmpty() && opStack.peek() == '(')
                    opStack.pop();

                opStack.push(ele.charAt(0));
            }
            //遇到乘除号，把前面的乘除号处理完，最后把提取到的符号压入栈
            else if (ele.charAt(0) == '×' || ele.charAt(0) == '÷') {
                while (!opStack.isEmpty() && opStack.peek() != '(' &&
                        opStack.peek() != '+' && opStack.peek() != '-') {
                    double b = numStack.pop();
                    double a = numStack.pop();
                    numStack.push(getAnswer(a, opStack.pop(), b));
                }

                if (!opStack.isEmpty() && opStack.peek() == '(')
                    opStack.pop();

                opStack.push(ele.charAt(0));
            }
            //遇到左括号，只进栈
            else if (ele.charAt(0) == '(') {
                opStack.push(ele.charAt(0));
            }
            //遇到右括号，只处理栈顶全部符号
            else if (ele.charAt(0) == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    double b = numStack.pop();
                    double a = numStack.pop();
                    numStack.push(getAnswer(a, opStack.pop(), b));
                }

                if (!opStack.isEmpty() && opStack.peek() == '(')
                    opStack.pop();
            }
        }

        //最后没有以右括号结尾的表达式还得另外把剩下的符号处理完
        while (!opStack.isEmpty()) {
            double b = numStack.pop();
            double a = numStack.pop();
            numStack.push(getAnswer(a, opStack.pop(), b));
        }

        return numStack.peek();
    }

    //判断是否数字
    private static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9')
                if (s.charAt(i)!='.')
                return false;
        }
        return true;
    }

    //四则运算
    private static double getAnswer(double a, char operator, double b) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '×':
                return a * b;
            case '÷':
                return a / b;
        }
        return 0;
    }
}