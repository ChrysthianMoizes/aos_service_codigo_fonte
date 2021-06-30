package br.edu.ifes.col.siscompras.compras.resource;

import br.edu.ifes.col.siscompras.compras.service.CompraService;
import br.edu.ifes.col.siscompras.compras.service.dto.CompraDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "CompraResource", description = "Resource responsável pelo gerenciamento de compras")
public class CompraResource {

    private final CompraService compraService;

    @PostMapping
    @Operation(description = "Criação de compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra realizada"),
            @ApiResponse(responseCode = "400", description = "Compra não autorizada")
    })
    public ResponseEntity<CompraDTO> comprar(@RequestBody CompraDTO compra) {
        CompraDTO compraDTO = compraService.comprar(compra);
        return new ResponseEntity<>(compraDTO, HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Listagem de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem completa")
    })
    public ResponseEntity<List<CompraDTO>> listar() {
        List<CompraDTO> compras = compraService.listar();
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

}
