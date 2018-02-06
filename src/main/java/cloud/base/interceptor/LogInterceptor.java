package cloud.base.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import cloud.base.annotation.SystemLog;
import cloud.base.model.Userinfo;
import cloud.base.service.ISysLogService;
import cloud.base.util.DateUtil;
import cloud.base.util.SecurityUtil;

@Aspect
public class LogInterceptor {
	
	//
	@Autowired
	private  ISysLogService sysLogService;
	// 定义一个切入点，拦截所有数据操作方法
	@Pointcut("execution(* cloud.base.service.impl.*.*(..))")
	private void datachange() {
		
	}


	@Around("datachange()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		
		Object object = pjp.proceed();// 执行该方法
		//根据注解得到日志内容
		Map<String, Object> map = getControllerMethodDescription(pjp);
		//保存日志
		if(map!=null){
			sysLogService.save(map);
		}
		return object;
	}
	//得到方法的注解，如果没有注解，返回null
	private  Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
       Map<String, Object> map = new HashMap<String, Object>();
       
       //得到类名
       String targetName = joinPoint.getTarget().getClass().getName();
       //得到执行的方法
       String methodName = joinPoint.getSignature().getName();
       //得到方法参数
       Object[] arguments = joinPoint.getArgs();
       //得到执行发放所属的类名
       Class targetClass = Class.forName(targetName);
       //遍历类里所有的方法，得到目标方法。
       Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
               Class[] clazzs = method.getParameterTypes();
               //考虑到重载的情况
                if (clazzs.length == arguments.length) {
                   if(method.getAnnotation(SystemLog.class)!=null){
                	   //封装日志map
                	   Userinfo user = SecurityUtil.getSessionUser().getUserinfo();
                	   Date nowDate = new Date();
                	   String formatDateString = DateUtil.date(nowDate);
                	   map.put("logtitle", method.getAnnotation(SystemLog.class).module()+"模块数据操作");
                       map.put("logcontent", user.getUsername() + "在" + formatDateString + "进行了" + method.getAnnotation(SystemLog.class).description()+"操作");
                       map.put("createtime", nowDate);
                       map.put("logtype", "1");
                       return map;
                   }
               }
           }
       }
        return null;
   }
}