package tk.zhangh.struts1.annotation;

import java.lang.annotation.*;

/**
 * From类标记注解
 * <p>
 * Form Bean标记注解,相当于struts中的<form-bean/>标签
 * <p>
 * Created by ZhangHao on 2016/12/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Forms {
}
