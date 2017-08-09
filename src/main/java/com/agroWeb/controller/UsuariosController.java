package com.agroWeb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agroWeb.controller.page.PageWrapper;
import com.agroWeb.model.Usuario;
import com.agroWeb.repository.GrupoRepository;
import com.agroWeb.repository.UsuarioRepository;
import com.agroWeb.repository.filter.UsuarioFilter;
import com.agroWeb.security.exception.EmailCadastradoException;
import com.agroWeb.security.exception.SenhaObrigatoriaUsuarioException;
import com.agroWeb.service.CadastroUsuarioService;
import com.agroWeb.service.StatusUsuario;
import com.agroWeb.service.exception.ImpossivelExcluirUsuarioException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("grupos", grupoRepository.findAll());
		return mv;
	}

	@PostMapping({ "/novo", "{\\+d}" })
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(usuario);
		}

		try {
			cadastroUsuarioService.salvar(usuario);
		} catch (EmailCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		} catch (SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario);
		}

		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/usuarios/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter, @PageableDefault(size = 3) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/usuario/PesquisaUsuarios");
		mv.addObject("grupos", grupoRepository.findAll());

		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(usuarioRepository.filtrar(usuarioFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@PutMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos,
			@RequestParam("status") StatusUsuario statusUsuario) {
		cadastroUsuarioService.alterarStatus(codigos, statusUsuario);
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Usuario usuario = usuarioRepository.buscarComGrupos(codigo);
		ModelAndView mv = novo(usuario);
		mv.addObject(usuario);
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroUsuarioService.excluir(id);
		} catch (ImpossivelExcluirUsuarioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

}