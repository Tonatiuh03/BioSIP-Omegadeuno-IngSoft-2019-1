/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Usuario;
import mx.unam.is20191.utils.Config;

/**
 *
 * @author jrcvd
 */
public class ProfFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest
                = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse
                = (HttpServletResponse) servletResponse;

        // managed bean name is exactly the session attribute name
        Usuario userManager = (Usuario) httpServletRequest
                .getSession().getAttribute("user");

        if (userManager != null) {
            if (UsuarioDao.isProfesor(userManager)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                System.err.println("No es profesor");
                httpServletResponse.sendRedirect(
                    httpServletRequest.getContextPath() + Config.USR_PRINCIPAL_PAGE);
            }
        } else {
            System.err.println("userManager not found");
            // user is not logged in, redirect to login page
            httpServletResponse.sendRedirect(
                    httpServletRequest.getContextPath() + Config.LOGIN_PAGE);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.err.println("loginFilter initialized");
    }

    @Override
    public void destroy() {
        // close resources
    }
}
