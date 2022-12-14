package br.com.app_cadastro.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app_cadastro.domain.vo.v1.PessoaVO;
import br.com.app_cadastro.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Pessoa Endpoint")
@RestController
@RequestMapping("api/pessoa/v1")
public class PessoaController {

	@Autowired
	PessoaService service;

	@CrossOrigin("localhost:8080")//permitido o acesso
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@Operation(summary="Listar todas as pessoas")
	@ResponseStatus(value = HttpStatus.OK)
	public List<PessoaVO> findAll() {
		List<PessoaVO> pessoasVO = service.buscarTodos();
			pessoasVO.stream().forEach(p -> p.add(linkTo(methodOn(PessoaController.class).findById(p.getKey())).withSelfRel()));
		return pessoasVO;
	}

	@CrossOrigin({"localhost:8080",  })//permitido o acesso
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public PessoaVO findById(@PathVariable("id") Long id) {
		PessoaVO pessoaVO = service.buscarPorId(id);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).findById(id)).withSelfRel());
		return pessoaVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public PessoaVO create(@Valid @RequestBody PessoaVO pessoa) {
		PessoaVO pessoaVO = service.inserir(pessoa);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).findById(pessoaVO.getKey())).withSelfRel());
		return pessoaVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public PessoaVO update(@Valid @RequestBody PessoaVO pessoa) {
		PessoaVO pessoaVO = service.atualizar(pessoa);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).findById(pessoaVO.getKey())).withSelfRel());
		return pessoaVO;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
