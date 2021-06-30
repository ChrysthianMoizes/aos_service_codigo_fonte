package br.edu.ifes.col.siscompras.compras.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_compra")
    @SequenceGenerator(name = "seq_compra", allocationSize = 1, sequenceName = "seq_compra")
    @Column(name = "id")
    private Integer id;

    @Column(name = "pagamento")
    private String pagamento;

    @Column(name = "entrega")
    private String entrega;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", pagamento='" + pagamento + '\'' +
                ", entrega='" + entrega + '\'' +
                ", itens=" + itens.toString() +
                '}';
    }
}
