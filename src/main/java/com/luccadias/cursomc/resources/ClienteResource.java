package com.luccadias.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luccadias.cursomc.domain.Cliente;
import com.luccadias.cursomc.dto.ClienteDTO;
import com.luccadias.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	ClienteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id){
		
		Cliente obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ClienteDTO objDto) {
		
		Cliente obj = service.formDTO(objDto);
		service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder
											.fromCurrentRequest()
											.path("/{id}")
											.buildAndExpand(obj.getId())
											.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		
		Cliente obj = service.formDTO(objDto);
		obj.setId(id);
		
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
