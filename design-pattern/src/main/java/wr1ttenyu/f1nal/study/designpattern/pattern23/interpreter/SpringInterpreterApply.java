package wr1ttenyu.f1nal.study.designpattern.pattern23.interpreter;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpringInterpreterApply {

    public static void main(String[] args) {
        // 创建 表达式 分析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 通过 表达式 分析器  分析  表达式  获取到 expression
        Expression expression = parser.parseExpression("10 * (2 + 1) * 1 + 66");
        // 通过 expression 获取 表达式  的计算结果
        /**
         * 其实 expression 里面包含的是 对 表达式解析之后 获得的一系列
         * {@link org.springframework.expression.spel.SpelNode}
         *
         * 最终通过 SpelNode 来 获取表达式结果
         */
        int value = (Integer)expression.getValue();
        System.out.println(value);
    }
}
