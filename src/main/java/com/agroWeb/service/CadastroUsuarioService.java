package com.agroWeb.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Usuario;
import com.agroWeb.repository.UsuarioRepository;
import com.agroWeb.security.exception.EmailCadastradoException;
import com.agroWeb.security.exception.SenhaObrigatoriaUsuarioException;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroUsuarioService {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Usuario salvar(Usuario usuario){
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailIgnoreCase(usuario.getEmail());
		
		if (usuarioOptional.isPresent()){
			throw new EmailCadastradoException("Usuário com este email já foi cadastrado");
		}
		
		if(usuario.isNovo() && usuario.getSenha().isEmpty()){
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para um novo usuário");
		}
		
		if(usuario.isNovo()){
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		
		return usuarioRepository.saveAndFlush(usuario);
	}

	@Transactional
	public void alteraStatus(Long[] codigos, StatusUsuario statusUsuario) {
		
		statusUsuario.executar(codigos, usuarioRepository);
	}

	@Transactional
	public void excluir(Long id) {
		try{
			usuarioRepository.delete(id);
			usuarioRepository.flush();
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossivel excluir este usuario!");
		}
		
	}

	
}
