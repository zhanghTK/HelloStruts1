package tk.zhangh.struts1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http请求的实际处理类
 *
 * Created by ZhangHao on 2016/11/7.
 */
public class RequestProcessor {

    public void process(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求路径信息
        String path = getRequestPath(req, resp);
        // 获取当前路径对应的ActionMapping
        ActionMapping mapping = getActionMapping(req, resp, path);
        // 从ActionMapping获取ActionForm，并验证
        ActionForm form = getActionForm(req, resp, mapping);
        // 从ActionMapping获取Action
        Action action = getAction(req, resp, mapping);
        // 验证
        validateForm(req, resp, form, mapping);
        // 调用Action
        ActionForward forward = doAction(req, resp, action, form, mapping);
        // 处理ActionForward
        processForwardConfig(req, resp, forward);
    }

    protected String getRequestPath(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }

    protected ActionMapping getActionMapping(HttpServletRequest req, HttpServletResponse resp, String path) {
        return null;
    }

    protected ActionForm getActionForm(HttpServletRequest req, HttpServletResponse resp, ActionMapping mapping) {
        return initActionForm(req, resp);
    }

    protected Action getAction(HttpServletRequest req, HttpServletResponse resp, ActionMapping mapping) {
        return null;
    }

    protected ActionForm initActionForm(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }

    protected void validateForm(HttpServletRequest req, HttpServletResponse resp,
                                ActionForm form, ActionMapping mapping) {

    }

    protected ActionForward doAction(HttpServletRequest req, HttpServletResponse resp,
                                     Action action, ActionForm form, ActionMapping mapping) {
        return null;
    }

    protected void processForwardConfig(HttpServletRequest req, HttpServletResponse resp, ActionForward forward) {

    }
}
