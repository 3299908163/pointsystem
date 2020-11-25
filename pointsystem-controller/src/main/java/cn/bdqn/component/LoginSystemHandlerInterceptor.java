package cn.bdqn.component;

import cn.bdqn.pointsystem.dao.UserMapper;
import cn.bdqn.pointsystem.emtitys.User;
import cn.bdqn.pointsystem.utlis.Constant;
import cn.bdqn.pointsystem.utlis.ReturnResult;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 跳转教师登陆页面
 */
public class LoginSystemHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User attribute = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
        boolean fdlg = true;
        if (attribute == null) {
            response.sendRedirect("/jump/loginTeacher");
            fdlg= false;
        } else {
            String sessionId = request.getSession(true).getId();
            String userSessionId = userMapper.getSessionIdById(attribute.getId());
            //session id不匹配
            if (!sessionId.equals(userSessionId)) {
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(ReturnResult.returnFail(null, null, 600)));
                if (1==attribute.getIsAdmin()){
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
}
