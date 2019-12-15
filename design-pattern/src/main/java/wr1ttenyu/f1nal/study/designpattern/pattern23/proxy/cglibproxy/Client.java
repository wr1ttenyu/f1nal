package wr1ttenyu.f1nal.study.designpattern.pattern23.proxy.cglibproxy;

/**
 * Cglib 代理模式基本介绍：
 *      1. 静态代理和 JDK 代理模式都要求目标对象是实现一个接口，但是有时候目标对象只是一个单独的对象，并没有
 *          实现任何的接口，这个时候就可以使用目标对象的子类来实现代理 --- Cglib 代理
 *      2. Cglib 代理也叫做子类代理，它是在内存中构建一个子类对象从而实现对目标对象功能扩展
 *      3. Cglib 是一个强大的高性能的代码生成包，他可以在运行期扩展java类与实现java接口，它广泛的被许多 AOP
 *          的框架使用，例如 Spring AOP，实现方法拦截
 *      4. 在 AOP 编程中如何选择代理模式：
 *          a.目标对象需要实现接口，使用JDK代理
 *          b.目标对象不需要实现接口，使用cglib代理
 *          在jdk1.8之前，cglib代理要比jdk代理执行效率更好，但在1.8及之后dk代理执行效率更好于cglib代理
 *      5. Cglib包的底层是通过使用字节码处理框架 ASM 来转换字节码并生成新的类
 */
public class Client {

    public static void main(String[] args) {
        TeacherDao target = new TeacherDao();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        TeacherDao proxyInstance = (TeacherDao)proxyFactory.getProxyInstance();
        proxyInstance.teach();
    }
}
