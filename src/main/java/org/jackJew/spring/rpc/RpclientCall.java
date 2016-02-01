package org.jackJew.spring.rpc;

import java.io.OutputStream;

import org.aopalliance.intercept.MethodInvocation;
import org.jackJew.biz.proxy.model.NewProxy;
import org.jackJew.biz.proxy.service.NewProxyRpcService;
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

/**
 * rpc client defined in resources/applicationContext-rpc.xml <br/>
 * <br/>
 * AOP proxy call based on JdkDynamicAopProxy, HttpInvokerProxyFactoryBean extends HttpInvokerClientInterceptor,
 * which is an org.aopalliance.intercept.MethodInterceptor, or an advice.
 * <br/>
 * HttpInvokerProxyFactoryBean -> invoke(MethodInvocation methodInvocation) -> <br/>
 * HttpComponentsHttpInvokerRequestExecutor doExecuteRequest() -> setRequestBody() <br/>
 * -> httpPost.setEntity(ByteArrayEntity, serialized bytes from ByteArrayOutputStream) <br/>
 * <br/>
 * 
 * AbstractHttpInvokerRequestExecutor.getByteArrayOutputStream(RemoteInvocation invocation)
 * -> <br/>
 * remoteInvocation = new RemoteInvocation(MethodInvocation); <br/>
 * new ObjectOutputStream(new ByteArrayOutputStream).writeObejct(remoteInvocation);
 * RemoteInvocation implements java.io.Serializable { <br/>
 * 		private String methodName;  <br/>
 * 		private Class[] parameterTypes;  <br/>
 * 		private Object[] arguments;  <br/>
 * }
 *<br/>
 *
 * finally accepts responseBody stream from HttpClient, and deserialized to RemoteInvocationResult.
 * <br/>
 * 
 * HttpInvokerServiceExporter: <br/>
 * HttpServletRequest -> getInputStream() -> deserialized to RemoteInvocation ->
 * jdk reflection call to proxy and AOP, write RemoteInvocationResult to HttpServletResponse#getOutputStream().
 * 
 * @author Jack
 *
 */
@Service
public class RpclientCall {

	@Autowired
	private NewProxyRpcService newProxyRpcService;
	
	/**
	 * NewProxy should be serializable, otherwise exception.
	 */
	public String call() {
		NewProxy newProxy = newProxyRpcService.getFromPool();
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
