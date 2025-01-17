package org.esfe.models;

import jakarta.persistence.*;

@Entity
@Table(name = "detalleorden")
public class DetalleOrdenKDSB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_ordenkdsb", nullable = false)
    private OrdenKDSB orden;

    @ManyToOne
    @JoinColumn(name = "id_productokdbs", nullable = false)
    private ProductoKDSB producto;

    private Integer cantidadKDSB;
    private Double precioKDSB;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrdenKDSB getOrden() {
        return orden;
    }

    public void setOrden(OrdenKDSB orden) {
        this.orden = orden;
    }

    public ProductoKDSB getProducto() {
        return producto;
    }

    public void setProducto(ProductoKDSB producto) {
        this.producto = producto;
    }

    public Integer getCantidadKDSB() {
        return cantidadKDSB;
    }

    public void setCantidadKDSB(Integer cantidadKDSB) {
        this.cantidadKDSB = cantidadKDSB;
    }

    public Double getPrecioKDSB() {
        return precioKDSB;
    }

    public void setPrecioKDSB(Double precioKDSB) {
        this.precioKDSB = precioKDSB;
    }
}
