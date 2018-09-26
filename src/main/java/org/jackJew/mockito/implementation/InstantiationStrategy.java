package org.jackJew.mockito.implementation;

import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class InstantiationStrategy {

  public final static Objenesis OBJENESIS = new ObjenesisStd();

  @SuppressWarnings("unchecked")
  public <T> T instantiate(Class<T> cls) {
    if (cls.isPrimitive() || cls.isAnonymousClass() || Modifier.isFinal(cls.getModifiers())) {
      throw new IllegalArgumentException("unable to mock on final, or anonymous, or primitive class !");
    }
    if (cls.isInterface()) {
      Object obj = Proxy.newProxyInstance(
          Thread.currentThread().getContextClassLoader(),
          new Class<?>[] {cls},
          new JdkDynamicProxy()
      );
      return (T) obj;
    } else {
      Class<?> newCls = new CglibClassSubclassing().createClass(cls);
      Factory factoryProxy = (Factory) OBJENESIS.getInstantiatorOf(newCls).newInstance();
      factoryProxy.setCallback(0, new Callback());
      return (T) factoryProxy;
    }
  }

  @SuppressWarnings("unchecked")
  public <T> T instantiate(Class<T> cls, Class<?> advisedInterface) {
    if (cls.isInterface()) {
      Object obj = Proxy.newProxyInstance(
          Thread.currentThread().getContextClassLoader(),
          new Class<?>[] {cls, advisedInterface},
          new JdkCallbackNoop()
      );
      return (T) obj;
    } else {
      return (T) null;
    }
  }

  public final Object invokeAndReturn(Method method, Object[] args) {
    MethodInvocation methodInvocation = new MethodInvocation(method, args);
    Mock.methodLocal.set(methodInvocation);

    Integer executionCount = Mock.methodExecutionMap.get(methodInvocation);
    if (executionCount == null) {
      Mock.methodExecutionMap.put(methodInvocation, 1);
    } else {
      Mock.methodExecutionMap.put(methodInvocation, ++executionCount);
    }
    return Mock.methodsMap.get(methodInvocation);
  }

  public class Callback implements MethodInterceptor {

    @Override
    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodproxy) {
      return invokeAndReturn(method, args);
    }
  }

  public class CglibCallbackNoop implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) {
      return null;
    }
  }

  public class JdkDynamicProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
      return invokeAndReturn(method, args);
    }
  }

  public class JdkCallbackNoop implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
      return null;
    }

  }
}
