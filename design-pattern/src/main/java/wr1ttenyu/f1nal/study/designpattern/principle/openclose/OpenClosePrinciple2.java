package wr1ttenyu.f1nal.study.designpattern.principle.openclose;

public class OpenClosePrinciple2 {

    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();

        Rectangle rectangle = new Rectangle();
        Circle circle = new Circle();

        graphicEditor.drawShape(rectangle);
        graphicEditor.drawShape(circle);
    }


}

/**
 * 开闭原则改进方法：
 * 改进思路 -->  把创建Shape类做成抽象类，并且提供一个抽象的draw方法，让子类去实现即可，
 *  这样我们有的图形加入时，只需要让新的图形继承Shape抽象类并实现抽象的draw方法即可，
 *  使用方的代码就不需要进行修改 --> 满足开闭原则
 */
class GraphicEditor1 {
    public void drawShape(Shape1 s) {
        s.draw();
    }
}

abstract class Shape1 {
    int mType;

    public abstract void draw();
}

class Rectangle1 extends Shape1 {
    Rectangle1() {
        super.mType = 1;
    }

    @Override
    public void draw() {
        System.out.println("绘制矩形");
    }
}

class Circle1 extends Shape1 {
    Circle1() {
        super.mType = 2;
    }

    @Override
    public void draw() {
        System.out.println("绘制圆形");
    }
}