package ec.com.banking.demo.account.mov.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clienteFeignClient", url = "${feign.client.url}")
public interface ClienteFeignClient {
    @GetMapping("/{nameClient}")
    Long getDataByNameClient(@PathVariable("nameClient") final String nameClient);
}
