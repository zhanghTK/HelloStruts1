package tk.zhangh.struts1.action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 保存框架对特定请求的处理流程配置信息
 *
 * Created by ZhangHao on 2016/11/7.
 */
public class ActionMapping {

    private Map<String, Action> mapping = new ConcurrentHashMap<>();

    public Action findAction(String url) {
        return mapping.get(url);
    }

    public void registerAction(String url, Action action) {
        mapping.put(url, action);
    }
}
