package br.edu.ifes.col.siscompras.compras.service;

import br.edu.ifes.col.siscompras.compras.domain.Compra;
import br.edu.ifes.col.siscompras.compras.repository.CompraRepository;
import br.edu.ifes.col.siscompras.compras.service.dto.CompraDTO;
import br.edu.ifes.col.siscompras.compras.service.dto.ItemCompraDTO;
import br.edu.ifes.col.siscompras.compras.service.exception.RegraNegocioException;
import br.edu.ifes.col.siscompras.compras.service.feign.EstoqueService;
import br.edu.ifes.col.siscompras.compras.service.feign.PagamentoService;
import br.edu.ifes.col.siscompras.compras.service.mapper.CompraMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompraService {

    private final PagamentoService pagamentoService;

    private final EstoqueService estoqueService;

    private final CompraMapper compraMapper;

    private final CompraRepository compraRepository;

    public List<CompraDTO> listar() {
        List<Compra> compras = compraRepository.findAll();
        return compraMapper.toDto(compras);
    }

    public CompraDTO comprar(CompraDTO compraDTO) {

        Float total = compraDTO.getItens().stream().reduce(0f, (subtotal, item) -> subtotal + (item.getQuantidade() * item.getValorUnitario()), Float::sum);
        String cartao = "123.456.789.000";

        reservarPagamento(cartao, total);

        decrementarEstoque(compraDTO.getItens());

        Random gerador = new Random();
        int val = gerador.nextInt(3);

        if(val == 0) {
            log.error("SISTEMA COMPRAS: Erro ao finalizar processamento de compra...");
            reverterPagamento(cartao, total);
            reverterEstoque(compraDTO.getItens());
            throw new RegraNegocioException("Erro ao finalizar compra");
        }

        Compra compra = compraMapper.toEntity(compraDTO);
        compraRepository.save(compra);

        despachar(compra);

        log.info("SISTEMA COMPRAS: Processamento de compra finalizado com sucesso...");

        return compraMapper.toDto(compra);
    }

    private void despachar(Compra compra) {
        System.out.println(compra.toString());
    }

    private void reservarPagamento(String cartao, Float total) {
        try {
            log.info("SISTEMA COMPRAS: Acessando serviço de pagamentos... solicitando reserva do saldo");
            pagamentoService.reservar(cartao, total);
        } catch (Exception e) {
            reverterPagamento(cartao, total);
            throw new RegraNegocioException("SISTEMA COMPRAS: Erro ao reservar pagamento");
        }
    }

    private void decrementarEstoque(List<ItemCompraDTO> itens) {
        try {
            log.info("SISTEMA COMPRAS: Acessando serviço de controle de estoque...");
            estoqueService.decrementar(itens);
        } catch (Exception e) {
            reverterEstoque(itens);
            throw new RegraNegocioException("SISTEMA COMPRAS: Erro ao decrementar estoque");
        }
    }


    private void reverterPagamento(String cartao, Float total) {
        pagamentoService.liberar(cartao, total);
    }

    private void reverterEstoque(List<ItemCompraDTO> itens) {
        estoqueService.incrementar(itens);
    }
}

