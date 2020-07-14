/**
 * 
 */
package example.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description:
 * 
 * @version 1.0
 * @see
 * 
 * @author <a href="mailto:johnsonzhiwen@163.com">郑智文(Johnson)</a>
 * @date 2012-12-12 下午11:31:30
 */
public class JdkServiceProxy implements InvocationHandler
{

	private Object protoObj;

	/**
	 * @param protoObj
	 */
	public JdkServiceProxy(Object protoObj)
	{
		super();
		this.protoObj = protoObj;
	}

	/**
	 * @see InvocationHandler#invoke(Object,
	 *      Method, Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{

		before();
		Object result = method.invoke(protoObj, args);
		after();
		return result;
	}

	private void before()
	{
		System.out.println("before...");
	}

	private void after()
	{
		System.out.println("after...");
	}

}
