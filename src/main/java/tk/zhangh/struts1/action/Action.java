package tk.zhangh.struts1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ҵ���߼��ӿ���
 *
 * Created by ZhangHao on 2016/11/7.
 */
public abstract class Action {

    public abstract ActionForward execute(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest req, HttpServletResponse resp);
}
