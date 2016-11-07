package tk.zhangh.struts1.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ������ܵ����
 *
 * ��ܵĳ�ʼ��
 * ������������
 *
 * Created by ZhangHao on 2016/11/7.
 */
public class ActionServlet extends HttpServlet {
    RequestProcessor requestProcessor;

    /**
     * ��ܳ�ʼ��
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        // todo ��ȡ�����ļ�
        // todo ��ɸ������ʼ��
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * ί�и�RequestProcessor����д���
     */
    protected void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        requestProcessor.process(req, resp);
    }
}
