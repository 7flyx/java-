package servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-11
 * Time: 15:21
 * Description: servletContext监听器 将模板引擎放到这个上下文对象即可
 */
// 监听器
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        TemplateEngine engine = new TemplateEngine(); //模板引擎对象
        // 注意此处上下文对象的来源，原先的getServletContext()，是在继承httpServlet之后才有的
        // 当前类并没有继承该类，而是实现了监听器 ，通过当前方法的形参就能获取servletContext
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(servletContextEvent.getServletContext());
        resolver.setPrefix("/WEB-INF/template/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf8");
        engine.setTemplateResolver(resolver);
        // 将当前初始化好的模板放在servletContext对象里面，然后可以通过getAttribute进行提取模板引擎对象
        servletContextEvent.getServletContext().setAttribute("engine",engine);
        System.out.println("会话值存储成功");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
