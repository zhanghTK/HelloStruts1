package tk.zhangh.struts1.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 整个框架的入口
 *
 * 框架的初始化
 * 所有请求的入口
 *
 * Created by ZhangHao on 2016/11/7.
 */
public class ActionServlet extends HttpServlet {
    RequestProcessor requestProcessor;

    /**
     * 框架初始化
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        // todo 读取配置文件
        // todo 完成各组件初始化
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * 委托给RequestProcessor类进行处理
     */
    protected void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        requestProcessor.process(req, resp);
    }
}
