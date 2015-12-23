package org.jackJew.spring.rpc;

import java.io.OutputStream;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vipshop.crawler.proxy.model.NewProxy;
import com.vipshop.crawler.proxy.service.NewProxyRpc;

/**
 * rpc client defined in resources/applicationContext-rpc.xml <br/>
 * <br/>
 * HttpInvokerProxyFactoryBean -> invoke(MethodInvocation methodInvocation) -> <br/>
 * HttpComponentsHttpInvokerRequestExecutor doExecuteRequest() -> setRequestBody() <br/>
 * -> httpPost.setEntity(serialized bytes from ByteArrayOutputStream) <br/>
 * <br/>
 * 
 * AbstractHttpInvokerRequestExecutor.getByteArrayOutputStream(RemoteInvocation invocation)
 * -> <br/>
 * writeRemoteInvocation(RemoteInvocation invocation, OutputStream os) <br/>
 * RemoteInvocation implements java.io.Serializable { <br/>
 * 		private String methodName;  <br/>
 * 		private Class[] parameterTypes;  <br/>
 * 		private Object[] arguments;  <br/>
 * }
 *<br/>
 *
 * process response: <br/>
 * readRemoteInvocationResult() -> ObjectOutputStream.readObject()
 * @author Jack
 *
 */
@Service
public class RpclientCall {

	@Autowired
	private NewProxyRpc newProxyRpc;
	
	/**
	 * NewProxy should be serializable, otherwise exception.
	 */
	public String call() {
		NewProxy newProxy = newProxyRpc.getOneFromPool();
		if (newProxy != null) {
			return newProxy.toString();
		} else {
			return "nothing";
		}
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:applicationContext*.xml");

		RpclientCall call = context.getBean(RpclientCall.class);
		// cglibAopProxy expected.
		System.out.println(call.call());

		((AbstractApplicationContext) context).close();
	}

}
