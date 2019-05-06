package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.common.Roles;
import framgia.co.edu.ftrr.service.RoleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    public RoleServiceImpl() {
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private Boolean hasRole(Integer role) {
        Set<String> roles = getAuthentication().getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        return roles.contains("ROLE_" + role);
    }

    @Override
    public Boolean isSm() {
        return hasRole(Roles.SM.getCode());
    }

    @Override
    public Boolean isDm() {
        return hasRole(Roles.DM.getCode());
    }

    @Override
    public Boolean isHr() {
        return hasRole(Roles.HR.getCode());
    }

    @Override
    public Boolean isEc() {
        return hasRole(Roles.EC.getCode());
    }

    @Override
    public Boolean isTrainer() {
        return hasRole(Roles.TRAINER.getCode());
    }

    @Override
    public Boolean isEmployee() {
        return hasRole(Roles.OTHER.getCode());
    }

}
