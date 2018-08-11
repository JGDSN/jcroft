package de.agdsn.jcroft.security.spring;

import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public interface RequestRedirector {
    public default void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/login?from="+request.getRequestURI()+reconstructParameters(request).toString());
    }
    public default StringBuilder reconstructParameters(HttpServletRequest request){
        StringBuilder build = new StringBuilder();
        if(request.getMethod().equalsIgnoreCase(HttpMethod.GET.name())){
            int i = 0;
            Enumeration<String> params = request.getParameterNames();
            while(params.hasMoreElements()){
                String param = params.nextElement();
                if(i==0)build.append("?");
                else build.append("&");
                build.append(param);
                String value = request.getParameter(param);
                if(value!=null&&!value.isEmpty()){
                    build.append("=");
                    build.append(value);
                }
                i++;
            }
        }
        return build;
    }
}
