package br.edu.ifes.col.siscompras.compras.repository;

import br.edu.ifes.col.siscompras.compras.domain.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>  {
}
