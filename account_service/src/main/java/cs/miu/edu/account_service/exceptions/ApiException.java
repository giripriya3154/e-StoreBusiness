package cs.miu.edu.account_service.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
public class ApiException extends  RuntimeException{
    private String error;
    public ApiException(String error){
        super(error);
    }
}
