package wr1ttenyu.f1nal.study.designpattern.pattern23.flyweight;

public class ConcreteWebSite extends WebSite {

    // 网站的发布类型
    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站的发布形式为：" + type + "用户" + user.getName() + "已经在使用中....");
    }
}
