package com.tienda.controller;

import com.tienda.entity.Proveedor;
import com.tienda.service.ProveedorService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/proveedor/lista";
    }

    @GetMapping("/lista")
    public String listarProveedores(Model model) {
        model.addAttribute("listaProveedores", proveedorService.listar());
        return "consultaProveedor";
    }

    @GetMapping("/editar/{id}")
    public String editarProveedor(@PathVariable("id") Integer id, Model model) {
        Proveedor prov = proveedorService.buscarPorId(id);
        model.addAttribute("proveedor", prov);
        return "mantenimientoProveedor";
    }

    @PostMapping("/actualizar")
    public String actualizarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorService.guardar(proveedor);
        return "redirect:/proveedor/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "añadirProveedor";
    }

    @PostMapping("/guardar")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorService.guardar(proveedor);
        return "redirect:/proveedor/lista";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable("id") Integer id) {
        proveedorService.eliminar(id);
        return "redirect:/proveedor/lista";
    }
    
    @GetMapping("/buscar")
    public String buscarPorRazonSocial(@RequestParam("razonSocial") String razonSocial, Model model) {
        List<Proveedor> resultados = proveedorService.buscarPorRazonSocial(razonSocial);
        model.addAttribute("listaProveedores", resultados);
        model.addAttribute("nombreBuscado", razonSocial);
        return "buscarProveedor";
    }


}