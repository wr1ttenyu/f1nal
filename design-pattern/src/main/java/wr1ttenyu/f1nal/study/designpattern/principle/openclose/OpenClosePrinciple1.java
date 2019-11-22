package wr1ttenyu.f1nal.study.designpattern.principle.openclose;

public class OpenClosePrinciple1 {

    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();

        Rectangle rectangle = new Rectangle();
        Circle circle = new Circle();

        graphicEditor.drawShape(rectangle);
        graphicEditor.drawShape(circle);
    }


}

/**
 * 这种绘制图形方法的设计优缺点
 * 1. 优点是比较好理解，简单易操作
 * 2. 确定是违反了ocp原则，违反了模块和函数应该对扩展开放（对提供方），对修改关闭（对调用方）。
 *  比如增加一个三角形，对于调用方（GraphicEditor）来说需要修改，也就违反了对修改关闭
 */
class GraphicEditor {
    public void drawShape(Shape s) {
        if (s.mType == 1) {
            drawRectangle(s);
        } else if (s.mType == 2) {
            drawCircle(s);
        }
    }

    private void drawRectangle(Shape r) {
        System.out.println("绘制矩形");
    }

    private void drawCircle(Shape c) {
        System.out.println("绘制圆形");
    }
}

class Shape {
    int mType;
}

class Rectangle extends Shape {
    Rectangle() {
        super.mType = 1;
    }
}

class Circle extends Shape {
    Circle() {
        super.mType = 2;
    }
}