package org.jackJew.mockito.implementation;

import org.jackJew.mockito.implementation.Mock.AnyAny;

import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.Set;

public class MethodInvocation {

  private Method method;
  private Object[] arguments;

  MethodInvocation(Method method, Object[] arguments) {
    this.method = method;
    this.arguments = arguments;
    if (arguments != null && arguments.length > 0) {
      Set<Entry<Class<?>, AnyAny>> set = Mock.anyObjectMap.entrySet();
      for (int i = 0; i < arguments.length; i++) {
        for (Entry<Class<?>, AnyAny> entry : set) {
          if (method.getParameterTypes()[i].isAssignableFrom(entry.getKey())) {
            if (entry.getValue() != null && (entry.getValue().getAnyObject() == arguments[i]
                || entry.getValue().getAnyObject() == null)) {
              entry.getValue().setUsed(true);
            }
          }
        }
      }
    }
  }

  public boolean equals(Object obj) {
    if (obj instanceof MethodInvocation) {
      MethodInvocation methodInvocation = (MethodInvocation) obj;
      if (!method.equals(methodInvocation.method)) {
        return false;
      }
      if (arguments == null && methodInvocation.arguments == null) {
        return true;
      } else if (arguments == null) {
        return false;
      } else if (methodInvocation.arguments == null) {
        return false;
      } else {
        int size0 = arguments.length, size1 = methodInvocation.arguments.length;
        if (size0 != size1) {
          return false;
        } else {
          Set<Entry<Class<?>, AnyAny>> set = Mock.anyObjectMap.entrySet();
          int i = 0;
          boolean allEqual = true;
          while (i < size0) {
            if (arguments[i] == methodInvocation.arguments[i]
                || (arguments[i] == null && methodInvocation.arguments[i] instanceof AdvisedMarker)
                || (arguments[i] != null && (
                arguments[i].equals(methodInvocation.arguments[i])
                    || methodInvocation.arguments[i] instanceof AdvisedMarker))
                || (arguments[i] instanceof AdvisedMarker
                && methodInvocation.arguments[i] instanceof AdvisedMarker)
            ) {
              i++;
              // do nothing
            } else {
              if (arguments[i] != null) {
                boolean matched = false;
                for (Entry<Class<?>, AnyAny> entry : set) {
                  if (method.getParameterTypes()[i].isAssignableFrom(entry.getKey())) {
                    if (entry.getValue() != null
                        && entry.getValue().getAnyObject() == methodInvocation.arguments[i]) {
                      matched = true;
                      break;
                    }
                  }
                }
                if (matched) {
                  i++;
                  continue;
                }
              }
              allEqual = false;
              break;
            }
          }
          return allEqual;
        }
      }
    }
    return false;
  }

  public int hashCode() {
    return method.hashCode();
  }

}
