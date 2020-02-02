package wr1ttenyu.f1nal.study.designpattern.pattern23.visitor;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;

/**
 * 访问者模式基本介绍：
 *  1. 访问者模式（Visitor Pattern),封装一些作用于某种数据结构的各元素的操作，它可以再不改变数据
 *      结构的前提下定义作用于这些元素的新的操作
 *  2. 主要将数据结构与数据操作分离，解决 数据结构和操作耦合性问题
 *  3. 访问者模式的基本工作原理是：在被访问的类里面加一个对外接待访问者的接口
 *  4. 访问者模式主要应用场景是：需要对一个对象结构中的对象进行很多不同操作（这些操作彼此没有关联）,
 *      同时需要避免让这些操作“污染”这些对象的类，可以选用访问者模式解决
 *
 * 访问者模式中的 双分派机制：所谓双分派是指不管类怎么变化，我们都能找到期望的方法运行。
 *      双分派意味着得到执行的操作取决于请求的种类和两个接收者的类型。
 *      对应Singer.puml来看，请求的类型就是 Action 的种类，可能有多种 Action
 *      两个接收者是 Action 的实现类和 Person 的实现类
 *
 * 访问者模式的注意事项和细节
 * 优点
 * 1. 访问者模式符合单一职责原则、让程序具有优秀的扩展性、灵活性非常高
 * 2. 访问者模式可以对功能进行统一，可以做报表、UI、拦截器与过滤器，适用于数据结构相对稳定的系统
 * 缺点
 * 1. 具体元素对访问者公布细节，也就是说访问者关注了其他类的内部细节，这是迪米特法则所不建议的，
 *      这样造成了具体元素变更比较困难
 * 2. 违背了依赖倒转原则。访问者依赖的是具体元素，而不是抽象元素
 * 3. 因此，如果一个系统有比较稳定的数据结构，又有经常变化的功能需求，那么访问者模式就比较适用
 *
 */
public class Summary {

    /**
     * SOLVE 访问者模式的应用实例 https://blog.csdn.net/qq_37909508/article/details/94338760
     * 将 {@link BeanDefinition} 传入 {@link BeanDefinitionVisitor} 中，
     * 通过 {@link BeanDefinitionVisitor} 来获取 {@link BeanDefinition} 中的各种属性
     *
     * {@link BeanDefinition} 相当于 是 ObjectStructure 角色
     * {@link BeanDefinitionVisitor} 相当于 Action 角色
     */
    public static void main(String[] args) {

    }
}
