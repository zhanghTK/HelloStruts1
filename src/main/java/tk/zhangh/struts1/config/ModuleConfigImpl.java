package tk.zhangh.struts1.config;

import tk.zhangh.struts1.action.ActionMapping;

/**
 * ������������Ϣ
 * Created by ZhangHao on 2016/11/8.
 */
public class ModuleConfigImpl implements ModuleConfig {
    private ActionMapping actionMapping = new ActionMapping();

    @Override
    public ActionMapping getActionMapping() {
        return actionMapping;
    }
}
