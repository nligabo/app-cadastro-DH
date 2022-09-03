package br.com.app_cadastro.adapter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.app_cadastro.domain.entity.Pessoa;
import br.com.app_cadastro.domain.vo.v1.PessoaVO;


public class DozerConverterTest {

	//MOCK = criar um objeto com a informação que vai ser testada
	
	private Pessoa mockEntity(Long numero) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(0l);
		pessoa.setNome("Nome Teste"+numero);
		pessoa.setSobrenome("Sobrenome Teste"+numero);
		pessoa.setNomeSocial("NomeSocial Teste"+numero);
		pessoa.setEmail("Email Teste"+numero);
		
		return pessoa;
	}
	
	private List<Pessoa> mockEntityList() {
	
		List<Pessoa> pessoas = new ArrayList<>();
		
		for(Long i = 0l; i < 5; i++) {
			pessoas.add(mockEntity(i));
		}
		return pessoas;
	}
	
	private PessoaVO mockEntityVO(Long numero) {
		PessoaVO pessoa = new PessoaVO();
		pessoa.setKey(0l);
		pessoa.setNome("Nome Teste"+numero);
		pessoa.setSobrenome("Sobrenome Teste"+numero);
		pessoa.setNomeSocial("NomeSocial Teste"+numero);
		pessoa.setEmail("Email Teste"+numero);
		
		return pessoa;
	}
	
	private List<PessoaVO> mockEntityListVO() {
	
		List<PessoaVO> pessoas = new ArrayList<>();
		
		for(Long i = 0l; i < 5; i++) {
			pessoas.add(mockEntityVO(i));
		}
		return pessoas;
	}
	
	//casos de test
	
	@Test
	public void parseEntityToVOListTest() {
		List<PessoaVO> outputList = DozerConverter.parseListObject(mockEntityList(), PessoaVO.class);
		
		PessoaVO outputZero = outputList.get(0);
		Assert.assertEquals(Long.valueOf(0l), outputZero.getKey());
		Assert.assertEquals("Nome Teste0", outputZero.getNome());
		Assert.assertEquals("Sobrenome Teste0", outputZero.getSobrenome());
		Assert.assertEquals("NomeSocial Teste0", outputZero.getNomeSocial());
		Assert.assertEquals("Email Teste0", outputZero.getEmail());
		
		
		
		
		
		
		
	}
	
}
