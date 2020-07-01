package it.dstech.formazione.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/addRicetta")
	public String addRicetta(Ricetta ricetta, Model mode) {
		ricetta.setCosto();
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

	@RequestMapping("/newDolce")
	public String newDolceForm(Model model) {
		Dolce dolce = new Dolce();
		model.addAttribute("dolce", dolce);
		return "nuovo-dolce";
	}

	@GetMapping("/addDolce")
	public String addDolce(Dolce dolce, Model mode) {
		dolce.setCosto();
		dolceService.add(dolce);
		return " ";

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

		dolce.setRicetta(ricetta);
		dolceService.edit(dolce);
		return " ";

	}

	@GetMapping("/scelta")
	public String scelta(@RequestParam("scelta") String scelta) {
		if ("admin".equalsIgnoreCase(scelta)) {
			return "admin";
		} else {
			return "cliente";
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
			return "nuova-ricetta";

		case 2:
			return "nuovo-dolce";

		}
		return "index";
	}

}
