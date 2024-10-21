package ec.com.banking.demo.account.mov.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Setter
@Getter
@AllArgsConstructor
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private String message;
    private Object data;
    public BaseResponse(String message) {
        this.message = message;
    }

}
