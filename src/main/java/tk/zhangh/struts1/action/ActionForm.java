package tk.zhangh.struts1.action;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

/**
 * 抽象表单Form类
 * <p>
 * 对应页面表单Form,用于保存请求的数据信息和辅助完成数据验证
 * <p>
 * Created by ZhangHao on 2016/11/7.
 */
@Data
@NoArgsConstructor
public abstract class ActionForm {
    private transient ActionServlet servlet;

    public abstract void reset(ActionMapping mapping, HttpServletRequest request);

    public abstract ActionErrors validate(ActionMapping mapping, HttpServletRequest request);
}
