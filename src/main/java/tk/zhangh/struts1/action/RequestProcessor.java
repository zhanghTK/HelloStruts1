package tk.zhangh.struts1.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.zhangh.struts1.config.ModuleConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http请求的实际处理类
 *
 * Created by ZhangHao on 2016/11/7.
 */
public class RequestProcessor {

    private static final Logger logger = LoggerFactory.getLogger(RequestProcessor.class);

    private ModuleConfig moduleConfig;

    /**
     * 初始化配置信息
     * @param moduleConfig 配置信息
     */
    public void init(ModuleConfig moduleConfig) {
        logger.info("init ModuleConfig with " + moduleConfig.toString());
        this.moduleConfig = moduleConfig;
    }

    /**
     * 处理请求
     * @param req request
     * @param resp response
     */
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        ActionMapping mapping = getActionMapping(req);
        Action action = getAction(mapping);
        ActionForm form = initActionForm(mapping, req);
        doAction(req, resp, action, form, mapping);
    }

    /**
     * 获取ActionMapping
     * @return actionMapping
     */
    private ActionMapping getActionMapping(HttpServletRequest req) {
        String path = req.getPathInfo();
        logger.info("get action mapping,request path:" + path);
        return (ActionMapping)moduleConfig.findActionConfig(path);
    }


    /**
     * 获取Action
     * @param mapping actionMapping
     * @return action
     */
    protected Action getAction(ActionMapping mapping) {
        logger.info("get action,actionMapping:" + mapping);
        String type = mapping.getType();
        try {
            Class actionClass = Class.forName(type);
            return (Action)actionClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("get Action:" + type +" instance error:", e);
            throw new RuntimeException("get Action:" + type +" instance error:", e);
        }
    }

    /**
     * 根据请求参数生成Form
     * @param req request
     * @return ActionForm
     */
    protected ActionForm initActionForm(ActionMapping mapping, HttpServletRequest req) {
        return null;
    }

    /**
     * 请求实际处理方法
     * @param req request
     * @param resp response
     * @param action action
     * @param form actionForm
     * @param mapping actionMapping
     * @return actionForward
     */
    protected ActionForward doAction(HttpServletRequest req, HttpServletResponse resp,
                                     Action action, ActionForm form, ActionMapping mapping) {
        return action.execute(mapping, form, req, resp);
    }
}
