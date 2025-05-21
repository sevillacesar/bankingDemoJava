package ec.com.banking.demo.account.mov.mapper;

import ec.com.banking.demo.account.mov.dtos.AccountDto;
import ec.com.banking.demo.account.mov.dtos.AccountReqDto;
import ec.com.banking.demo.account.mov.models.Account;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account accountReqDtoToAccount(AccountReqDto accountReqDto);

    AccountDto accountToAccountDto(Account account);

    Account accountDtoToAccount(AccountDto accountDto);

    List<AccountDto> accountsToAccountDtos(Iterable<Account> accounts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    @Mapping(target = "clientId", ignore = true)
    void updateAccountFromDto(AccountReqDto accountReqDto, @MappingTarget Account account);

}
