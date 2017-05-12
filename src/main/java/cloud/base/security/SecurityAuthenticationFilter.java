package cloud.base.security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class SecurityAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
        
        /* 
         * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
         
         *	此方法用作Spring security登录验证方法，
         *  表单用户名属性应为：j_username，密码应为：j_password"，也可以复写obtainUsername和obtainPassword方法改变属性名称
         *	如果完成验证，返回UsernamePasswordAuthenticationToken 对象，否则返回空
         *  
         */
        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        		
//                if (!request.getMethod().equals("POST")) {
//                        throw new AuthenticationServiceException("登录方法只支持POST方式，不支持" + request.getMethod()+"方法");
//                }
//                如果需要定制业务，可以重写此方法。
//                BadCredentialsException exception = new BadCredentialsException("用户名或密码不匹配");  
//                                                                throw exception;  
                String username = obtainUsername(request);
                String password = obtainPassword(request);
                //实现验证
        		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                //允许设置用户详细属性
                setDetails(request, authRequest);
                //运行
                return this.getAuthenticationManager().authenticate(authRequest);
        }
}
