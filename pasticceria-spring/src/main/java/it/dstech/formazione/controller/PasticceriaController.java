package it.dstech.formazione.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.dstech.formazione.models.Dolce;
import it.dstech.formazione.models.Ingrediente;
import it.dstech.formazione.models.Ordinazione;
import it.dstech.formazione.models.Ricetta;
import it.dstech.formazione.services.DolceServiceDAO;
import it.dstech.formazione.services.IngredienteServiceDAO;
import it.dstech.formazione.services.OrdinazioneServiceDAO;
import it.dstech.formazione.services.RicettaServiceDAO;

@Controller
public class PasticceriaController {

	@Autowired
	private IngredienteServiceDAO ingre;
	@Autowired
	private RicettaServiceDAO ricService;
	@Autowired
	private DolceServiceDAO dolceService;
	@Autowired
	private OrdinazioneServiceDAO ordinazioneService;

	@PostMapping("/addIngre")
	public String addIngrediente(Ingrediente ingrediente, Model mode) {
		ingre.add(ingrediente);
		mode.addAttribute("listaOrdinazioni",ordinazioneService.findByOrderByConsegnaAsc() );
		return "admin";

	}

	@PostMapping("/addRicetta")
	public String addRicetta(Ricetta ricetta, Model mode, @RequestParam(value = "ingrediente") long[] listIngredienti) {

		if (listIngredienti != null) {
			ricetta.setListaIngredienti(new ArrayList<>());
			for (int i = 0; i < listIngredienti.length; i++) {
				ricetta.getListaIngredienti().add(ingre.findById(listIngredienti[i]));
			}
			ricetta.trovaCosto();

		}

		ricService.add(ricetta);
		mode.addAttribute("listaOrdinazioni",ordinazioneService.findByOrderByConsegnaAsc() );

		return "admin";

	}

	
	@PostMapping("/addDolce")
	public String addDolce(Dolce dolce, Model mode, @RequestParam(value = "id") long id) {
		
		dolce.setRicetta(new ArrayList<Ricetta>());
		dolce.getRicetta().add(ricService.findById(id));
		dolce.trovaCosto();
		dolceService.add(dolce);
		mode.addAttribute("listaOrdinazioni",ordinazioneService.findByOrderByConsegnaAsc() );

		return "admin";

	}
	
	@GetMapping("/scelta")
	public String scelta(@RequestParam("scelta") String scelta,Model model) {
		if ("admin".equalsIgnoreCase(scelta)) {
			model.addAttribute("listaOrdinazioni",ordinazioneService.findByOrderByConsegnaAsc());
			return "admin";
		} else {
			return "loginRegistrazione";
		}
	}

	@GetMapping("/")
	public String home() {

		return "index";

	}
	@PostMapping("/consegna")
	public String consegna(@RequestParam("id") long id,Model model) {

		Ordinazione ordinazione=ordinazioneService.findById(id);
		ordinazione.setConsegna(LocalDateTime.now());
		ordinazioneService.edit(ordinazione);
		model.addAttribute("listaOrdinazioni",ordinazioneService.findByOrderByConsegnaAsc());		
		return "admin";

	}


	@PostMapping("/azioneAdmin")
	public String azione(@RequestParam("azione") int scelta, Model model) {

		switch (scelta) {
		case 0:
			Ingrediente ingrediente = new Ingrediente();
			model.addAttribute("ingrediente", ingrediente);
			return "nuovo-ingrediente";

		case 1:
			Ricetta ricetta = new Ricetta();
			model.addAttribute("ricetta", ricetta);
			model.addAttribute("listaIngredienti", ingre.findAll());
			return "nuova-ricetta";

		case 2:
			Dolce dolce = new Dolce();
			model.addAttribute("dolce", dolce);
			model.addAttribute("listaRicette", ricService.findAll());
			return "nuovo-dolce";

		}
		return "index";
	}

}
