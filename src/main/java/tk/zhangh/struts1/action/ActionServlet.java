package tk.zhangh.struts1.action;

import tk.zhangh.struts1.config.ModuleConfig;
import tk.zhangh.struts1.config.ModuleConfigImpl;
import tk.zhangh.web.HelloAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    ModuleConfig moduleConfig;

    /**
     * 框架初始化
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        initModuleConfig();
        initRequestProcess();
    }

    private void initModuleConfig() {
        moduleConfig = new ModuleConfigImpl();
        ActionMapping actionMapping = moduleConfig.getActionMapping();
        List<Action> actions = createActions();
        for (Action action : actions) {
            actionMapping.registerAction("/hello", action);
        }
    }

    private List<Action> createActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new HelloAction());
        return actions;
    }

    private void initRequestProcess() {
        requestProcessor = new RequestProcessor();
        requestProcessor.init(moduleConfig);
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
