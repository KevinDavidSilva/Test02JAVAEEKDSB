package org.esfe.servicio.Interfaces;

import org.esfe.models.ProductoKDSB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    Page<ProductoKDSB> buscarTodosPaginados(Pageable pageable);

    List<ProductoKDSB> obtenerTodos();

    Optional<ProductoKDSB> buscarPorId(Integer id);

    ProductoKDSB crearOEditar(ProductoKDSB producto);

    void eliminarPorId(Integer id);
}
