package wr1ttenyu.f1nal.study.designpattern.principle.liskovsubstitution;

public class LiskovSubstitution1 {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        System.out.println(a.func1(9,1));
        // 类B重写A的func1 可能是无意重写  但是这会导致 b调用func1的结果和a不同 导致错误
        // 这种重写虽然方便 但是会造成整个继承体系的可复用性变差 特别是运行多态比较频繁的时候
        System.out.println(b.func1(9,1));
        System.out.println(b.func2(9,1));
    }

}

class A {
    public int func1(int i, int j) {
        return i - j;
    }
}

class B extends A {
    @Override
    public int func1(int i, int j) {
        return i + j;
    }

    public int func2(int i, int j) {
        return func1(i, j) + 9;
    }
}