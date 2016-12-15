package tk.zhangh.struts1.config;

/**
 * 上下文配置信息接口
 * Created by ZhangHao on 2016/11/8.
 */
public interface ModuleConfig {

    /**
     * 注册Action配置信息
     *
     * @param actionConfig Action配置信息
     */
    void addActionConfig(ActionConfig actionConfig);

    /**
     * 查找Action配置信息
     *
     * @param path 请求路径
     * @return Action配置信息
     */
    ActionConfig findActionConfig(String path);


    /**
     * 注册 Action From 配置信息
     *
     * @param formBeanConfig formBeanConfig
     */
    void addFormBeanConfig(FormBeanConfig formBeanConfig);

    /**
     * 查找Form配置信息
     *
     * @param name from名称
     * @return form配置信息
     */
    FormBeanConfig findFormBeanConfig(String name);
}
