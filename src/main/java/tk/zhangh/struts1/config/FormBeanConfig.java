package tk.zhangh.struts1.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.zhangh.struts1.action.ActionForm;
import tk.zhangh.struts1.action.ActionServlet;

/**
 * Form配置信息
 * <p>
 * Form配置信息封装类,使用了lombok生成相关get,set方法
 * <p>
 * Created by ZhangHao on 2016/12/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormBeanConfig {
    private String name;
    private String type;

    public ActionForm createActionForm(ActionServlet servlet) {
        try {
            ActionForm form = (ActionForm) Class.forName(type).newInstance();
            form.setServlet(servlet);
            return form;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
