package tk.zhangh.struts1.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.zhangh.struts1.config.ActionConfig;
import tk.zhangh.struts1.config.FormBeanConfig;
import tk.zhangh.struts1.config.ModuleConfig;
import tk.zhangh.web.HelloForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http请求的实际处理类
 * <p>
 * Created by ZhangHao on 2016/11/7.
 */
public class RequestProcessor {

    private static final Logger logger = LoggerFactory.getLogger(RequestProcessor.class);

    private ModuleConfig moduleConfig;

    private ActionServlet actionServlet;

    public RequestProcessor(ActionServlet actionServlet) {
        this.actionServlet = actionServlet;
    }

    /**
     * 初始化配置信息
     *
     * @param moduleConfig 配置信息
     */
    public void init(ModuleConfig moduleConfig) {
        logger.info("init ModuleConfig with " + moduleConfig.toString());
        this.moduleConfig = moduleConfig;
    }

    /**
     * 处理请求
     *
     * @param request  request
     * @param response response
     */
    public void process(HttpServletRequest request, HttpServletResponse response) {
        ActionMapping mapping = getActionMapping(request);
        Action action = createAction(mapping);
        ActionForm form = createActionForm(mapping);
        initForm(form, request);
        form.validate(mapping, request);
        doAction(request, response, action, form, mapping);
    }

    private void initForm(ActionForm form, HttpServletRequest request) {
        // todo 根据request填充form
        HelloForm helloForm = (HelloForm) form;
        helloForm.setName(request.getParameter("name"));
        helloForm.setPass(request.getParameter("pass"));
    }

    /**
     * 获取ActionMapping
     *
     * @return actionMapping
     */
    private ActionMapping getActionMapping(HttpServletRequest req) {
        String path = req.getPathInfo();
        logger.info("get action mapping,request path:" + path);
        return (ActionMapping) moduleConfig.findActionConfig(path);
    }


    /**
     * 获取Action
     *
     * @param mapping actionMapping
     * @return action
     */
    protected Action createAction(ActionMapping mapping) {
        logger.info("get action,actionMapping:" + mapping);
        String type = mapping.getType();
        try {
            Class actionClass = Class.forName(type);
            return (Action) actionClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("get Action:" + type + " instance error:", e);
            throw new RuntimeException("get Action:" + type + " instance error:", e);
        }
    }

    /**
     * 获取ActionForm
     *
     * @param actionConfig actionConfig
     * @return actionForm
     */
    public ActionForm createActionForm(ActionConfig actionConfig) {
        String name = actionConfig.getName();
        FormBeanConfig config = moduleConfig.findFormBeanConfig(name);
        return config.createActionForm(actionServlet);
    }

    /**
     * 请求实际处理方法
     *
     * @param req     request
     * @param resp    response
     * @param action  action
     * @param form    actionForm
     * @param mapping actionMapping
     * @return actionForward
     */
    protected ActionForward doAction(HttpServletRequest req, HttpServletResponse resp,
                                     Action action, ActionForm form, ActionMapping mapping) {
        return action.execute(mapping, form, req, resp);
    }
}

