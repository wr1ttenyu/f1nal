package wr1ttenyu.f1nal.study.designpattern.pattern23.bridge;

/**
 * phoneFunction2.puml 这种实现方式的好处：
 *      1.对于不同样式的手机，我们实现其样式之后，对于不同的品牌，我们只要聚合到具体的手机样式实现类中就行了。
 *      2.防止了类爆炸，也遵循了单一职责原则
 */
public class Client {

    public static void main(String[] args) {
        FoldedPhone vivoFoldedPhone = new FoldedPhone();
        vivoFoldedPhone.setStyle("折叠手机");
        vivoFoldedPhone.setFunction(new VivoFunction());
        vivoFoldedPhone.open();
        vivoFoldedPhone.call();
        vivoFoldedPhone.close();

        FoldedPhone xiaomiFoldedPhone = new FoldedPhone();
        xiaomiFoldedPhone.setStyle("折叠手机");
        xiaomiFoldedPhone.setFunction(new XiaoMiFunction());
        xiaomiFoldedPhone.open();
        xiaomiFoldedPhone.call();
        xiaomiFoldedPhone.close();

        UpRightPhone xiaomiUpRightPhone = new UpRightPhone();
        xiaomiUpRightPhone.setStyle("翻盖手机");
        xiaomiUpRightPhone.setFunction(new XiaoMiFunction());
        xiaomiUpRightPhone.open();
        xiaomiUpRightPhone.call();
        xiaomiUpRightPhone.close();
    }
}
