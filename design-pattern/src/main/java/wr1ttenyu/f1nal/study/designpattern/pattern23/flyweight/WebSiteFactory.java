package wr1ttenyu.f1nal.study.designpattern.pattern23.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 网站工厂类
 */
public class WebSiteFactory {

    private Map<String, ConcreteWebSite> pool = new HashMap<>();

    // 根据类型获取网站，如果没有就创建一个，放入池中并返回
    public WebSite getWebSite(String type) {
        if(!pool.containsKey(type)) {
            pool.put(type, new ConcreteWebSite(type));
        }
        return pool.get(type);
    }

    public int getWebSiteCount() {
        return pool.size();
    }
}
