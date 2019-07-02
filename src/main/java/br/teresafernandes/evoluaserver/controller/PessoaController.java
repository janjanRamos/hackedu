/**
 * Data: 13 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teresafernandes.evoluaserver.dominio.Pessoa;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.repo.PessoaRepository;
import br.teresafernandes.evoluaserver.repo.SetorRepository;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
@RestController
@RequestMapping({ "/pessoa" })
public class PessoaController extends AbstractController<Pessoa>{

	@Autowired
	SetorRepository setorRepository;
	
	/**
	 * @param repository
	 */
	PessoaController(PessoaRepository repository) {
		super(repository);
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Pessoa obj) {
		try {
			obj.validar();
			if(obj.getSetor() != null 
					&& !setorRepository.existsById(obj.getSetor().getId())) {
				throw new ServiceBusinessException("Setor inválido");
			}
		}catch (ServiceBusinessException e) {
		    return new ResponseEntity<Object>(e, e.getStatus());
		}
		
		return ResponseEntity.ok().body(repository.save(obj));
	}
}
