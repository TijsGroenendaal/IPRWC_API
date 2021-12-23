package groenendaal.tijs.iprwc_api.helper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

public class RelationHelper {

    public static UUID getUserId() {
        List<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().toList();

        try {
            return UUID.fromString(authorities.get(0).toString());
        } catch (IllegalArgumentException e) {
            if (authorities.size() != 1) {
                return UUID.fromString(authorities.get(1).toString());
            } else {
                return null;
            }
        }
    }

}
