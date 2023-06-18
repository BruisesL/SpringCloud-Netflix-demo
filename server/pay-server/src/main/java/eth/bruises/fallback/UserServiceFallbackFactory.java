package eth.bruises.fallback;

import eth.bruises.domain.User;
import eth.bruises.feign.UserService;
import feign.hystrix.FallbackFactory;

public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public User getUser(Long id) {
                return new User(-1L, "已熔断", "触发托底方法");
            }
        };
    }
}
