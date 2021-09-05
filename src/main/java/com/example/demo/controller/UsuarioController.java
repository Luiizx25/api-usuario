package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuario;
import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@PostMapping
	public Usuario salvarUsuario(@RequestBody Usuario usuario) {
		return repository.save(usuario);
	}

	@GetMapping
	public List<Usuario> listarUsuarios() {
		return repository.findAll();
	}

	@GetMapping("/{codigoUsuario}")
	public Object usuarioPorId(@PathVariable Integer codigoUsuario) {

		Optional<Usuario> usuario = repository.findById(codigoUsuario);

		try {
			if (usuario.isEmpty()) {
				throw new UsuarioNotFoundException("Usuario nao encontrado ):");
			}
		} catch (UsuarioNotFoundException e) {
			return e;
		}
		return usuario.get();
	}

	@DeleteMapping("/{codigoUsuario}")
	public void excluirUsuario(@PathVariable Integer codigoUsuario) {
		repository.deleteById(codigoUsuario);
	}

}
