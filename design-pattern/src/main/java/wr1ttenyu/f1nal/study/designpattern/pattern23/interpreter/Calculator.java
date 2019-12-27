package wr1ttenyu.f1nal.study.designpattern.pattern23.interpreter;

import java.util.Map;
import java.util.Stack;

public class Calculator {

    private String expression;

    private Stack<Expression> expressionStack = new Stack<>();

    public Calculator(String expression) {
        this.expression = expression;

        char[] chars = expression.toCharArray();
        Expression left;
        Expression right;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            switch (ch) {
                case '+':
                    left = expressionStack.pop();
                    right = new VarExpression(String.valueOf(chars[++i]));
                    expressionStack.push(new AddExpression(left, right));
                    break;
                case '-':
                    left = expressionStack.pop();
                    right = new VarExpression(String.valueOf(chars[++i]));
                    expressionStack.push(new SubExpression(left, right));
                    break;
                default:
                    expressionStack.push(new VarExpression(String.valueOf(ch)));
                    break;
            }
        }
    }

    public int run(Map<String, Integer> varVal) {
        return expressionStack.pop().interpreter(varVal);
    }
}
