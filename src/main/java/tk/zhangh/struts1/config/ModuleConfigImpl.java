package tk.zhangh.struts1.config;

import lombok.ToString;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 上下文配置信息
 * Created by ZhangHao on 2016/11/8.
 */
@ToString
public class ModuleConfigImpl implements ModuleConfig {
    /**
     * Action配置信息集合
     */
    private Map<String, ActionConfig> actionConfigs = new ConcurrentHashMap<>();

    @Override
    public void addActionConfig(ActionConfig actionConfig) {
        actionConfigs.put(actionConfig.getPath(), actionConfig);
    }

    @Override
    public ActionConfig findActionConfig(String path) {
        return actionConfigs.get(path);
    }
}
