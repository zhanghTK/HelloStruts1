package tk.zhangh.web;

import tk.zhangh.struts1.action.Action;
import tk.zhangh.struts1.action.ActionForm;
import tk.zhangh.struts1.action.ActionForward;
import tk.zhangh.struts1.action.ActionMapping;
import tk.zhangh.struts1.annotation.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试使用Action
 * <p>
 * 对应一个简单Form类
 * <p>
 * Created by ZhangHao on 2016/11/8.
 */
@Actions(path = "/hello", name = "helloForm")
public class HelloAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest req, HttpServletResponse resp) {
        try {
            HelloForm helloForm = (HelloForm) form;
            resp.getWriter().println("Hello World");
            resp.getWriter().println("name :" + helloForm.getName());
            resp.getWriter().println("pass :" + helloForm.getPass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
