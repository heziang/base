package cloud.base.cxf;


import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace="base")//如果不写targetNamespace的值时，默认是包反转，比如服务器项目中包是1.2.3，那么默认值为3.2.1，如果在另外
//的项目客户端中调用，则创建接口类HelloWorld时，类名可以不一样，但是targetNamespace必须一样。不然调用不成功！最好自己定义一个名称
@SOAPBinding(style = Style.RPC)
public interface HelloWorld {
    String sayHi(String text);
}
