package khai.edu.epos_re.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class UserAccountPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
        return hasPermission(authentication, targetType, permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if ((authentication == null) || (targetType== null) || !(permission instanceof  String)){
            return false;
        }
        return hasPermission(authentication, targetType.toUpperCase(), permission.toString().toUpperCase());
    }

    private boolean hasPermission(Authentication authentication, String targetType, String permission) {
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()
        ) {
            if (grantedAuthority.getAuthority().startsWith(targetType) &&
                    grantedAuthority.getAuthority().contains(permission)) {
                return true;
            }
        }
        return false;
    }
}
