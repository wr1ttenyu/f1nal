package wr1ttenyu.f1nal.study.designpattern.pattern23.responsibilitychain;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter;

/**
 * spring mvc 的请求拦截器链对象
 * {@link HandlerExecutionChain}
 * Handler execution chain, consisting of handler object and any handler interceptors.
 *
 * spring mvc 的请求拦截器链执行过程
 * 1. 请求入口
 * {@link DispatcherServlet#doDispatch(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
 *
 * 2. 获取请求对应的拦截器链
 * {@link DispatcherServlet#getHandler(javax.servlet.http.HttpServletRequest)}
 * 拦截器链中 包含请求匹配的 {@link HandlerInterceptor} 链 和 {@link HandlerMethod} 我们自定义Controller的处理请求的方法对象
 *
 * 3. 执行所有拦截器的 preHandle 方法
 *      {@link HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
 *          javax.servlet.http.HttpServletResponse, java.lang.Object)}
 *      {@link HandlerExecutionChain#applyPreHandle(javax.servlet.http.HttpServletRequest,
 *          javax.servlet.http.HttpServletResponse)}
 *
 * 4. 执行匹配到的自定义 Controller 方法
 * {@link AbstractHandlerMethodAdapter#handle(javax.servlet.http.HttpServletRequest,
 *          javax.servlet.http.HttpServletResponse, java.lang.Object)}
 *
 * 5. 执行所有拦截器的 postHandle 方法
 *      {@link HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
 *          javax.servlet.http.HttpServletResponse, java.lang.Object,
 *          org.springframework.web.servlet.ModelAndView)}
 *      {@link HandlerExecutionChain#applyPostHandle(javax.servlet.http.HttpServletRequest,
 *          javax.servlet.http.HttpServletResponse, org.springframework.web.servlet.ModelAndView)}
 *
 * 6. 执行所有拦截器的 afterCompletion 方法
 *      {@link HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
 *          javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)}
 *      {@link DispatcherServlet#processDispatchResult(javax.servlet.http.HttpServletRequest,
 *          javax.servlet.http.HttpServletResponse, org.springframework.web.servlet.HandlerExecutionChain,
 *          org.springframework.web.servlet.ModelAndView, java.lang.Exception)}
 *
 * 对源码的总结：
 * 1. {@link HandlerExecutionChain} 中维护了 {@link HandlerInterceptor} 的集合，可以向其中注册拦截器
 * 2. {@link HandlerExecutionChain} 主要负责的是请求拦截器的执行和请求处理，但是他本身并不处理请求，而是将请求分配给
 *      链上注册的处理器执行，这是职责链实现方式，减少职责链本身与处理逻辑之间的耦合，规范处理流程
 */
public class SpringMvcResponsibilityChainApply {

    public static void main(String[] args) {

    }
}
