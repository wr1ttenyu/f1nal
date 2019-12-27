package wr1ttenyu.f1nal.study.designpattern.pattern23.interpreter;

public abstract class SymbolExpression extends Expression {

    protected Expression left;

    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
