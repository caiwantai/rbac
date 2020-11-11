package cn.wolfcode.rbac.common.utils;

import cn.wolfcode.rbac.common.UserPermission;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class SessionUtil {
    public static final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";
    public static HttpSession getSession(){
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
    }

    public static void setCurrentSessionAttribute(UserPermission userPermission){
        getSession().setAttribute(EMPLOYEE_IN_SESSION,userPermission);
    }

    public static UserPermission getCurrentSessionAttribute(){
        return (UserPermission) getSession().getAttribute(EMPLOYEE_IN_SESSION);
    }

    public static void removeSession(){
        getSession().invalidate();
    }
}
