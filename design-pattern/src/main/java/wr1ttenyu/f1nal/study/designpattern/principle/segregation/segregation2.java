package wr1ttenyu.f1nal.study.designpattern.principle.segregation;

public class segregation2 {

    public static void main(String[] args) {
        A1 a = new A1();
        B1 b = new B1();
        a.depend1(b);
        a.depend2(b);
        a.depend3(b);

        C1 c = new C1();
        D1 d = new D1();
        c.depend1(d);
        c.depend4(d);
        c.depend5(d);
    }

}

class A1 {

    public void depend1(Interface2 inter) {
        inter.operation1();
    }

    public void depend2(Interface3 inter) {
        inter.operation2();
    }

    public void depend3(Interface3 inter) {
        inter.operation3();
    }

}

class B1 implements Interface2, Interface3 {

    @Override
    public void operation1() {
        System.out.println("B1 implements Interface1 operation1");
    }

    @Override
    public void operation2() {
        System.out.println("B1 implements Interface1 operation2");
    }

    @Override
    public void operation3() {
        System.out.println("B1 implements Interface1 operation3");
    }

}

class C1 {

    public void depend1(Interface2 inter) {
        inter.operation1();
    }

    public void depend4(Interface4 inter) {
        inter.operation4();
    }

    public void depend5(Interface4 inter) {
        inter.operation5();
    }

}

class D1 implements Interface2, Interface4{

    @Override
    public void operation1() {
        System.out.println("D1 implements Interface1 operation1");
    }

    @Override
    public void operation4() {
        System.out.println("D1 implements Interface1 operation4");
    }

    @Override
    public void operation5() {
        System.out.println("D1 implements Interface1 operation5");
    }

}

interface Interface2 {
    void operation1();
}

interface Interface3 {

    void operation2();

    void operation3();
}

interface Interface4 {

    void operation4();

    void operation5();
}