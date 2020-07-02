package it.dstech.formazione.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.dstech.formazione.models.Dolce;
import it.dstech.formazione.models.Ingrediente;
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
		mode.addAttribute("ordinazioni", ordinazioneService.findAll());
		return "admin";

	}

	@PostMapping("/addRicetta")
	public String addRicetta(Ricetta ricetta, Model mode, @RequestParam(value = "ingrediente") long[] listIngredienti) {

		if (listIngredienti != null) {
			ricetta.setListaIngredienti(new ArrayList<>());
			for (int i = 0; i < listIngredienti.length; i++) {
				ricetta.getListaIngredienti().add(ingre.findById(listIngredienti[i]));
			}
		}

		ricetta.trovaCosto();
		ricService.add(ricetta);
		return "admin";

	}

	@PutMapping("/aggiungiIngrediente")
	public String aggiungiIngrediente(Ricetta ricetta, Ingrediente ingrediente, Model mode) {

		ricetta.getListaIngredienti().add(ingrediente);
		ricService.edit(ricetta);
		return " ";

	}

	@PutMapping("/editRicetta")
	public String editRicetta(Ricetta ricetta, Model mode) {

		ricService.edit(ricetta);
		return " ";

	}

	@DeleteMapping("deleteRicetta")
	public String deleteRicetta(Ricetta ricetta, Model mode) {

		ricService.remove(ricetta);
		return " ";

	}

	@PostMapping("/addDolce")
	public String addDolce(Dolce dolce, Model mode, @RequestParam(value = "id") long id) {
		System.out.println(id);
		dolce.getRicetta().add(ricService.findById(id));
		dolce.trovaCosto();
		dolceService.add(dolce);
		return "admin ";

	}

	@DeleteMapping("deleteDolce")
	public String deleteDolce(Dolce dolce, Model mode) {

		dolceService.remove(dolce);
		return " ";

	}

	@PutMapping("/editDolce")
	public String editDolce(Dolce dolce, Model mode) {

		dolceService.edit(dolce);
		return " ";

	}

	@PutMapping("/aggiungiRicetta")
	public String aggiungiRicettaDolce(Dolce dolce, Ricetta ricetta, Model mode) {

		dolceService.edit(dolce);
		return " ";

	}

	@GetMapping("/scelta")
	public String scelta(@RequestParam("scelta") String scelta) {
		if ("admin".equalsIgnoreCase(scelta)) {
			return "admin";
		} else {
			return "loginRegistrazione";
		}
	}

	@GetMapping("/")
	public String home() {

		return "index";

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
