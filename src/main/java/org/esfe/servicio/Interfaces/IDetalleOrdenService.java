package org.esfe.servicio.Interfaces;

import org.esfe.models.DetalleOrdenKDSB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDetalleOrdenService {
    Page<DetalleOrdenKDSB> buscarTodosPaginados(Pageable pageable);

    List<DetalleOrdenKDSB> obtenerTodos();

    Optional<DetalleOrdenKDSB> buscarPorId(Integer id);

    DetalleOrdenKDSB crearOEditar(DetalleOrdenKDSB detalleorden);

    void eliminarPorId(Integer id);

}
