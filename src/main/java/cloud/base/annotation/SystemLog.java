package cloud.base.annotation;

import java.lang.annotation.*;  

/** 
 *自定义注解 日志类
 */  
  
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface SystemLog {  
  
    String module()  default "";  //所属功能模块
    String description()  default "";  //描述
    
}  
