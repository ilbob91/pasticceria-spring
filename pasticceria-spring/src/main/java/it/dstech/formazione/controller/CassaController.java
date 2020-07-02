package it.dstech.formazione.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.dstech.formazione.models.Cliente;
import it.dstech.formazione.models.Dolce;
import it.dstech.formazione.models.Ordinazione;
import it.dstech.formazione.services.ClienteServiceDAO;
import it.dstech.formazione.services.DolceServiceDAO;
import it.dstech.formazione.services.OrdinazioneServiceDAO;

@Controller
public class CassaController {

	@Autowired
	private ClienteServiceDAO clienteService;
	@Autowired
	private OrdinazioneServiceDAO ordinazioneService;
	@Autowired
	private DolceServiceDAO dolceService;

	@DeleteMapping("/deleteCliente")
	public String deleteCliente(Cliente cliente, Model model) {

		clienteService.remove(cliente);
		return "";

	}

	@PutMapping("/editCliente")
	public String editCliente(Cliente cliente, Model model) {

		clienteService.edit(cliente);
		return "";

	}

	@PutMapping("/aggiungiOrdinazione")
	public String aggiungiOrdinazioneCliente(Cliente cliente, Ordinazione ordinazione, Model model) {

		cliente.getListaOrdinazioni().add(ordinazione);
		clienteService.edit(cliente);
		return "";

	}

	@PostMapping("/addOrdinazione")
	public String addOrdinazione(Ordinazione ordinazioneiente, Model model) {

		ordinazioneService.add(ordinazioneiente);
		return "";

	}

	@DeleteMapping("/deleteOrdinazione")
	public String deleteOrdinazione(Ordinazione ordinazioneiente, Model model) {

		ordinazioneService.remove(ordinazioneiente);
		return "";

	}

	@PutMapping("/editOrdinazione")
	public String editOrdinazione(Ordinazione ordinazioneiente, Model model) {

		ordinazioneService.edit(ordinazioneiente);
		return "";

	}

	@PutMapping("/aggiungiDolce")
	public String aggiungiDolceOrdinazione(Ordinazione ordinazione, Dolce dolce, Model model) {

		ordinazione.getListaDolci().add(dolce);
		ordinazione.setCosto();
		ordinazioneService.edit(ordinazione);
		return "";

	}

	@PostMapping("/azioneAccesso")
	public String azione(@RequestParam("azione") int scelta, @RequestParam("cognome") String cognome,
			@RequestParam("nome") String nome, Model model) {
		Cliente cliente = clienteService.findByCognomeAndNome(cognome, nome);
		switch (scelta) {

		case 0:
			if (cliente != null) {
				model.addAttribute(cliente);
				return "cliente";
			}
			return "loginRegistrazione";

		case 1:

			if (cliente != null) {
				return "loginRegistrazione";
			}
			Cliente nuovoCliente = new Cliente();
			nuovoCliente.setCognome(cognome);
			nuovoCliente.setNome(nome);
			clienteService.add(nuovoCliente);
			model.addAttribute(nuovoCliente);
			return "cliente";
		}
		return "index";

	}

	@PostMapping("/azioneCliente")
	public String azione(@RequestParam("cliente") String id, Model model) {
		Ordinazione ordinazione = new Ordinazione();
		model.addAttribute("cliente", clienteService.findById(Long.parseLong(id)));
		model.addAttribute("listaDolci", dolceService.findAll());
		model.addAttribute("ordinazione", ordinazione);
		return "nuova-ordinazione";

	}
}
