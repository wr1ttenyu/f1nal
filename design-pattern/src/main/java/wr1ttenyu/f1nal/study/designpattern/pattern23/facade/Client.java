package wr1ttenyu.f1nal.study.designpattern.pattern23.facade;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;

/**
 * 需求：组建一个家庭影院：
 * DVD 播放器、 投影仪、 自动屏幕、 环绕立体声、 爆米花机,要求完成使用家庭影院的功能， 其过程为：
 * 直接用遥控器： 统筹各设备开关
 * 开爆米花机 放下屏幕 开投影仪 开音响 开 DVD， 选 dvd 去拿爆米花 调暗灯光 播放
 * 观影结束后， 关闭各种设备
 *
 * 传统方式解决影院管理问题分析
 * 1) 在 ClientTest 的 main 方法中， 创建各个子系统的对象， 并直接去调用子系统(对象)相关方法， 会造成调用过程
 * 混乱， 没有清晰的过程
 * 2) 不利于在 ClientTest 中， 去维护对子系统的操作
 * 3) 解决思路： 定义一个高层接口， 给子系统中的一组接口提供一个一致的界面(比如在高层接口提供四个方法
 * ready, play, pause, end )， 用来访问子系统中的一群接口
 * 4) 也就是说 就是通过定义一个一致的接口(界面类)， 用以屏蔽内部子系统的细节， 使得调用端只需跟这个接口发
 * 生调用， 而无需关心这个子系统的内部细节 => 外观模式
 *
 * 传统方式解决影院管理说明
 * 1) 外观模式可以理解为转换一群接口， 客户只要调用一个接口， 而不用调用多个接口才能达到目的。 比如： 在 pc
 * 上安装软件的时候经常有一键安装选项（省去选择安装目录、 安装的组件等等） ， 还有就是手机的重启功能（把
 * 关机和启动合为一个操作） 。
 * 2) 外观模式就是解决多个复杂接口带来的使用困难， 起到简化用户操作的作用
 *
 * 外观模式的注意事项和细节
 * 1. 外观模式屏蔽了子系统的细节，因此外观模式降低了客户端对子系统使用的复杂性
 * 2. 外观模式对客户端与子系统的耦合关系 - 解耦且更清晰，让子系统内部的模块更易维护和扩展
 * 3. 通过合理的使用外观模式，可以帮我们更好的划分访问的层次
 * 4. 当系统需要进行分层设计时，可以考虑使用 Facade 模式
 * 5. 在维护一个遗留的大型系统时，可能这个系统已经变得非常难以维护和扩展，此时可以
 *      考虑为新系统开发一个 Facade 类，来提供遗留系统的比较清晰简单的接口，
 *      让新系统与 Facade 类交互，提高复用性
 * 6. 不能过多的或者不合理的使用外观模式，使用外观模式好，还是直接调用模块好，取决于
 *      那种方式让系统有层次，更利于维护。
 *
 * 外观模式在 Mybatis 中的应用：MybatisFacadeApply.puml
 * {@link Configuration} 类中组合了
 *      {@link DefaultReflectorFactory} {@link DefaultObjectFactory} {@link DefaultObjectWrapperFactory}
 * {@link Configuration#newMetaObject(java.lang.Object)} 方法即是创建 MetaObject 的门面
 *      调用者只需要传入参数，具体如何创建对调用者是透明的
 */
public class Client {

    public static void main(String[] args) {
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.play();
        homeTheaterFacade.pause();
        homeTheaterFacade.end();
    }
}
