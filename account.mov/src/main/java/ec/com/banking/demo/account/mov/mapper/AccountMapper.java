package ec.com.banking.demo.account.mov.mapper;

import ec.com.banking.demo.account.mov.dtos.AccountDto;
import ec.com.banking.demo.account.mov.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto accountToAccountDto(Account account);

    Account accountDtoToAccount(AccountDto accountDto);

    List<AccountDto> accountsToAccountDtos(Iterable<Account> accounts);
}
