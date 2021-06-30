package br.edu.ifes.col.siscompras.compras.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CompraDTO implements Serializable {

    private Integer id;

    private String entrega;

    private String pagamento;

    List<ItemCompraDTO> itens = new ArrayList<>();
}
