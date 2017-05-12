package cloud.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware{
	private static ApplicationContext applicationContext;
	private SpringUtil(){
		
	}
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		applicationContext = ac;
	}
	public static <T> T getBean(String beanName,Class<T> classType){
		return applicationContext.getBean(beanName,classType);
	}
	public static<T> T getBean(Class<T> type){
		return applicationContext.getBean(type);
	}
	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}
}
