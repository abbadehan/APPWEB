/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compras.web.filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SISTEMAS
 */
@WebFilter (filterName ="AutenticacionFilter", urlPatterns = ("/*"))
public class AutenticacionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(" Iniciando el filtro de autenticacion ");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       String url = ((HttpServletRequest)request).getRequestURL().toString();

 if (url.contains("login.html")) {
        chain.doFilter(request, response);
        }else{
            //1.-obtener la sesion
        //tenemos que hacer un casting
        HttpSession sesion = ((HttpServletRequest)request).getSession();
        //2.-leer atributo usuario
        //3.- si no nos hemos logueado hacermos un redirect login si no chain.dofilter
        
        if(sesion.getAttribute("usuario")!=null){
            
            chain.doFilter(request, response);
        }else{
           ((HttpServletResponse)response).sendRedirect("login.html");
        }
        }
    }

    @Override
    public void destroy() {
        System.out.println("Destruyendoel filtro de autenticacion");
    }
    
}
