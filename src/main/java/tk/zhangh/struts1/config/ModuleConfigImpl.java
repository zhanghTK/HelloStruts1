package tk.zhangh.struts1.config;

import lombok.ToString;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 上下文配置信息
 * <p>
 * 提供Action,Form等配置信息
 * <p>
 * Created by ZhangHao on 2016/11/8.
 */
@ToString
public class ModuleConfigImpl implements ModuleConfig {
    /**
     * Action配置信息集合
     */
    private Map<String, ActionConfig> actionConfigs = new ConcurrentHashMap<>();

    /**
     * Form配置信息集合
     */
    private Map<String, FormBeanConfig> formBeanConfigs = new ConcurrentHashMap<>();

    @Override
    public void addActionConfig(ActionConfig actionConfig) {
        actionConfigs.put(actionConfig.getPath(), actionConfig);
    }

    @Override
    public ActionConfig findActionConfig(String path) {
        return actionConfigs.get(path);
    }

    @Override
    public void addFormBeanConfig(FormBeanConfig formBeanConfig) {
        formBeanConfigs.put(formBeanConfig.getName(), formBeanConfig);
    }

    @Override
    public FormBeanConfig findFormBeanConfig(String name) {
        return formBeanConfigs.get(name);
    }
}
