package tk.zhangh.struts1.action;

import tk.zhangh.struts1.config.ModuleConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http�����ʵ�ʴ�����
 *
 * Created by ZhangHao on 2016/11/7.
 */
public class RequestProcessor {

    private ModuleConfig moduleConfig;

    /**
     * ��ʼ��������Ϣ
     * @param moduleConfig ������Ϣ
     */
    public void init(ModuleConfig moduleConfig) {
        this.moduleConfig = moduleConfig;
    }

    /**
     * ��������
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
     * ��ȡActionMapping
     * @return actionMapping
     */
    private ActionMapping getActionMapping() {
        return moduleConfig.getActionMapping();
    }

    /**
     * ��ȡAction
     * @param req request
     * @return Action
     */
    protected Action getAction(HttpServletRequest req) {
        String path = req.getPathInfo();
        ActionMapping mapping = getActionMapping();
        return mapping.findAction(path);
    }

    /**
     * ���������������Form
     * @param req request
     * @return ActionForm
     */
    protected ActionForm initActionForm(HttpServletRequest req) {
        return null;
    }

    /**
     * ����ʵ�ʴ�����
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
