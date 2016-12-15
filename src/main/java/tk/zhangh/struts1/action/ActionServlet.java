package tk.zhangh.struts1.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.zhangh.struts1.annotation.Actions;
import tk.zhangh.struts1.config.ActionConfig;
import tk.zhangh.struts1.config.FormBeanConfig;
import tk.zhangh.struts1.config.ModuleConfig;
import tk.zhangh.struts1.config.ModuleConfigImpl;
import tk.zhangh.web.HelloAction;
import tk.zhangh.web.HelloForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 整个框架的入口
 * <p>
 * 框架的初始化
 * 所有请求的入口
 * <p>
 * Created by ZhangHao on 2016/11/7.
 */
public class ActionServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(ActionServlet.class);

    RequestProcessor requestProcessor;
    ModuleConfig moduleConfig;

    /**
     * 框架初始化
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        logger.info(getClass().getName() + " init");
        initModuleConfig();
        initRequestProcess();
    }

    /**
     * 初始化ModuleConfig
     */
    private void initModuleConfig() {
        logger.info("init ModuleConfig");
        moduleConfig = new ModuleConfigImpl();
        createActionConfig().forEach(moduleConfig::addActionConfig);
        createFormBeanConfig().forEach(moduleConfig::addFormBeanConfig);
    }

    /**
     * 创建ActionConfig实例列表
     *
     * @return actionConfig列表
     */
    private List<ActionConfig> createActionConfig() {
        // todo 动态创建
        List<ActionConfig> actions = new ArrayList<>();
        ActionConfig actionConfig = new ActionMapping();
        Class clazz = HelloAction.class;
        Actions annotation = (Actions) clazz.getAnnotation(Actions.class);
        actionConfig.setPath(annotation.path());
        actionConfig.setName(annotation.name());
        actionConfig.setType(clazz.getName());
        logger.info("add ActionConfig " + actionConfig);
        actions.add(actionConfig);
        return actions;
    }

    /**
     * 创建FormConfig实例列表
     *
     * @return formConfig列表
     */
    private List<FormBeanConfig> createFormBeanConfig() {
        // todo 动态创建
        List<FormBeanConfig> forms = new ArrayList<>();
        FormBeanConfig formBeanConfig = new FormBeanConfig();
        Class clazz = HelloForm.class;
        String name = clazz.getSimpleName();
        name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
        formBeanConfig.setName(name);
        formBeanConfig.setType(clazz.getName());
        logger.info("add FormConfig " + formBeanConfig);
        forms.add(formBeanConfig);
        return forms;
    }

    /**
     * 初始化RequestProcess
     */
    private void initRequestProcess() {
        logger.info("init RequestProcess");
        requestProcessor = new RequestProcessor(this);
        requestProcessor.init(moduleConfig);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("process GET request");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("process POST request");
        process(req, resp);
    }

    /**
     * 委托给RequestProcessor类进行处理
     */
    protected void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        requestProcessor.process(req, resp);
    }
}
