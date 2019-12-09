package wr1ttenyu.f1nal.study.designpattern.pattern23.composite;

/**
 * 需求
 * 编写程序展示一个学校院系结构，要在一个页面中展示出学校的院系组成，一个学校有多个学院，一个学院有多个系
 *
 */
public class Client {

    public static void main(String[] args) {
        University university = new University("安徽工业大学", "宇宙中心大学");

        College math = new College("数理学院", "里面有一个猛男,wr1ttenyu");
        university.add(math);
        Department mathApply = new Department("数学与应用数学", "老比比专业");
        Department msgApply = new Department("信息与计算科学", "老师垃圾一米");
        math.add(mathApply);
        math.add(msgApply);

        College language = new College("外国语学院", "里面美女全是wr1ttenyu的");
        university.add(language);
        Department english = new Department("英语专业", "全是妹子");
        Department espresso = new Department("意大利语专业", "学习意大利语的");
        language.add(english);
        language.add(espresso);

        university.print();

        language.print();
    }

}
