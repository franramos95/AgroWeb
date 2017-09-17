package com.agroWeb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Produto;
import com.agroWeb.repository.ProdutoRepository;
import com.agroWeb.service.exception.NomeProdutoJaCadastradoException;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public Produto salva(Produto produto) {

		Optional<Produto> produtoOptional = produtoRepository.findByNomeIgnoreCase(produto.getNome());

		if (produtoOptional.isPresent() && produto.isNova()) {
			throw new NomeProdutoJaCadastradoException("Produto já cadastrada");
		}
		return produtoRepository.saveAndFlush(produto);
	}

	public void excluir(Long id) {
		try {
			produtoRepository.delete(id);
			produtoRepository.flush();
		} catch (Exception e) {
			throw e;
		}
	}
}
