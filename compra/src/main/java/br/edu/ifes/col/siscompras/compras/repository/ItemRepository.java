package br.edu.ifes.col.siscompras.compras.repository;

import br.edu.ifes.col.siscompras.compras.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>  {
}
