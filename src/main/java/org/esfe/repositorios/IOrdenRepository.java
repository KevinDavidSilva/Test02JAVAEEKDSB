package org.esfe.repositorios;

import org.esfe.models.OrdenKDSB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenRepository extends JpaRepository<OrdenKDSB, Integer> {
}