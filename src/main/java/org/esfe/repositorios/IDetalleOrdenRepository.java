package org.esfe.repositorios;

import org.esfe.models.DetalleOrdenKDSB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrdenKDSB, Integer>{
    Page<DetalleOrdenKDSB> findByOrderByDesc(Pageable pageable);
}
