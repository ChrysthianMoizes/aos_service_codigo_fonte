package br.edu.ifes.col.siscompras.compras.service.mapper;


import br.edu.ifes.col.siscompras.compras.domain.Item;
import br.edu.ifes.col.siscompras.compras.service.dto.ItemCompraDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemCompraDTO, Item> {

    @Mapping(source = "compraId", target = "compra.id")
    Item toEntity(ItemCompraDTO dto);

    @InheritInverseConfiguration
    ItemCompraDTO toDto(Item entity);
}
