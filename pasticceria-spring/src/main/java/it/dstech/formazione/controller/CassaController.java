package it.dstech.formazione.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import it.dstech.formazione.models.Cliente;
import it.dstech.formazione.models.Dolce;
import it.dstech.formazione.models.Ordinazione;
import it.dstech.formazione.services.ClienteServiceDAO;
import it.dstech.formazione.services.OrdinazioneServiceDAO;

@Controller
public class CassaController {
	
	@Autowired
	private ClienteServiceDAO clienteService;
	@Autowired
	private OrdinazioneServiceDAO ordinazioneService;
	
     
	@GetMapping("/addCliente")
	public String addCliente(Cliente cliente,Model model) {
		
		clienteService.add(cliente);
		return  "";
		
	}
	@DeleteMapping("/deleteCliente")
	public String deleteCliente(Cliente cliente,Model model) {
		
		clienteService.remove(cliente);
		return  "";
		
	}
	@PutMapping("/editCliente")
	public String editCliente(Cliente cliente,Model model) {
		
		clienteService.edit(cliente);
		return  "";
		
	}
	
	@PutMapping("/aggiungiOrdinazione")
	public String aggiungiOrdinazioneCliente(Cliente cliente,Ordinazione ordinazione,Model model) {
		
		cliente.getListaOrdinazioni().add(ordinazione);
		clienteService.edit(cliente);
		return  "";
		
	}
	
	@GetMapping("/addOrdinazione")
	public String addOrdinazione(Ordinazione ordinazioneiente,Model model) {
		
		ordinazioneService.add(ordinazioneiente);
		return  "";
		
	}
	@DeleteMapping("/deleteOrdinazione")
	public String deleteOrdinazione(Ordinazione ordinazioneiente,Model model) {
		
		ordinazioneService.remove(ordinazioneiente);
		return  "";
		
	}
	@PutMapping("/editOrdinazione")
	public String editOrdinazione(Ordinazione ordinazioneiente,Model model) {
		
		ordinazioneService.edit(ordinazioneiente);
		return  "";
		
	}
	
	@PutMapping("/aggiungiDolce")
	public String aggiungiDolceOrdinazione(Ordinazione ordinazione, Dolce dolce,Model model) {
		
		ordinazione.getListaDolci().add(dolce);
		ordinazione.setCosto();
		ordinazioneService.edit(ordinazione);
		return  "";
		
	}
	
	

}
