package cn.xy.servlet;

import cn.xy.bean.Store;
import cn.xy.service.StoreService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 登录servlet
 * @Author  pzg
 *
 **/
@Data
@Component
public class StoreLoginServlet extends HttpServlet {
    @Autowired
    private StoreService storeService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("LOGIN-STATUS")==null){
            resp.sendRedirect(req.getContextPath()+"/store/storeLogin.jsp");
        }
        resp.getWriter().println("Login success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store =storeService.login(req.getParameter("sName"), req.getParameter("sPassword"));
        if(store != null){
            req.getSession().setAttribute("LOGIN-STATUS",store);
            resp.sendRedirect(req.getContextPath()+"/product.jsp");
        }else {
            resp.sendRedirect(req.getContextPath()+"/storeLogin.jsp?error=loginFail");
        }
    }
}
