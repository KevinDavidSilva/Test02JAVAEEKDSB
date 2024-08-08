package org.esfe.servicio.Interfaces;

import org.esfe.models.DetalleOrdenKDSB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDetalleOrdenService {
    List<DetalleOrdenKDSB> obtenerTodos();

    Page<DetalleOrdenKDSB> buscarTodosPaginados(Pageable pageable);

    DetalleOrdenKDSB buscarPorId(Integer id);

    DetalleOrdenKDSB crearOEditar(DetalleOrdenKDSB detalleOrden);

    void eliminarPorId(Integer id);

}
