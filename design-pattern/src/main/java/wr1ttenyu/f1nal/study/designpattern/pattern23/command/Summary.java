package wr1ttenyu.f1nal.study.designpattern.pattern23.command;

/**
 * 命令模式基本介绍：
 * 1. 命令模式：在软件设计中，我们经常需要向某些对象发送请求，但是并不知道请求的
 *      接受者是谁，也不知道被请求的操作是哪个，而我们只需在程序运行时通过向命令接收者
 *      发送命令即可，此时可以使用命令模式来进行设计
 *
 * 2. 命令模式使得请求发送者与请求接收者消除彼此之间的耦合，让对象之间的调用关系更加灵活，实现解耦
 *
 * 3. 在命令模式中，会将一个请求封装为一个对象，以便使用不同参数来表示不同的请求（即命名），
 *      同时命令模式也支持可撤销操作
 *
 * 命令模式的注意事项和细节：
 * 1. 将发起请求的对象与执行请求的对象解耦。发起请求的对象是调用者，调用者只要调用命令对象的execute()方法
 *      就可以让接收者工作，而不必知道具体的接收者对象是谁、是如何实现的，命令对象会负责让接收者执行请求
 *      的动作，也就是说：“请求发起者” 和 “请求执行者” 之间的解耦是通过命令对象实现的，命令对象起到了
 *      纽带桥梁的作用。
 * 2. 容易设计一个命令队列。只要把命令对象放到队列，就可以多线程的执行命令。因为命令模式中的命令都实现了 Command 接口，
 *      那么在线程中只要调用 Command 接口统一的方法就行
 * 3. 容易实现对请求的撤销和重做
 * 4. 命令模式的不足：可能导致某些系统有过多的具体命令类，增加了系统的复杂度，这点在使用的时候要注意
 * 5. 空命令也是一种设计模式，他为我们省去了判空的操作。
 * 6. 命令模式经典的应用场景： 界面的一个按钮都是一条命令、 模拟 CMD（DOS 命令） 订单的撤销/恢复、 触发-
 *      反馈机制
 *
 * 命令模式与门面模式的区别（我自己的理解）：
 *   门面模式：是对一系列操作的组合，比如洗澡需要加热水 开浴霸等等，我们提供一个接口，
 *      里面直接把洗澡的准备工作做完了，这样做的好处不仅是让调用方更加方便，也对操作进行了一个分组分层，
 *      注意是对一系列操作进行了一个分组分层，比如准备洗澡  开始洗澡  结束洗澡，是采用门面模式的设计的
 *      三个接口，它把一系列接口进行了分组 分层
 *   命令模式：命令模式则是只用一个接口，实现不同的功能，不同的实现者去实现这个接口，调用方通过指令或者标志位，又或者是直接指定命令对象
 *      让命令对象去执行具体的操作，调用具体的实现，也就是说命令对象的调用命令接收者的形式是统一的，因为不同的实现者
 *      都去实现了命令接口
 *      ###在命令模式中，命令的实现者，都需要实现 Command 接口，这是跟门面模式最大的区别###
 */
public class Summary {
}