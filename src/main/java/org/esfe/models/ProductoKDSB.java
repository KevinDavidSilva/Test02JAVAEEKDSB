package org.esfe.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
public class ProductoKDSB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    private String nombreKDSB;

    private Set<DetalleOrdenKDSB> detallesOrden = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombreKDSB() {
        return nombreKDSB;
    }

    public void setNombreKDSB(String nombreMABT) {
        this.nombreKDSB = nombreKDSB;
    }

    public Set<DetalleOrdenKDSB> getDetallesOrden() {
        return detallesOrden;
    }

    public void setDetallesOrden(Set<DetalleOrdenKDSB> detallesOrden) {
        this.detallesOrden = detallesOrden;
    }
}
