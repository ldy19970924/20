package cn.xy.servlet;

import cn.xy.bean.Store;
import cn.xy.service.StoreService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 通过手机号登录功能servlet
 * @Author  pzg
 *
 **/
@WebServlet("/StoreLoginByPhoneServlet")
@Data
@Component
public class StoreLoginByPhoneServlet extends HttpServlet {
    @Autowired
    private StoreService storeService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("LOGIN-STATUS")==null){
            response.sendRedirect(request.getContextPath()+"/storeLoginByPhone.jsp");
        }
        response.getWriter().println("Login success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Store store = storeService.loginByPhone(request.getParameter("phone"), request.getParameter("code"));
            if(store != null){
                request.getSession().setAttribute("LOGIN-STATUS",store);
            }else {
                response.sendRedirect(request.getContextPath()+"/storeLoginByPhone.jsp?error=loginFail");
            }
    }
}
