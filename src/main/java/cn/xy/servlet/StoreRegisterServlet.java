package cn.xy.servlet;

import cn.xy.bean.Store;
import cn.xy.service.StoreService;
import lombok.Data;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description 注册的servlet
 * @Author  pzg
 *
 **/
@Data
@Component
public class StoreRegisterServlet extends HttpServlet {
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
        /*判断request 的请求方式是否为post并且contentType是否以multipart/开头
         *
         * 如果不是说明是普通的表单提交
         * */
        if (!ServletFileUpload.isMultipartContent(req)) {
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设定缓冲区大小
        factory.setSizeThreshold(1024*1024*5);
        //设置临时目录
        String relpath = this.getServletContext().getRealPath("/");
        File temp = new File(relpath+"/WEB-INF/temp");
        temp.mkdirs();
        factory.setRepository(temp);
        //2.创建解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置单个文件的最大上传字节数
        upload.setFileSizeMax(3*1024*1024);

        try {

            /*解析request对象，返回FileItem集合*/
            List<FileItem> items = upload.parseRequest(req);

            /*创建一个UserBean  用于封装用户数据*/
            Store store = new Store();

            /*遍历FileItem集合*/
            for (FileItem item : items) {

                String filedName = item.getFieldName();//获取name属性

                System.out.println("filedName=" + filedName);

                if (item.isFormField()) {
                    /*处理表单项*/
                    String value = item.getString("utf-8");

                    System.out.println("value=" + value);

                    if (filedName.equals("sName")) {
                        store.setSname(value);
                    } else if (filedName.equals("sPassword")) {
                        store.setSpassword(value);
                    } else if (filedName.equals("sPhone")) {
                        store.setSphone(value);
                    } else if (filedName.equals("cdCard")) {
                        store.setCdcard(value);
                    }

                } else {
                    /*处理文件上传*/

                    if (filedName.equals("sImage")) {
                        String filename = item.getName();
                        String uploadPath = relpath +"/image/store";
                        //目录打散
                        Date dt = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String date = sdf.format(dt);
                        uploadPath += "/"+date;
                        File file = new File(uploadPath);
                        file.mkdirs();
                        //同名处理
                        int index = filename.lastIndexOf(".");
                        String end = filename.substring(index);
                        //格式验证
                        List<String> ends = new ArrayList<String>();
                        ends.add(".jpg");
                        ends.add(".bmp");
                        ends.add(".gif");
                        ends.add(".png");
                        if(!ends.contains(end)) {
                            req.setAttribute("message", "格式不支持");
                            return;
                        }
                        filename = (UUID.randomUUID().toString().replace("-", "").toLowerCase()).substring(0,24)+end;
                        file  = new File(file,filename);
                        item.write(file);
                        String newfilename = date+"/"+filename;
                        store.setSimage(newfilename);
                        req.setAttribute("message", "上传成功");
                    }

                }

            }

            store.setState(0);
            System.out.println(store);
            int k=storeService.register(store.getSname(),store.getSpassword(),store.getSimage(),store.getSphone(),store.getState(),store.getCdcard());
            System.out.println(k);
            if(k >0) {
                req.getRequestDispatcher("/stores/storeLogin.jsp").forward(req, resp);
            }else{
                req.getRequestDispatcher("/stores/storeRegister.jsp").forward(req, resp);
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
