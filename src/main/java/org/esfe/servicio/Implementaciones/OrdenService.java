package org.esfe.servicio.Implementaciones;

import org.esfe.models.OrdenKDSB;
import org.esfe.repositorios.IOrdenRepository;
import org.esfe.servicio.Interfaces.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService implements IOrdenService {
    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public Page<OrdenKDSB> buscarTodosPaginados(Pageable pageable) {
        return ordenRepository.findAll(pageable);
    }

    @Override
    public List<OrdenKDSB> obtenerTodos() {
        return ordenRepository.findAll();
    }

    @Override
    public Optional<OrdenKDSB> buscarPorId(Integer id) {
        return ordenRepository.findById(id);
    }

    @Override
    public OrdenKDSB crearOEditar(OrdenKDSB orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public void eliminarPorId(Integer id) {
        ordenRepository.deleteById(id);
    }

}
