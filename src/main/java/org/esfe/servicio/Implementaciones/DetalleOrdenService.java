package org.esfe.servicio.Implementaciones;

import org.esfe.models.DetalleOrdenKDSB;
import org.esfe.repositorios.IDetalleOrdenRepository;
import org.esfe.servicio.Interfaces.IDetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleOrdenService implements IDetalleOrdenService {
    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public List<DetalleOrdenKDSB> obtenerTodos() {
        return detalleOrdenRepository.findAll();
    }

    @Override
    public Page<DetalleOrdenKDSB> buscarTodosPaginados(Pageable pageable) {
        return detalleOrdenRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public DetalleOrdenKDSB buscarPorId(Integer id){
        return detalleOrdenRepository.findById(id).orElse(null);
    }

    @Override
    public DetalleOrdenKDSB crearOEditar(DetalleOrdenKDSB detalleorden) {
        return detalleOrdenRepository.save(detalleorden);
    }

    @Override
    public void eliminarPorId(Integer id) {
        detalleOrdenRepository.deleteById(id);
    }
}
