package ec.com.banking.demo.account.mov.mapper;

import ec.com.banking.demo.account.mov.dtos.MovementDto;
import ec.com.banking.demo.account.mov.models.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovementMapper {
    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    MovementDto movement2MovementDto(Movement movement);

    Movement movementDto2Movement(MovementDto movementDto);

    List<MovementDto> movementList2MovementDto(List<Movement> movementList);
}
