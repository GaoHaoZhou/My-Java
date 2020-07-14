/**
 * 
 */
package example.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Description:
 * 
 * @version 1.0
 * @see
 * 
 * @author <a href="mailto:johnsonzhiwen@163.com">郑智文(Johnson)</a>
 * @date 2012-12-11 下午2:35:17
 */
public class CglibServiceInterceptor implements MethodInterceptor
{

	@SuppressWarnings("unchecked")
	public <T> T createObject(T t)
	{

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(t.getClass());
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}

	/**
	 * @see MethodInterceptor#intercept(Object,
	 *      Method, Object[],
	 *      MethodProxy)
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable
	{
		System.out.println("before...");
		Object returnObj = proxy.invokeSuper(obj, args);
		System.out.println("after...");
		return returnObj;
	}

}
