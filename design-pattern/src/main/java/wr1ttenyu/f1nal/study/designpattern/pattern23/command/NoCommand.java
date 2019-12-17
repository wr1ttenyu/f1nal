package wr1ttenyu.f1nal.study.designpattern.pattern23.command;

/**
 * 设置一个空操作
 *      假设要给遥控器的按钮赋值操作命令，在给没有具体命令的按钮初始化时，可以直接赋值空操作
 *      这样做的好处是，不需要去判断这个按钮的命令是否为空，直接执行它的空命令就好，省去了判空的步骤
 *      这也是一种设计模式
 */
public class NoCommand implements Command {

    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }
}
