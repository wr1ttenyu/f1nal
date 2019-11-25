package wr1ttenyu.f1nal.study.designpattern.principle;

/**
 * 设计原则核心思想
 * 1. 找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混合在一起
 * 2. 针对接口编程，而不是针对现实编程
 * 3. 为了交互对象之间的松耦合设计而努力
 */
interface Summary {
}

/**
 * uml 类图
 * 类之间的六种关系：
 * 依赖：只要是在类中用到了对方 没有对方 编译不过
 * 泛化：实际上是继承关系  是依赖关系的一种特例
 * 实现：实际上是实现类和其实现的接口之间的关系  是依赖关系的一种特例
 * 关联：类与类之间的具有方向性的关联关系  单项或者双向 是依赖关系的一种特例
 * 聚合：表示整体与部分的关系，整体与部分可以分开 聚合是关联关系的特例 具有导航性（谁聚合到哪里去）
 *     和多重性（单聚合与多重聚合） 单聚合是同一个类只聚合一个 多重聚合是同一个类聚合多个
 * 组合：表示整体与部分的关系，整体与部分不可以分开 同生同灭
 */

/**
 * 设计模式的本质 是提高软件的可维护性、通用性、扩展性、稳定性，并降低软件的复杂度
 *
 * 设计模式的三种类型：
 *
 * 创建型模式：单例模式、抽象工厂模式、工厂模式、原型模式、建造者模式
 *
 * 结构型模式：适配器模式、桥接模式、装饰模式、组合模式、外观模式、享元模式、代理模式
 *
 * 行为型模式：模板方法模式、命令模式、访问者模式、迭代器模式、观察者模式、中介者模式、
 *      备忘录模式、解释器模式、状态模式、策略模式、职责链模式
 *
 *
 */