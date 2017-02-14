package org.jackJew.reflection.testDtoPackage;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.util.ReflectionUtils;

import com.google.common.base.Defaults;
import com.google.common.collect.Lists;

public class DtoPackageTest {
	
	private final Objenesis objenesis = new ObjenesisStd();

	@Test
	public void testDtoMethods() {
		String usePackage = "org/jackJew/test/dto/**/*";
		String baseClassPackage = DtoPackageTest.class.getPackage().getName();

		ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();

		List<Class<?>> classes = Lists.newArrayList();

		try {
			Resource[] resources = rpr.getResources(usePackage);
			for (Resource resource : resources) {
				if (resource.getFile().isFile()) {
					String shortClassName = resource.getFilename().replace(".class", "");

					String fullClassName = baseClassPackage + "." + shortClassName;
					try {
						Class<?> cls = Class.forName(fullClassName);
						if (!cls.isAnnotation() && !cls.isInterface() && !Modifier.isAbstract(cls.getModifiers())
								&& !cls.isEnum()) {
							classes.add(cls);
						}
					} catch (Exception e) {
						System.out.println(fullClassName + " class does not exist");
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Class<?> cls : classes) {
			System.out.println(cls.getName());
			Object obj = null;
			try {
				Constructor<?> ctr = null;
				if (cls.isMemberClass()) {
					ctr = cls.getDeclaredConstructor(new Class<?>[] { cls.getEnclosingClass() });
					ReflectionUtils.makeAccessible(ctr);
					obj = ctr.newInstance(new Object[] { null });
				} else {
					ctr = cls.getDeclaredConstructor(new Class<?>[0]);
					ReflectionUtils.makeAccessible(ctr);
					obj = ctr.newInstance(null);
				}

			} catch (Exception e) {
				System.out.println(cls.getName() + " has no default constructor");
				try {
					Constructor<?>[] ctrs = cls.getDeclaredConstructors();
					Constructor<?> ctr = ctrs[0];
					int i = 0;
					while (++i < ctrs.length) {
						if (ctr.getParameterTypes().length < ctrs[i].getParameterTypes().length) {
							ctr = ctrs[i];
						}
					}
					Object[] args = new Object[ctr.getParameterTypes().length];
					for (i = 0; i < args.length; i++) {
						Class<?> parameterType = ctr.getParameterTypes()[i];
						if (parameterType.isPrimitive() || parameterType.isAnonymousClass()
								|| Modifier.isFinal(parameterType.getModifiers())) {
							args[i++] = Defaults.defaultValue(parameterType);
						} else {
							if (parameterType.isMemberClass()) {
								args[i++] = parameterType
										.getDeclaredConstructor(new Class<?>[] { parameterType.getEnclosingClass() })
										.newInstance(new Object[] { null });
							} else {
								args[i++] = parameterType.getDeclaredConstructor(new Class<?>[0]).newInstance(null);
							}
						}
					}
					obj = ctr.newInstance(args);
				} catch (Exception e2) {
					System.out.println(cls.getName() + " invoke constructor fail");
				}
			}
			if (obj == null) {
				continue;
			}
			Method[] methods = cls.getDeclaredMethods();
			for (Method method : methods) {
				if (!method.getName().startsWith("get") && !method.getName().endsWith("set")) {
					continue;
				}
				ReflectionUtils.makeAccessible(method);
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes == null || parameterTypes.length == 0) {
					try {
						method.invoke(obj, null);
					} catch (Exception e) {
					}
				} else {
					Object[] args = new Object[parameterTypes.length];
					int i = 0;
					for (Class<?> parameterType : parameterTypes) {
						if (parameterType.isPrimitive() || parameterType.isAnonymousClass()
								|| Modifier.isFinal(parameterType.getModifiers())) {
							args[i++] = Defaults.defaultValue(parameterType);
						} else {
							Object arg = objenesis.getInstantiatorOf(parameterType).newInstance();
							args[i++] = arg;
						}
					}
					try {
						method.invoke(obj, args);
					} catch (Exception e) {
					}
					args = null;
				}
			}
		}
	}

}
