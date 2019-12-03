package wr1ttenyu.f1nal.study.designpattern.pattern23.adapter;

import wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.classadapter.ClassAdapterClient;
import wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.interfaceadapter.FinalImplClass;
import wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.objectadapter.ObjectAdapterClient;

/**
 * 适配器模式
 *  三个角色：
 *      1. src(source) 被适配者（需要被适配的类、接口、对象）
 *      2. 适配器 adapter
 *      3. dst(destination) 目标（最后需要的输出，即 Target）
 * 基本介绍：
 *  1. 适配器模式（Adapter Pattern）将某个类的接口转换成客户端期望的另一个接口表示，
 *      主要目的是兼容性，让原本因为接口不匹配不能一起工作的两个类可以协同工作。其别名为包装器（Wapper)
 *  2. 适配器模式属于结构性模式
 *  3. 主要分为三类：类适配器模式 {@link ClassAdapterClient}
 *                对象适配器模式 {@link ObjectAdapterClient}
 *                接口适配器模式 {@link FinalImplClass}
 * 工作原理：
 *  1. 适配器模式：将一个类的接口转换成另一种接口，让原本接口不兼容的类可以兼容
 *  2. 从用户的角度看不到被适配者，是解耦的
 *  3. 用户调用适配器转化出来的目标接口方法，适配器再调用被适配者的相关接口方法
 *  4. 用户收到反馈结果，感觉只是和目标接口交互
 */
public class Summary {
}