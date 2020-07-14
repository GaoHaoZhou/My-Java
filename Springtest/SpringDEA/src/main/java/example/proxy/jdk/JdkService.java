/**
 * 
 */
package example.proxy.jdk;

/**
 * Description:
 * 
 * @version 1.0
 * @see
 * 
 * @author <a href="mailto:johnsonzhiwen@163.com">郑智文(Johnson)</a>
 * @date 2012-12-12 下午11:30:15
 */
public class JdkService implements IJdkService
{

	/**
	 * @see IJdkService#service()
	 */
	@Override
	public void service()
	{
		System.out.println("service...");
	}

}
