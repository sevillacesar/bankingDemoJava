package ec.com.banking.neo.account.client.responses;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Setter
@Getter
public class BaseResponse {
    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }

}
