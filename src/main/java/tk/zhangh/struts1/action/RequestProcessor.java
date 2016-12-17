package tk.zhangh.struts1.action;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.zhangh.struts1.config.FormBeanConfig;
import tk.zhangh.struts1.config.ModuleConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求实际处理
 * <p>
 * Http请求的实际处理类,完成映射,校验,业务逻辑调用等
 * <p>
 * Created by ZhangHao on 2016/11/7.
 */
public class RequestProcessor {

    private static final Logger logger = LoggerFactory.getLogger(RequestProcessor.class);

    private ModuleConfig moduleConfig;

    private ActionServlet actionServlet;

    protected Map<String, Action> actions = new ConcurrentHashMap<>();

    public RequestProcessor(ActionServlet actionServlet) {
        this.actionServlet = actionServlet;
    }

    /**
     * 初始化配置信息
     *
     * @param moduleConfig 配置信息
     */
    public void init(ModuleConfig moduleConfig) {
        logger.info("init ModuleConfig with " + moduleConfig.toString());
        this.moduleConfig = moduleConfig;
    }

    /**
     * 处理请求
     *
     * @param request  request
     * @param response response
     */
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String path = processPath(request);
            ActionMapping mapping = processMapping(path);
            ActionForm form = processActionForm(request, mapping);
            processPopulate(request, form, mapping);
            processValidate(request, form, mapping);
            Action action = processActionCreate(mapping);
            ActionForward forward = processActionPerform(request, response, action, form, mapping);
            // todo process forward
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, e.getMessage());
        }


    }

    /**
     * 获取请求action路径
     *
     * @param request 请求对象
     * @return 请求的action路径
     */
    private String processPath(HttpServletRequest request) {
        return request.getPathInfo();
    }

    /**
     * 根据路径获取映射的actionMapping
     *
     * @param path 请求路径
     * @return actionMapping
     */
    private ActionMapping processMapping(String path) {
        logger.info("get action mapping,request path:" + path);
        try {
            return (ActionMapping) moduleConfig.findActionConfig(path);
        } catch (Exception e) {
            throw new RuntimeException("page not found");
        }
    }

    /**
     * 创建ActionForm对象,并设置生命范围
     *
     * @return actionForm
     */
    private ActionForm processActionForm(HttpServletRequest request, ActionMapping mapping) {
        ActionForm form = createActionForm(request, mapping, moduleConfig);
        if (form != null) {
            if ("request".equals(mapping.getScope())) {
                request.setAttribute(mapping.getName(), form);
            } else {
                request.getSession().setAttribute(mapping.getName(), form);
            }
        }
        return form;
    }

    /**
     * 请求对象内数据转化为Form实例内的数据
     */
    private void processPopulate(HttpServletRequest request, ActionForm form, ActionMapping mapping) {
        if (form == null) {
            return;
        }
        form.reset(mapping, request);
        try {
            populate(form, request);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("populate form error");
        }
    }

    /**
     * Request向Form的具体转化过程
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void populate(Object form, HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
        Map<String, String> properties = new HashMap<>();
        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String value = request.getParameter(name);
            properties.put(name, value);
        }
        BeanUtils.populate(form, properties);
    }

    /**
     * 调用最终业务逻辑
     *
     * @return 请求的下一个目的地
     */
    private ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response,
                                               Action action, ActionForm form, ActionMapping mapping) {
        return action.execute(mapping, form, request, response);
    }

    /**
     * 验证from内数据信息
     *
     * @return 验证是否通过
     */
    private boolean processValidate(HttpServletRequest request, ActionForm form, ActionMapping mapping) {
        if (form == null) {
            return true;
        } else {
            ActionErrors errors = form.validate(mapping, request);
            return errors != null && !errors.isEmpty();
        }
    }

    /**
     * 创建ActionForm对象
     *
     * @return actionForm
     */
    private ActionForm createActionForm(HttpServletRequest request, ActionMapping mapping, ModuleConfig moduleConfig) {
        String name = mapping.getName();
        FormBeanConfig config = moduleConfig.findFormBeanConfig(name);
        ActionForm form = lookupActionForm(request, name, mapping.getScope());
        if (form == null) {
            form = config.createActionForm(actionServlet);
        }
        return form;
    }

    /**
     * 根据生命范围查找ActionForm实例
     *
     * @return actionForm
     */
    private ActionForm lookupActionForm(HttpServletRequest request, String attribute, String scope) {
        return (ActionForm) ("request".equals(scope)
                ? request.getAttribute(attribute)
                : request.getSession().getAttribute(attribute));
    }


    /**
     * 获取Action实例,如不存在则创建
     *
     * @param mapping 请求action路径
     * @return action
     */
    private Action processActionCreate(ActionMapping mapping) {
        String type = mapping.getType();
        Action instance = actions.get(type);
        if (instance == null) {
            try {
                instance = (Action) Class.forName(type).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("create action error:" + mapping.getPath());
            }
        }
        return instance;
    }
}

