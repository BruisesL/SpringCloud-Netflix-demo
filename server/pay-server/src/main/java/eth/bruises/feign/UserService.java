package eth.bruises.feign;

import eth.bruises.domain.User;
import eth.bruises.fallback.UserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 表示当前接口是一个feign接口，并且指明要调用的服务的服务名称
// contextId：代表你当前这个feign接口的唯一标识，此标识如果不加，那么如果调用同一个服务写了两个feign接口会报错
@FeignClient(value = "user-server", path = "user", contextId = "userService", fallbackFactory = UserServiceFallbackFactory.class)
public interface UserService {

    // feign接口方法的编写规范就是去复制你要调用的controller接口方法
    @GetMapping("/{id}")
    User getUser(@PathVariable("id") Long id);


}