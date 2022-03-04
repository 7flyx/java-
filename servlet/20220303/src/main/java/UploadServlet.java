import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-04
 * Time: 17:28
 * Description:
 */

//下面这条注释，然后才能调用getPart方法
@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从请求中获取文件
        Part part = req.getPart("MyFile");//参数是input标签的name
        System.out.println(part.getContentType()); //文件的类型
        System.out.println(part.getSize()); //文件大小，单位字节
        System.out.println(part.getSubmittedFileName()); //文件的全名程，包括了后缀名
        part.write("d:/java101/myfile.txt"); //将文件写入硬盘，需要自己定义文件名和后缀名
        resp.getWriter().write("upload success");
    }
}
