package br.com.cotinformatica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cotinformatica.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
	
	@Query("from Usuario u where u.email = :email")
	Usuario findByEmail(@Param("email") String email);
	
	@Query("from Usuario u where u.email = :email and u.senha = :senha")
	Usuario findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);

}
