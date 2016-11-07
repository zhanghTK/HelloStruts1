package tk.zhangh.struts1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http�����ʵ�ʴ�����
 *
 * Created by ZhangHao on 2016/11/7.
 */
public class RequestProcessor {

    public void process(HttpServletRequest req, HttpServletResponse resp) {
        // ��ȡ����·����Ϣ
        String path = getRequestPath(req, resp);
        // ��ȡ��ǰ·����Ӧ��ActionMapping
        ActionMapping mapping = getActionMapping(req, resp, path);
        // ��ActionMapping��ȡActionForm������֤
        ActionForm form = getActionForm(req, resp, mapping);
        // ��ActionMapping��ȡAction
        Action action = getAction(req, resp, mapping);
        // ��֤
        validateForm(req, resp, form, mapping);
        // ����Action
        ActionForward forward = doAction(req, resp, action, form, mapping);
        // ����ActionForward
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
