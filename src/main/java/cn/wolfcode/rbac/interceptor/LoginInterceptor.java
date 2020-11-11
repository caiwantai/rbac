package cn.wolfcode.rbac.interceptor;

import cn.wolfcode.rbac.annotation.PermissionAllowed;
import cn.wolfcode.rbac.common.UserPermission;
import cn.wolfcode.rbac.common.utils.SessionUtil;
import cn.wolfcode.rbac.domain.Permission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserPermission currentSessionAttribute = SessionUtil.getCurrentSessionAttribute();
        if (Objects.isNull(currentSessionAttribute)){
            response.sendRedirect("/");
            return false;
        }
        // 默认处理器
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        if (currentSessionAttribute.getAdmin()){
            return true;
        }

        HandlerMethod method =(HandlerMethod) handler;
        PermissionAllowed permissionAllowed = method.getMethodAnnotation(PermissionAllowed.class);
        if (Objects.isNull(permissionAllowed)){
            return true;
        }
        List<Permission> permissions = currentSessionAttribute.getPermissions();
        String value = null;
        if (Objects.nonNull(permissions) && permissions.size()>0){
           value = permissionAllowed.value();
            for (Permission permission : permissions) {
                if (permission.getExpression().equals(value)){
                    return true;
                }
            }
        }
//        System.out.println("expression.split(\":\")[0] = " +
//                value.split(":")[0]);
        request.setAttribute("currentMenu",value.split(":")[0]);
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
