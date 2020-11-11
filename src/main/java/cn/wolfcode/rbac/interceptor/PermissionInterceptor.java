package cn.wolfcode.rbac.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 默认处理器
//        if (!(handler instanceof HandlerMethod)){
//            return true;
//        }
//        if (currentSessionAttribute.getAdmin()){
//            return true;
//        }
//
//        HandlerMethod method =(HandlerMethod) handler;
//        PermissionAllowed permissionAllowed = method.getMethodAnnotation(PermissionAllowed.class);
//        if (Objects.isNull(permissionAllowed)){
//            return true;
//        }
//        List<Permission> permissions = currentSessionAttribute.getPermissions();
//        String value = null;
//        if (Objects.nonNull(permissions) && permissions.size()>0){
//            value = permissionAllowed.value();
//            for (Permission permission : permissions) {
//                if (permission.getExpression().equals(value)){
//                    return true;
//                }
//            }
//        }
////        System.out.println("expression.split(\":\")[0] = " +
////                value.split(":")[0]);
//        request.setAttribute("currentMenu",value.split(":")[0]);
//        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request,response);
//        return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
