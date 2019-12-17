package wr1ttenyu.f1nal.study.designpattern.pattern23.command;

public class LightOnCommand implements Command {

    private LightReceiver lightReceiver;

    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        lightReceiver.on();
    }

    @Override
    public void undo() {
        System.out.println("撤销电灯打开命令");
    }
}
