package tk.zhangh.struts1.action;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装消息
 * <p>
 * 封装所有的信息
 * <p>
 * Created by ZhangHao on 2016/12/17.
 */
public class ActionMessages {
    private Map message = new HashMap<>();

    public boolean isEmpty() {
        return message.isEmpty();
    }
}
