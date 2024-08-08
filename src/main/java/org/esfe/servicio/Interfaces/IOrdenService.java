package org.esfe.servicio.Interfaces;

import org.esfe.models.OrdenKDSB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IOrdenService {
    Page<OrdenKDSB> buscarTodosPaginados(Pageable pageable);

    List<OrdenKDSB> obtenerTodos();

    Optional<OrdenKDSB> buscarPorId(Integer id);

    OrdenKDSB crearOEditar(OrdenKDSB orden);

    void eliminarPorId(Integer id);
}
