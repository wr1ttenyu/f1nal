package wr1ttenyu.f1nal.study.designpattern.pattern23.command;

public class LightOffCommand implements Command {

    private LightReceiver lightReceiver;

    public LightOffCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        lightReceiver.off();
    }

    @Override
    public void undo() {
        System.out.println("撤销电灯关闭命令");
    }
}
