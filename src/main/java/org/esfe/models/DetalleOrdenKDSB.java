package org.esfe.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "detalleorden")
public class DetalleOrdenKDSB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idOrdenKDSB", nullable = false)
    @NotNull(message = "La orden es requerida")
    private OrdenKDSB orden;

    @ManyToOne
    @JoinColumn(name = "idProductoKDBS", nullable = false)
    @NotNull(message = "El producto es requerido")
    private ProductoKDSB producto;

    @NotNull(message = "La cantidad es requerida")
    private Integer cantidadKDSB;

    @NotNull(message = "El precio es requerido")
    private Double precioKDSB;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
