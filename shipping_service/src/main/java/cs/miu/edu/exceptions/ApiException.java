package cs.miu.edu.exceptions;

import lombok.AllArgsConstructor;
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
