package wr1ttenyu.f1nal.study.designpattern.pattern23.visitor;

public class Fail extends Action {
    
    @Override
    public void getManResult(Man man) {
        System.out.println(" 男人说：你这个歌手很失败！");
    }

    @Override
    public void getManResult(Woman woman) {
        System.out.println(" 女人说：你这个歌手很失败！");
    }
}
