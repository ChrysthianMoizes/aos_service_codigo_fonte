package br.edu.ifes.col.siscompras.compras.resource;

import br.edu.ifes.col.siscompras.compras.service.CompraService;
import br.edu.ifes.col.siscompras.compras.service.dto.CompraDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/compras")
@Slf4j
public class CompraResource {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraDTO> comprar(@RequestBody CompraDTO compra) {
        CompraDTO compraDTO = compraService.comprar(compra);
        return new ResponseEntity<>(compraDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompraDTO>> listar() {
        List<CompraDTO> compras = compraService.listar();
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

}
