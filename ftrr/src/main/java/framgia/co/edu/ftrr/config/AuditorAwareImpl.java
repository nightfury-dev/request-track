package framgia.co.edu.ftrr.config;

import framgia.co.edu.ftrr.entity.User;
import framgia.co.edu.ftrr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get User from database which is authenticated by Spring Security
     * and it will use for auto updating(createBy, updateBy) when data be changed
     *
     * @return  optional current User
     */
    @Override
    public Optional<User> getCurrentAuditor() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

}
