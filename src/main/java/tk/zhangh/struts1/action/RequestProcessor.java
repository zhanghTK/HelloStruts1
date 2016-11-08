package tk.zhangh.struts1.action;

import tk.zhangh.struts1.config.ModuleConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http请求的实际处理类
 *
 * Created by ZhangHao on 2016/11/7.
 */
public class RequestProcessor {

    private ModuleConfig moduleConfig;

    /**
     * 初始化配置信息
     * @param moduleConfig 配置信息
     */
    public void init(ModuleConfig moduleConfig) {
        this.moduleConfig = moduleConfig;
    }

    /**
     * 处理请求
     * @param req request
     * @param resp response
     */
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        ActionMapping mapping = getActionMapping();
        Action action = getAction(req);
        ActionForm form = initActionForm(req);
        doAction(req, resp, action, form, mapping);
    }

    /**
     * 获取ActionMapping
     * @return actionMapping
     */
    private ActionMapping getActionMapping() {
        return moduleConfig.getActionMapping();
    }

    /**
     * 获取Action
     * @param req request
     * @return Action
     */
    protected Action getAction(HttpServletRequest req) {
        String path = req.getPathInfo();
        ActionMapping mapping = getActionMapping();
        return mapping.findAction(path);
    }

    /**
     * 根据请求参数生成Form
     * @param req request
     * @return ActionForm
     */
    protected ActionForm initActionForm(HttpServletRequest req) {
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
