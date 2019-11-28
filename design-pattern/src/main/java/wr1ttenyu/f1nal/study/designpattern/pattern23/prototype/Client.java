package wr1ttenyu.f1nal.study.designpattern.pattern23.prototype;

/**
 * 需求 ：克隆羊问题
 * 现在有一只羊 tom， 姓名为: tom, 年龄为： 1， 颜色为： 白色， 请编写程序创建和 tom 羊 属性完全相同的 10
 * 只羊。
 */
public class Client {

    public static void main(String[] args) {
        // 传统方式 分析：
        // 1.优点：好理解 易操作
        // 2.在创建对象时，总是需要重新获取原始对象的属性，如果创建的对象比较复杂时，效率较低
        // 3.总是需要重新初始化对象，而不是动态的获取对象运行时的状态，不够灵活（比如类增加一个属性 那么我们也需要修改代码来copy这个属性）
        // 改进的思路分析：
        //   Java 中 Object 类是所有类的根类， Object 类提供了一个 clone()方法，
        //   该方法可以将一个 Java 对象复制一份，但是需要实现 clone 的 Java 类
        //   必须要实现一个接口 Cloneable，该接口表示该类能够复制且具有复制的能力 => 原型模式
        Sheep1 sheep1 = new Sheep1("tom", 1, "白色");
        Sheep1 sheep2 = new Sheep1(sheep1.getName(), sheep1.getAge(), sheep1.getColor());
        Sheep1 sheep3 = new Sheep1(sheep1.getName(), sheep1.getAge(), sheep1.getColor());
        Sheep1 sheep4 = new Sheep1(sheep1.getName(), sheep1.getAge(), sheep1.getColor());

        // 改进后方法：
        Sheep2 sheep21 = new Sheep2("tom", 1, "白色");
        Sheep2 sheep22 = (Sheep2)sheep21.clone();
        System.out.println(sheep21);
        System.out.println(sheep22);

        // 浅拷贝
        System.out.println("--------------- 浅拷贝 ---------------");
        Sheep2 sheepWithObj = new Sheep2("tom", 1, "白色");
        Sheep2 friend = new Sheep2("jack", 1, "黑色");
        sheepWithObj.setFriend(friend);
        Sheep2 sheep23 = (Sheep2)sheepWithObj.clone();
        System.out.println(sheep23.getFriend() == friend);

        // 深拷贝
        System.out.println("--------------- 深拷贝 ---------------");
        Sheep3DeepClone deepClone = new Sheep3DeepClone("deep" , 1, "白色");
        deepClone.setFriend(new Sheep1("tom", 1, "白色"));
        System.out.println(deepClone);

        Sheep3DeepClone cloneSheep1 = (Sheep3DeepClone)deepClone.clone();
        System.out.println(cloneSheep1);
        System.out.println(deepClone.getFriend() == cloneSheep1.getFriend());

        Sheep3DeepClone cloneSheep2 = deepClone.deepCloneBySerialization();
        System.out.println(cloneSheep2);
        System.out.println(deepClone.getFriend() == cloneSheep2.getFriend());



    }

    /**
     * {@link DemoApplication#getSheep()} 这是原型模式在spring中的应用
     */
}
