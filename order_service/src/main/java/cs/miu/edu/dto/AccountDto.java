package cs.miu.edu.dto;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
    private Integer accountId;
    private String firstName;
    private String lastName;
    private String emailAddress;


}
