package it.dstech.formazione.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(CassaController.class);

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

	@PostMapping("/addOrdinazione")
	public String addOrdinazione(Ordinazione ordinazione, Model model, @RequestParam("idCliente") String id,
			@RequestParam(value = "dolce") long[] listDolci) {

		if (listDolci != null) {
			ordinazione.setListaDolci(new ArrayList<>());
			for (int i = 0; i < listDolci.length; i++) {
				Dolce dolce = dolceService.findById(listDolci[i]);
				ordinazione.getListaDolci().add(dolce);
				dolce.setQuantita(dolce.getQuantita() - 1);
				dolceService.edit(dolce);
			}
			ordinazione.trovaCosto();
		}
		Cliente cliente = clienteService.findById(Long.parseLong(id));
		ordinazione.setCliente(new ArrayList<Cliente>());
		ordinazione.getCliente().add(cliente);
		ordinazioneService.add(ordinazione);

		cliente.getListaOrdinazioni().add(ordinazione);

		clienteService.add(cliente);
		model.addAttribute("listaOrdinazioni", cliente.getListaOrdinazioni());
		model.addAttribute("cliente", cliente);
		LOGGER.info("Creata nuova ordinazione");
		return "cliente";

	}

	@PostMapping("/azioneAccesso")
	public String azione(@RequestParam("azione") int scelta, @RequestParam("cognome") String cognome,
			@RequestParam("nome") String nome, Model model) {
		Cliente cliente = clienteService.findByCognomeAndNome(cognome, nome);
		switch (scelta) {

		case 0:
			if (cliente != null) {
				model.addAttribute("listaOrdinazioni", cliente.getListaOrdinazioni());
				model.addAttribute(cliente);
				LOGGER.info("Accesso effettuato all'applicazione");
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
			LOGGER.info("Nuovo utente creato");
			return "cliente";
		}
		return "index";

	}

	@PostMapping("/azioneCliente")
	public String azione(@RequestParam("cliente") long id, Model model) {
		Ordinazione ordinazione = new Ordinazione();

		model.addAttribute("cliente", id);
		model.addAttribute("listaDolci", dolceService.findMaggioreZero());
		model.addAttribute("ordinazione", ordinazione);
		return "nuova-ordinazione";

	}

	@PostMapping("/viewDolci")
	public String viewDolc(@RequestParam("id") long id, @RequestParam("idCliente") long idCliente, Model model) {
		Ordinazione ordinazione = ordinazioneService.findById(id);
		List<Dolce> lista = ordinazione.getListaDolci();
		model.addAttribute("listaDolci", lista);
		model.addAttribute("idCliente", idCliente);
		return "view-dolci";
	}

	@GetMapping("/indietro")
	public String indietro(@RequestParam("idCliente") long id, Model model) {
		Cliente cliente = clienteService.findById(id);
		model.addAttribute("listaOrdinazioni", cliente.getListaOrdinazioni());
		model.addAttribute("cliente", cliente);
		return "cliente";
	}

}
