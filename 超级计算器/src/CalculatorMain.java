import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class CalculatorMain {
    public static void main(String[] args) throws Exception {
        new CalculatorJFrame();
        String str="System.out.print(3-2*2);";
        eval(str);
    }
    private static void eval(String str) throws Exception {
        /*由于java最小的执行单元是类，如果想要执行该语句，必须为其构建一个完整的类*/
        //1-使用StringBuilder拼接一个完整的Hello World程序
        StringBuilder strB=new StringBuilder(80);
        strB.append("public class Hello {");
        strB.append("public static void main(String[] args) {");
        strB.append(str);
        strB.append("}}");
        //2-将拼接好的字符串保存到一个Hello.java的文件里
        OutputStream out=new FileOutputStream("Hello.java");
        out.write(strB.toString().getBytes());
        out.close();
        System.out.println();
        //3-调用java进程来编译Hello.java
        Process javacProcess=Runtime.getRuntime().exec("javac Hello.java");
        //4-读取javac进程中的错误流信息
        InputStream error=javacProcess.getErrorStream();
        //读取流中的数据
        byte[] b=new byte[1024];
        //对每一个文件里的内容进行复制
        int len = -1;//表示已经读取了多少个字节，如果len返回-1，表示已经读到最后
        while ((len = error.read(b)) != -1) {
            String msg=new String(b,0,len);
            System.out.println(msg);
        }
        //关闭资源
        error.close();
        //5-调用java进程来运行Hello.clss
        Process javaProcess=Runtime.getRuntime().exec("java Hello");
        //6-读取java进程流中的信息
        InputStream info=javaProcess.getInputStream();
        while ((len = info.read(b)) != -1) {
            String msg=new String(b,0,len);
            System.out.println(msg);
        }
        info.close();
        //7-删除java和class文件
        new File("Hello.java").delete();
        new File("Hello.class").delete();
    }
}
