package io.gtihub.andresgois.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY) //, fetch = FetchType.EAGER) não recomendado, deixa consultas pesadas
    private Set<Pedido> pedidos;
    public Cliente(){}

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ClienteTeste{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
