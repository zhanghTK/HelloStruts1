package tk.zhangh.struts1.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Action配置信息
 * <p>
 * Action类的配置信息封装类,使用了lombok生成相关get,set方法
 * <p>
 * Created by ZhangHao on 2016/11/9.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionConfig {
    /**
     * 请求路径
     */
    private String path;

    /**
     * 请求路径对应的Action全类名
     */
    private String type;

    /**
     * 请求对应的from bean 名称
     */
    private String name;

    /**
     * 有效期
     */
    private String scope = "session";
}
