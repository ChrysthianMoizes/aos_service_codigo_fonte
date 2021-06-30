package br.edu.ifes.col.siscompras.compras.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ItemCompraDTO implements Serializable {

    private Integer id;

    private Integer quantidade;

    private Float valorUnitario;

    private Integer compraId;
}
