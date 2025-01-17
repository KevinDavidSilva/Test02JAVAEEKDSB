package org.esfe.servicio.Implementaciones;

import org.esfe.models.ProductoKDSB;
import org.esfe.repositorios.IProductoRepository;
import org.esfe.servicio.Interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public Page<ProductoKDSB> buscarTodosPaginados(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public List<ProductoKDSB> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<ProductoKDSB> buscarPorId(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public ProductoKDSB crearOEditar(ProductoKDSB producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoRepository.deleteById(id);
    }
}
