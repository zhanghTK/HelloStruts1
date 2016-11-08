package tk.zhangh.struts1.config;

import tk.zhangh.struts1.action.ActionMapping;

/**
 * 上下文配置信息接口
 * Created by ZhangHao on 2016/11/8.
 */
public interface ModuleConfig {
    /**
     * 获取ActionMapping
     * @return actionMapping
     */
    ActionMapping getActionMapping();
}
