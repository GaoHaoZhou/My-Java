/**
 * 
 */
package example.proxy;

import example.proxy.cglib.CglibService;
import example.proxy.cglib.CglibServiceInterceptor;
import example.proxy.jdk.IJdkService;
import example.proxy.jdk.JdkService;
import example.proxy.jdk.JdkServiceProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Description:
 * 
 * @version 1.0
 * @see
 * 
 * @author <a href="mailto:johnsonzhiwen@163.com">郑智文(Johnson)</a>
 * @date 2012-12-12 下午10:29:11
 */
public class Main
{

	private static void processCglibProxy()
	{
		CglibService interceptor = new CglibServiceInterceptor()
				.createObject(new CglibService());
		interceptor.service();
	}

	private static void processJDKProxy()
	{
		IJdkService protoService = new JdkService();
		InvocationHandler handler = new JdkServiceProxy(protoService);
		IJdkService proxyService = (IJdkService) Proxy.newProxyInstance(
				protoService.getClass().getClassLoader(),
				new Class[] { IJdkService.class }, handler);
		proxyService.service();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("*********************************cglib:");
		processCglibProxy();
		System.out.println("***********************************jdk:");
		processJDKProxy();
	}

}
