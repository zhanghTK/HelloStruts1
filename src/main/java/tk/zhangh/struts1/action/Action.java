package tk.zhangh.struts1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 业务逻辑接口类
 * <p>
 * Created by ZhangHao on 2016/11/7.
 */
public abstract class Action {

    public abstract ActionForward execute(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest req, HttpServletResponse resp);
}
