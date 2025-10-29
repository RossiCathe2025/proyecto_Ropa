package com.tienda.controller;

import com.tienda.entity.Producto;
import com.tienda.service.ProductoService;
import com.tienda.service.ProveedorService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final ProveedorService proveedorService;

    public ProductoController(ProductoService productoService, ProveedorService proveedorService) {
        this.productoService = productoService;
        this.proveedorService = proveedorService;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/producto/lista";
    }

    @GetMapping("/lista")
    public String listarProductos(Model model) {
        model.addAttribute("listaProductos", productoService.listar());
        return "consultaProducto";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("producto", productoService.buscarPorId(id));
        model.addAttribute("proveedores", proveedorService.listar());
        return "mantenimientoProducto";
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/producto/lista";
    }
    
    @GetMapping("/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("proveedores", proveedorService.listar());
        return "añadirProducto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/producto/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id) {
        productoService.eliminar(id);
        return "redirect:/producto/lista";
    }
    
    @GetMapping("/buscar")
    public String buscarPorNombre(@RequestParam("nombre") String nombre, Model model) {
        List<Producto> resultados = productoService.buscarPorNombre(nombre);
        model.addAttribute("listaProductos", resultados);
        model.addAttribute("nombreBuscado", nombre);
        return "buscarProducto";
    }

}
