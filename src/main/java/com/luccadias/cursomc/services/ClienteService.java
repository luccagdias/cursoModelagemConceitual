package com.luccadias.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luccadias.cursomc.domain.Cliente;
import com.luccadias.cursomc.domain.enums.TipoCliente;
import com.luccadias.cursomc.dto.ClienteDTO;
import com.luccadias.cursomc.repositories.ClienteRepository;
import com.luccadias.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() 
				-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		
		return repo.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		find(obj.getId());
		
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		repo.deleteById(id);
	}
	
	public Cliente formDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCpfCnpj(), TipoCliente.PESSOAFISICA);
	}
}
