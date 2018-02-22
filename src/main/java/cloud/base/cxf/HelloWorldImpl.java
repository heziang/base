package cloud.base.cxf;


import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(endpointInterface = "cloud.base.cxf.HelloWorld")////这里指定服务的接口类的路径，也可以不写
@SOAPBinding(style = Style.RPC)

public class HelloWorldImpl implements HelloWorld { 
public String sayHi(@WebParam(name = "text")String text) 
{ 
	StringBuffer sb = new StringBuffer();  
    sb.append("<?xml version='1.0' encoding='UTF-8'?>");  
    sb.append("<Result>");  
    sb.append("<cinamaName>机械战警</cinamaName>");  
    sb.append("<director>" + text + "</director>");  
    sb.append("<introduce>一部好莱坞大片，3D观影，不错！！！</introduce>");  
    sb.append("<price>25</price>");  
    sb.append("</Result>");  
    return sb.toString(); 
 } 
}
