package tk.zhangh.struts1.annotation;

import java.lang.annotation.*;

/**
 * Action类标记注解
 * <p>
 * Created by ZhangHao on 2016/12/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Actions {

    /**
     * 指定Action处理请求路径
     *
     * @return 处理请求路径
     */
    String path();

    /**
     * 指定from名称
     *
     * @return from名称
     */
    String name() default "";

    /**
     * 指定转发的URL路径
     *
     * @return 转发URL路径
     */
    String[] forwards() default "";

    /**
     * 指定包含输入表单的URL路径，表单验证失败时，请求会被转发到该URL中
     *
     * @return 输入表单URL路径
     */
    String input() default "";

    /**
     * 指定是否要先调用ActionForm Bean的validate()方法。默认为true
     *
     * @return 是否调用ActionForm Bean的validate()方法
     */
    boolean value() default true;
}
