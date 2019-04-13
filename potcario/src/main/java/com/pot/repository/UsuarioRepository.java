package com.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pot.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	public Usuario findByEmailAndPwd(String pEmail, String pPwd);
	
	public Usuario findByEmail(String pEmail);
}
