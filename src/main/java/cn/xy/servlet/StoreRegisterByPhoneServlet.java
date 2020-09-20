package cn.xy.servlet;


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
 * @Description 通过手机号注册的servlet
 * @Author  pzg
 *
 **/
@Data
@Component
public class StoreRegisterByPhoneServlet extends HttpServlet {
    @Autowired
    private StoreService storeService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String sPhone = req.getParameter("sPhone");
        String code = req.getParameter("code");
        int state = 0;
        String cdCard = req.getParameter("cdCard");
        String sName=sPhone;
        String sPassword =req.getParameter("sPassword");
        int k = storeService.registerByPhone(sName,sPhone,sPassword,cdCard,state,code);
        if (k > 0) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("registerByPhone.jsp").forward(req, resp);
        }
    }
}
