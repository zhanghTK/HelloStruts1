package tk.zhangh.struts1.config;

/**
 * 上下文配置信息接口
 * Created by ZhangHao on 2016/11/8.
 */
public interface ModuleConfig {

    /**
     * 注册Action配置信息
     * @param actionConfig Action配置信息
     */
    void addActionConfig(ActionConfig actionConfig);

    /**
     * 查找Action配置信息
     * @param path 请求路径
     * @return Action配置信息
     */
    ActionConfig findActionConfig(String path);
}
