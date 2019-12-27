package wr1ttenyu.f1nal.study.designpattern.pattern23.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 需求
 * 四则运算问题
 * 通过解释器模式来实现四则运算， 如计算 a+b-c 的值， 具体要求
 * 1) 先输入表达式的形式， 比如 a+b+c-d+e, 要求表达式的字母不能重复
 * 2) 在分别输入 a ,b, c, d, e 的值
 * 3) 最后求出结果
 *
 * 传统方案解决四则运算问题分析
 * 1) 编写一个方法， 接收表达式的形式， 然后根据用户输入的数值进行解析， 得到结果
 * 2) 问题分析： 如果加入新的运算符， 比如 * / ( 等等， 不利于扩展， 另外让一个方法来解析会造成程序结构混乱，
 *      不够清晰.
 * 3) 解决方案： 可以考虑使用解释器模式， 即： 表达式 -> 解释器(可以有多种) -> 结果
 */
public class Client {

    public static void main(String[] args) throws IOException {
        String expStr = getExpStr();
        Map<String, Integer> value = getValue(expStr);
        Calculator calculator = new Calculator(expStr);
        int result = calculator.run(value);
        System.out.println("计算结果为: " + result);
    }

    // 获得表达式
    public static String getExpStr() throws IOException {
        System.out.print("请输入表达式：");
        return (new BufferedReader(new InputStreamReader(System.in))).readLine();
    }

    // 获得表达式
    public static Map<String, Integer> getValue(String expression) throws IOException {
        Map<String, Integer> varVal = new HashMap<>();
        char[] chars = expression.toCharArray();
        for (char ch : chars) {
            String key = String.valueOf(ch);
            if(ch != '+' && ch != '-' && !varVal.containsKey(key)) {
                System.out.print("请输入 " + ch +  " 的值:");
                String s = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                varVal.put(key, Integer.valueOf(s));
            }
        }
        return varVal;
    }
}
