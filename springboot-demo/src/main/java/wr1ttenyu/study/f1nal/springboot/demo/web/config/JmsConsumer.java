package wr1ttenyu.study.f1nal.springboot.demo.web.config;

import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.transport.tcp.TcpTransport;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    //接收queue类型消息
    //destination对应配置类中ActiveMQQueue("wr1ttenyu.queue")设置的名字
    /**
     * activemq 工作线程 在{@link TcpTransport#run()} 中循环执行 {@link TcpTransport#doRun()} 方法
     *      并阻塞在 {@link OpenWireFormat#unmarshal(java.io.DataInput)} 方法的 int size = dis.readInt(); 代码处
     *      等待 activemq 服务器发来消息
     * */
    @JmsListener(destination = "wr1ttenyu.queue")
    public void ListenQueue(String msg) {
        System.out.println("接收到queue消息：" + msg);
    }

    //接收topic类型消息
    //destination对应配置类中ActiveMQTopic("wr1ttenyu.topic")设置的名字
    //containerFactory对应配置类中注册JmsListenerContainerFactory的bean名称
    @JmsListener(destination = "wr1ttenyu.topic", containerFactory = "jmsTopicListenerContainerFactory")
    public void ListenTopic(String msg) {
        System.out.println("接收到topic消息：" + msg);
    }
}
