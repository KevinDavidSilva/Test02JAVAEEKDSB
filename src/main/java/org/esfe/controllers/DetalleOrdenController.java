package org.esfe.controllers;

import org.esfe.models.DetalleOrdenKDSB;
import org.esfe.models.OrdenKDSB;
import org.esfe.models.ProductoKDSB;
import org.esfe.servicio.Interfaces.IDetalleOrdenService;
import org.esfe.servicio.Interfaces.IOrdenService;
import org.esfe.servicio.Interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/detalleorden")
public class DetalleOrdenController {

    @Autowired
    private IDetalleOrdenService detalleOrdenService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IProductoService productoService;

    @GetMapping()
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<DetalleOrdenKDSB> detallesOrden = detalleOrdenService.buscarTodosPaginados(pageable);
        model.addAttribute("detallesOrden", detallesOrden);

        int totalPages = detallesOrden.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "detalleorden/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ordenes", ordenService.obtenerTodos());
        model.addAttribute("productos", productoService.obtenerTodos());
        return "detalleorden/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer ordenId, @RequestParam Integer productoId,
                       @RequestParam Integer cantidadKDSB, @RequestParam Double precioKDSB,
                       RedirectAttributes attributes) {
        OrdenKDSB orden = ordenService.buscarPorId(ordenId).orElse(null);
        ProductoKDSB producto = productoService.buscarPorId(productoId).orElse(null);

        if (orden != null && producto != null) {
            DetalleOrdenKDSB detalleOrden = new DetalleOrdenKDSB();
            detalleOrden.setOrden(orden);
            detalleOrden.setProducto(producto);
            detalleOrden.setCantidadKDSB(cantidadKDSB);
            detalleOrden.setPrecioKDSB(precioKDSB);

            detalleOrdenService.crearOEditar(detalleOrden);
            attributes.addFlashAttribute("msg", "Detalle guardado con éxito");
        } else {
            attributes.addFlashAttribute("error", "Error al guardar el detalle: orden o producto no encontrado");
        }

        return "redirect:/detalleorden";
    }


    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        DetalleOrdenKDSB detalleOrden = detalleOrdenService.buscarPorId(id);
        model.addAttribute("detalleOrden", detalleOrden);
        return "detalleorden/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        DetalleOrdenKDSB detalleOrden = detalleOrdenService.buscarPorId(id);
        model.addAttribute("ordenes", ordenService.obtenerTodos());
        model.addAttribute("productos", productoService.obtenerTodos());
        model.addAttribute("detalleOrden", detalleOrden);
        return "detalleorden/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam Integer ordenId, @RequestParam Integer productoId,
                         @RequestParam Integer cantidadKDSB, @RequestParam Double precioKDSB,
                         RedirectAttributes attributes) {
        OrdenKDSB orden = ordenService.buscarPorId(ordenId).get();
        ProductoKDSB producto = productoService.buscarPorId(productoId).get();

        if (orden != null && producto != null) {
            DetalleOrdenKDSB detalleOrden = new DetalleOrdenKDSB();
            detalleOrden.setId(id);
            detalleOrden.setOrden(orden);
            detalleOrden.setProducto(producto);
            detalleOrden.setCantidadKDSB(cantidadKDSB);
            detalleOrden.setPrecioKDSB(precioKDSB);

            detalleOrdenService.crearOEditar(detalleOrden);
            attributes.addFlashAttribute("msg", "Detalle de orden modificado correctamente");
        }

        return "redirect:/detalleorden";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        DetalleOrdenKDSB detalleOrden = detalleOrdenService.buscarPorId(id);
        model.addAttribute("detalleOrden", detalleOrden);
        return "detalleorden/delete";
    }

    @PostMapping("/delete")
    public String delete(DetalleOrdenKDSB detalleOrden, RedirectAttributes attributes) {
        detalleOrdenService.eliminarPorId(detalleOrden.getId());
        attributes.addFlashAttribute("msg", "Detalle de orden eliminado correctamente");
        return "redirect:/detalleorden";
    }
}
