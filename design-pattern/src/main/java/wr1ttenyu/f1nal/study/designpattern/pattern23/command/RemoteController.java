package wr1ttenyu.f1nal.study.designpattern.pattern23.command;

public class RemoteController {

    private Command[] onCommand;
    private Command[] offCommand;

    // 需要执行撤销的命令
    private Command undoCommand;

    public RemoteController() {
        onCommand = new Command[5];
        offCommand = new Command[5];

        for (int i = 0; i < 5; i++) {
            onCommand[i] = new NoCommand();
            offCommand[i] = new NoCommand();
        }
    }

    // 自定义按钮命令
    public void setCommand(int num, Command onCommand, Command offCommand) {
        this.onCommand[num] = onCommand;
        this.offCommand[num] = offCommand;
    }

    // 执行打开操作
    public void onButtonWasPushed(int num) {
        onCommand[num].execute();
        // 记录这一次操作 用于撤销
        undoCommand = onCommand[num];
    }

    // 执行关闭操作
    public void offButtonWasPushed(int num) {
        offCommand[num].execute();
        undoCommand = offCommand[num];
    }

    // 执行撤销操作
    public void undoButtonWasPushed() {
        undoCommand.undo();
    }
}
