package io.gtihub.andresgois.domain.repository;

import io.gtihub.andresgois.domain.entity.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClientesRepository extends JpaRepository<Cliente, Integer> {

    // Query method
    List<Cliente> findByNomeLike(String nome);
    List<Cliente> findByNomeOrId(String nome, Integer id);
    Cliente findOneByNome(String nome);
    boolean existsByNome(String nome);

    List<Cliente> findAll();

    List<Cliente> findAll(Example example);
    // query
    @Query(value = " select c from Cliente c where c.nome like :nome ")
    List<Cliente> buscaPorNome(@Param("nome") String nome);

    // query nativa
    @Query(value = " select * from Cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> buscaPorNomeNoBanco(@Param("nome") String nome);

    // Para modificadores: delete e update
    @Query(value = " delete from Cliente c where c.nome = :nome ")
    @Modifying
    void deleteByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")// trás os clientes tendo ou não pedidos
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
