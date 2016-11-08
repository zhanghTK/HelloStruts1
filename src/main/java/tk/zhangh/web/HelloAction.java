package tk.zhangh.web;

import tk.zhangh.struts1.action.Action;
import tk.zhangh.struts1.action.ActionForm;
import tk.zhangh.struts1.action.ActionForward;
import tk.zhangh.struts1.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ≤‚ ‘ π”√Action
 *
 * Created by ZhangHao on 2016/11/8.
 */
public class HelloAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.getWriter().println("Hello World");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
