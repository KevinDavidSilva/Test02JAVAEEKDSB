package org.esfe.controllers;
import org.esfe.models.OrdenKDSB;
import org.esfe.servicio.Interfaces.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/ordenes")
public class OrdenController {
    @Autowired
    private IOrdenService ordenService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<OrdenKDSB> ordenes = ordenService.buscarTodosPaginados(pageable);
        model.addAttribute("ordenes", ordenes);

        int totalPages = ordenes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "orden/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("orden", new OrdenKDSB()); // Asegúrate de que este nombre coincida con el que usas en Thymeleaf
        return "orden/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute OrdenKDSB orden, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "orden/create";
        }
        ordenService.crearOEditar(orden);
        attributes.addFlashAttribute("msg", "Orden guardada correctamente");
        return "redirect:/ordenes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Optional<OrdenKDSB> orden = ordenService.buscarPorId(id);
        if (orden.isPresent()) {
            model.addAttribute("orden", orden.get());
            return "orden/details";
        } else {
            return "redirect:/ordenes";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Optional<OrdenKDSB> orden = ordenService.buscarPorId(id);
        if (orden.isPresent()) {
            model.addAttribute("orden", orden.get());
            return "orden/edit";
        } else {
            return "redirect:/ordenes";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Optional<OrdenKDSB> orden = ordenService.buscarPorId(id);
        if (orden.isPresent()) {
            model.addAttribute("orden", orden.get());
            return "orden/delete";
        } else {
            return "redirect:/ordenes";
        }
    }

    @PostMapping("/delete")
    public String delete(OrdenKDSB orden, RedirectAttributes attributes) {
        ordenService.eliminarPorId(orden.getId());
        attributes.addFlashAttribute("msg", "Orden eliminada correctamente");
        return "redirect:/ordenes";
    }
}
