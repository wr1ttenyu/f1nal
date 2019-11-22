package wr1ttenyu.f1nal.study.designpattern.principle.liskovsubstitution;

public class LiskovSubstitution2 {

    public static void main(String[] args) {
        A1 a = new A1();
        B1 b = new B1();
        System.out.println(a.func1(9,1));
        // 类B重写A的func1 可能是无意重写  但是这会导致 b调用func1的结果和a不同 导致错误
        // 这种重写虽然方便 但是会造成整个继承体系的可复用性变差 特别是运行多态比较频繁的时候
        // 这里A B使用聚合的关系
        // 这时如果还想调用 A 的方法 func1 就会通过 b 的 func3 方法 就不会出现误解
        System.out.println(b.func3(9,1));
        System.out.println(b.func2(9,1));
    }

}

class Base{

}

class A1 extends Base {
    public int func1(int i, int j) {
        return i - j;
    }
}

class B1 extends Base {
    // 这里A B使用z组合的关系
    private A1 a1 = new A1();

    public int func1(int i, int j) {
        return i + j;
    }

    public int func2(int i, int j) {
        return func1(i, j) + 9;
    }

    public int func3(int i, int j) {
        return a1.func1(i, j) + 9;
    }
}