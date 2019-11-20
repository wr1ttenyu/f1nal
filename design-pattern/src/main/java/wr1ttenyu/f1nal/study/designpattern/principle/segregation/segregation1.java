package wr1ttenyu.f1nal.study.designpattern.principle.segregation;

public class segregation1 {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.depend1(b);
        a.depend2(b);
        a.depend3(b);

        C c = new C();
        D d = new D();
        c.depend1(d);
        c.depend4(d);
        c.depend5(d);
    }

}

class A {

    public void depend1(Interface1 inter) {
        inter.operation1();
    }

    public void depend2(Interface1 inter) {
        inter.operation2();
    }

    public void depend3(Interface1 inter) {
        inter.operation3();
    }

}

class B implements Interface1 {

    @Override
    public void operation1() {
        System.out.println("B implements Interface1 operation1");
    }

    @Override
    public void operation2() {
        System.out.println("B implements Interface1 operation2");
    }

    @Override
    public void operation3() {
        System.out.println("B implements Interface1 operation3");
    }

    @Override
    public void operation4() {
        System.out.println("B implements Interface1 operation4");
    }

    @Override
    public void operation5() {
        System.out.println("B implements Interface1 operation5");
    }
}

class C {

    public void depend1(Interface1 inter) {
        inter.operation1();
    }

    public void depend4(Interface1 inter) {
        inter.operation4();
    }

    public void depend5(Interface1 inter) {
        inter.operation5();
    }

}

class D implements Interface1{

    @Override
    public void operation1() {
        System.out.println("D implements Interface1 operation1");
    }

    @Override
    public void operation2() {
        System.out.println("D implements Interface1 operation2");
    }

    @Override
    public void operation3() {
        System.out.println("D implements Interface1 operation3");
    }

    @Override
    public void operation4() {
        System.out.println("D implements Interface1 operation4");
    }

    @Override
    public void operation5() {
        System.out.println("C implements Interface1 operation5");
    }

}

interface Interface1 {

    void operation1();

    void operation2();

    void operation3();

    void operation4();

    void operation5();

}