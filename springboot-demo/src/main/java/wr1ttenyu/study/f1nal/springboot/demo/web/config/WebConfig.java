package wr1ttenyu.study.f1nal.springboot.demo.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        HashMap<Integer, ListNode> nodeMapA = new HashMap();
        while(nodeA != null) {
            nodeMapA.put(nodeA.val, nodeA);
            nodeA = nodeA.next;
        }
        while(headB != null) {
            if(nodeMapA.containsKey(nodeB.val)) {
                if(headB == nodeMapA.get(nodeB.val)) return headB;
            }
            headB = headB.next;
        }

        return null;
    }

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/demo/**");
    }

    class MyHandlerInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            LOGGER.info("拦截器 preHandle 方法执行 返回 true");
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            LOGGER.info("拦截器 postHandle 方法执行");
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            LOGGER.info("拦截器 afterCompletion 方法执行");
        }
    }*/
}

class ListNode {

    public int val;

    public ListNode next;

}