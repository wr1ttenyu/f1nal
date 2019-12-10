package wr1ttenyu.f1nal.study.designpattern.pattern23.composite;

import java.util.HashMap;
import java.util.Map;

/**
 * 组合模式基本介绍：
 *  1. 组合模式：又叫部分整体模式。它创建了对象组的树形结构，将对象组合成树状结构以表示“整体-部分”的层次关系
 *  2. 组合模式使用用户对单个对象和组合对象的访问具有一致性，即：组合能让客户以一致的方式处理个别对象以及组合对象
 *
 * 组合模式的注意事项和细节
 *  1. 简化客户端操作，客户端只需要面对一致的对象而不用考虑整体部分或者节点叶子的问题。
 *  2. 具有较强的扩展性。当我们要更改组合对象时，我们只需要调整内部的层次关系，客户端不用做出任何改动。
 *  3. 方便创建出复杂的层次结构。客户端不用理会组合里面的组成细节，
 *          容易添加节点或者叶子从而创建出复杂的树形结构。
 *  4. ##需要遍历组织机构，或者处理的对象具有树形结构时，非常适合使用组合模式。##
 *  5. ##要求较高的抽象性，如果节点和叶子有很多差异性的话，比如很多方法和属性都不一样，不适合使用组合模式##
 *  4、5 两点是 重点
 *
 * 组合模式在 jdk 中的应用：{@link HashMap#putAll(java.util.Map)} 参考类图 JdkCompositeApply.puml
 *  从 {@link HashMap#putAll(java.util.Map)} 方法来看：是将 HashMap 进行了组合，putAll的参数是作为子节点
 *  但是从使用者的角度来看，我不需要知道内部的数据组合方式，我还是通过正常的 {@link HashMap#get(java.lang.Object)}
 *  {@link HashMap#put(java.lang.Object, java.lang.Object)} 方法来操作数据
 *  {@link HashMap.Node} 作为子叶节点，不能继续添加子节点，只能操作里面存储的 key 和 value
 *
 */
public class Summary {

    public static void main(String[] args) {
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(0, "东游记");

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "西游记");
        map2.put(2, "红楼梦");

        map1.putAll(map2);
    }
}
