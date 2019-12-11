package wr1ttenyu.f1nal.study.designpattern.pattern23.flyweight;

/**
 * 展示网站项目需求
 * 小型的外包项目， 给客户 A 做一个产品展示网站， 客户 A 的朋友感觉效果不错， 也希望做这样的产品展示网
 * 站， 但是要求都有些不同：
 * 1) 有客户要求以新闻的形式发布
 * 2) 有客户人要求以博客的形式发布
 * 3) 有客户希望以微信公众号的形式发布
 *
 * 传统解决方案：
 * 针对不同的用户，直接对项目进行复制，给每个网站租用一个空间，然后做针对性的修改
 * 传统解决方案分析：
 * 1) 需要的网站结构相似度很高， 而且都不是高访问量网站，如果分成多个虚拟空间来处理，相当于一个相同网站
 * 的实例对象很多， 造成服务器的资源浪费
 * 2) 解决思路：整合到一个网站中，共享其相关的代码和数据，对于硬盘、内存、CPU、数据库空间等服务器资源
 * 都可以达成共享，减少服务器资源
 * 3) 对于代码来说，由于是一份实例，维护和扩展都更加容易
 * 4) 上面的解决思路就可以使用 享元模式 来解决
 */
public class Client {

    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();

        WebSite webSite1 = webSiteFactory.getWebSite("新闻");
        webSite1.use(new User("Tom"));

        WebSite webSite2 = webSiteFactory.getWebSite("博客");
        webSite2.use(new User("King"));

        WebSite webSite3 = webSiteFactory.getWebSite("博客");
        webSite3.use(new User("Jack"));

        System.out.println("网站的数量一共有：" + webSiteFactory.getWebSiteCount());
    }
}
