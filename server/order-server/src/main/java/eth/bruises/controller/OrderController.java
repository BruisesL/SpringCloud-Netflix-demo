package eth.bruises.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import eth.bruises.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 无负载均衡的请求方法
     * @param id
     * @return
     */
//    @GetMapping("/{id}")
//    public User getUser(@PathVariable("id") Long id) {
//        return restTemplate.getForObject("http://localhost:10010/user/" + id, User.class);
//    }

    /**
     * 使用负载均衡后的请求方法
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getUserFallbackMethod") // 该接口需要监控是否需要熔断
    public User getUser(@PathVariable("id") Long id){
        log.info("进入正常方法");
        return restTemplate.getForObject("http://user-server/user/" + id, User.class);
    }


    public User getUserFallbackMethod(@PathVariable("id") Long id){
        log.info("进入熔断方法");
        return new User(-1L, "已熔断", "熔断");
    }
}
