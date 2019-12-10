package wr1ttenyu.f1nal.study.designpattern.pattern23.bridge;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * 需求：现在对不同手机类型(类型是统一的)的不同品牌实现操作编程(比如:开机、 关机、 上网， 打电话等)
 * 设计1：见phoneFunction.puml
 * 这种设计的问题：
 *  1).但是这样设计会出现类爆炸的问题，每种样式的手机下面都对应一种品牌手机的实现
 *      当我们扩展一种手机样式时，要实现所有的手机品牌
 *  2).违反了单一职责原则，增加手机的样式之后，还需要实现所有品牌手机的功能
 * 问题分析及问题解决方案：
 * 这里的问题主要是：在增加手机的样式时，手机的功能被重复实现了，手机的样式应该和手机的功能解耦
 *      也就是说，不应该在实现手机具体样式时重复实现手机功能，而是手机的具体样式需要去聚合手机功能的具体实现
 *      我们把手机的功能和手机的样式拆分成两个维度，手机的功能有各个品牌的实现，然后将两者进行聚合
 * 上面的解决方式：也就是桥接模式，将继承关系转化为关联关系，
 *      原本是各个品牌的功能实现是在继承了手机的样式的基础上去实现，现在改为手机的功能实现聚合到手机样式的抽象里面
 *
 * 桥接模式总结：
 *  1. 桥接模式是指：将具体行为的实现与抽象放在两个不同的类层次中，使两个层次可以独立改变（
 *          比如将手机的具体功能的抽象接口聚合在手机的样式抽象类中，而不同品牌手机功能实现则跟手机的样式实现没有关系）
 *  2. 桥接模式是一种结构型设计模式
 *  3. 桥接模式应用场景：桥接模式是基于类的最小设计原则，通过使用封装、聚合等行为，让不同的类承担不同的职责。
 *          它的主要特点是把抽象与行为实现分离开来，当其他类依赖该行为时，通过聚合该行为的抽象，
 *          这就保证了该行为实现扩展的独立性，避免通过继承的方式导致类爆炸。
 *          ***对于那些不希望使用继承或因为多层次继承导致系统类的个数急剧增加的系统， 桥接模式尤为适用.***
 *
 * 桥接模式在 JDBC 中的应用：
 *  {@link com.mysql.cj.jdbc.Driver} 实现了接口 {@link Driver}
 *
 *  {@link com.mysql.cj.jdbc.Driver} 注册到 {@link DriverManager} 中
 *
 *  实际上更像是一种策略模式，但也是将 {@link Driver} 的具体实现聚合到 {@link DriverManager} 中,
 *      让不同数据库的 {@link Driver} 的实现是独立的
 *
 *
 * 桥接模式的注意事项和细节：
 *   1. 将原本通过继承之后再实现某一行为的方式，变为，通过聚合的方式去注入这一行为的具体实现，来减少实现类的数量，
 *          有助于系统进行分层设计，从而产生更好的结构化系统
 *   2. 桥接模式替代多层继承方案，可以减少子类的个数，降低系统的管理和维护成本
 *   3. 桥接模式的引入增加了系统的理解和设计难度，由于聚合关联建立在抽象层，要求开发者针对抽象进行设计和编程
 *   4. 桥接模式要求正确识别出系统中两个独立变化的维度，将他们拆分出来，
 *          比如 消息的类型：即时消息和延时消息  消息的分类：手机短信、邮件消息、QQ消息
 */
public class Summary {
}