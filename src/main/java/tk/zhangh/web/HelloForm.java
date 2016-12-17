package tk.zhangh.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.zhangh.struts1.action.ActionErrors;
import tk.zhangh.struts1.action.ActionForm;
import tk.zhangh.struts1.action.ActionMapping;
import tk.zhangh.struts1.annotation.Forms;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试使用Form
 * <p>
 * HelloAction对应的Form类
 * <p>
 * Created by ZhangHao on 2016/12/15.
 */
@Forms
@Data
@NoArgsConstructor
public class HelloForm extends ActionForm {

    private String name;

    private String pass;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        name = null;
        pass = null;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return new ActionErrors();
    }
}
