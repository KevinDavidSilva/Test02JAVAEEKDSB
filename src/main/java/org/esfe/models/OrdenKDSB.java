package org.esfe.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate; 
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ordenes")
public class OrdenKDSB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha es requerida")
    private LocalDate fecha;

    @OneToMany(mappedBy = "orden")
    private Set<DetalleOrdenKDSB> detallesOrden = new HashSet<>();

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Set<DetalleOrdenKDSB> getDetallesOrden() {
        return detallesOrden;
    }

    public void setDetallesOrden(Set<DetalleOrdenKDSB> detallesOrden) {
        this.detallesOrden = detallesOrden;
    }
}
