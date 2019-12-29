package wr1ttenyu.f1nal.study.designpattern.pattern23.state;

/**
 * 状态模式的基本介绍：
 * 1. 状态模式（State Pattern): 它主要用来解决对象在多种状态转换时，需要对外输出不同的行为的问题。
 *          状态和行为是一一对应的，状态之间可以互相装换
 * 2. 当一个对象的内在状态改变时，允许改变其行为，这个对象看起来像是改变了其类
 *
 * 状态模式的注意事项和细节
 * 1. 代码有很强的可读性。状态模式将每个状态的行为封装到对应的一个类中
 * 2. 方便维护。将容易产生问题的 if-else 语句删除了，如果把所有状态的行为都放到一个类中，
 *          每次调用方法是都要判断当前是什么状态，不但会产出很多 if-else 语句，而且容易出错
 * 3. 符合“开闭原则”。容易增删状态。
 * 4. 会产生很多类。每个状态都要一个对应的类，当状态过多时会产生很多类，加大维护难度。
 * 5. 应用场景：
 *      当一个事件或者对象有很多种状态，且状态之间会互相切换，对不同的状态要求有不同的行为的时候，
 *      可以考虑使用状态模式
 *
 */
public class Summary {
}
