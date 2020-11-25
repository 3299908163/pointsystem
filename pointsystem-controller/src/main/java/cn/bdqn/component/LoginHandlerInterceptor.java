package cn.bdqn.component;

import cn.bdqn.pointsystem.dao.UserMapper;
import cn.bdqn.pointsystem.emtitys.User;
import cn.bdqn.pointsystem.utlis.Constant;
import cn.bdqn.pointsystem.utlis.ReturnResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 拦截器
 *
 * @author yajun
 * @create 2019/12/25
 */
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User attribute = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
        boolean fdlg = true;
        if (attribute == null) {
            response.sendRedirect("/");
            fdlg= false;
        } else {
            String sessionId = request.getSession(true).getId();
            String userSessionId = userMapper.getSessionIdById(attribute.getId());
            //session id不匹配
            if (!sessionId.equals(userSessionId)) {
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(ReturnResult.returnFail(null, null, 600)));
                if (0==attribute.getIsAdmin()){
                    // 获取session中的sessionId 判断是否一致
                    writer.flush();
                    writer.close();
                }else {
                    writer.write(JSON.toJSONString(ReturnResult.returnFail(null, null, 600)));
                    writer.flush();
                    writer.close();
                }
                fdlg = false;

            }


        }

        return fdlg;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
