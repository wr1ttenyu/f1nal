package wr1ttenyu.f1nal.study.designpattern.pattern23.visitor;

public class Success extends Action {

    @Override
    public void getManResult(Man man) {
        System.out.println(" 男人说：你这个歌手很nice！");
    }

    @Override
    public void getManResult(Woman woman) {
        System.out.println(" 女人说：你这个歌手很nice！");
    }
}
