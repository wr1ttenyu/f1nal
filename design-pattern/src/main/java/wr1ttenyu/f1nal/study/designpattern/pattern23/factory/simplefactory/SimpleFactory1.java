package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.simplefactory;

import wr1ttenyu.f1nal.study.designpattern.utils.DesignPatterUtil;

/**
 * 需求：
 * 一个披萨的项目： 要便于披萨种类的扩展， 要便于维护
 * 1) 披萨的种类很多(比如 GreekPizz、 CheesePizz 等)
 * 2) 披萨的制作有 prepare， bake, cut, box
 * 3) 完成披萨店订购功能。
 *
 * {@link OrderPizza} 的优缺点
 * 1.优点：简单 好理解 易操作
 * 2.缺点：违反了ocp原则，如果新增了一种pizza，在OrderPizza可以有多种实现时，则需要对所有创建pizza的代码进行修改
 *
 * 改进思路：
 * 分析：代码修改可以接受，但是如果我们在其它的地方也有创建pizza的代码，就意味着，
 *      也需要修改，而创建pizza的代码，往往有多处
 * 思路：把创建pizza对象的代码封装到一个类中，这样我们增加pizza种类时，只需要修改这个类就行了 --> 简单工厂模式
 */
public class SimpleFactory1 {
}

class PizzaStore {

    public static void main(String[] args) {
        new OrderPizza();
    }
}

class OrderPizza {

    public OrderPizza() {
        Pizza pizza = null;
        String orderType;
        for (; ; ) {
            orderType = DesignPatterUtil.getType();
            if (orderType.equals("greek")) {
                pizza = new GreekPizza();
            } else if (orderType.equals("cheese")) {
                pizza = new CheesePizza();
            } else {
                break;
            }
            // 输出pizza制作流程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }
    }
}

abstract class Pizza {

    protected String name;

    public abstract void prepare();

    public void bake() {
        System.out.println(name + " baking;");
    }

    public void cut() {
        System.out.println(name + " cutting;");
    }

    public void box() {
        System.out.println(name + " boxing;");
    }

    public void setName(String name) {
        this.name = name;
    }
}

class CheesePizza extends Pizza {

    public CheesePizza() {
        super.setName("奶酪Pizza");
    }

    @Override
    public void prepare() {
        System.out.println("给奶酪Pizza 准备原材料");
    }
}

class GreekPizza extends Pizza {

    public GreekPizza() {
        super.setName("希腊Pizza");
    }

    @Override
    public void prepare() {
        System.out.println("给希腊Pizza 准备原材料");
    }
}