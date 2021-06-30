package br.edu.ifes.col.siscompras.compras.service.mapper;


import br.edu.ifes.col.siscompras.compras.domain.Compra;
import br.edu.ifes.col.siscompras.compras.service.dto.CompraDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface CompraMapper extends EntityMapper<CompraDTO, Compra> {

    @AfterMapping
    default void atualizarItens(@MappingTarget Compra compra) {
        compra.getItens().forEach(item -> item.setCompra(compra));
    }
}
