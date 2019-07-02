/**
 * Data: 13 de jun de 2019
 */
package br.teresafernandes.evoluaserver.dominio;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.util.ValidatorUtil;

/**
 * @author Teresa Fernandes
 *
 */
@Entity
public class Pessoa implements EntidadePersistente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="id_setor", nullable=false)
	private Setor setor;
	
	@Column(name="cargo", nullable=false)
	private String cargo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@JsonProperty("setor")
	public void getGestor(Map<String,Object> mapGestor) {
		this.setor = null;
		if(mapGestor.containsKey("id")) {
			this.setor = new Setor();
	        this.setor.setId(((Integer)mapGestor.get("id")).longValue());
		}
    }
	
	public void validar() throws ServiceBusinessException {
		if(ValidatorUtil.isEmpty(nome)) {
			throw new ServiceBusinessException("Nome: campo obrigatório.");
		}
		if(ValidatorUtil.isEmpty(setor)) {
			throw new ServiceBusinessException("Setor: campo obrigatório.");
		}
	}

}