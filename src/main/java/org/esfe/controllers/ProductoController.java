package org.esfe.controllers;


import org.esfe.models.ProductoKDSB;
import org.esfe.servicio.Interfaces.IProductoService;
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
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<ProductoKDSB> productos = productoService.buscarTodosPaginados(pageable);
        model.addAttribute("productos", productos);

        int totalPages = productos.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "producto/index";
    }

    // Mostrar el formulario para crear un nuevo producto
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("producto", new ProductoKDSB());
        return "producto/create";
    }

    // Procesar el formulario para guardar un nuevo producto
    @PostMapping("/save")
    public String save(@ModelAttribute ProductoKDSB producto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "producto/create";
        }
        productoService.crearOEditar(producto);
        attributes.addFlashAttribute("msg", "Producto guardado correctamente");
        return "redirect:/productos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        ProductoKDSB producto = productoService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        model.addAttribute("producto", producto);
        return "producto/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        ProductoKDSB producto = productoService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        model.addAttribute("producto", producto);
        return "producto/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Optional<ProductoKDSB> producto = productoService.buscarPorId(id);
        if (producto.isPresent()) {
            model.addAttribute("productoKDSB", producto.get());
            return "producto/delete";
        } else {
            return "redirect:/productos";
        }
    }

    @PostMapping("/delete")
    public String delete(ProductoKDSB productoKDSB, RedirectAttributes attributes) {
        productoService.eliminarPorId(productoKDSB.getId());
        attributes.addFlashAttribute("msg", "Producto eliminado correctamente");
        return "redirect:/productos";
    }
}
