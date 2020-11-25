package cn.bdqn.component;

import cn.bdqn.pointsystem.emtitys.User;
import cn.bdqn.pointsystem.utlis.Constant;
import cn.bdqn.pointsystem.utlis.ReturnResult;
import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 拦截老师请求学生页面
 * @Classname StudentHandlerInterceptor
 * @Description TODO
 * @Date 2020-01-07 15:56
 * @Created by Stand
 */
public class StudentHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User currentUser = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
        if(currentUser.getIsAdmin() == 1){
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ReturnResult.returnFail("No access , Request exception:A-15879", null, 15879)));
            writer.flush();
            writer.close();
            return false;
        }
        return true;
    }
}
