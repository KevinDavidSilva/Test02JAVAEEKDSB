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


    @ManyToMany
    @JoinTable(
            name = "detalleorden",
            joinColumns = @JoinColumn(name = "id_productokdbs"),
            inverseJoinColumns = @JoinColumn(name = "id_ordenkdsb")
    )
    private Set<OrdenKDSB> orden = new HashSet<>();

//    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<DetalleOrdenKDSB> detallesOrden = new HashSet<>();

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreKDSB() {
        return nombreKDSB;
    }

    public void setNombreKDSB(String nombreKDSB) {
        this.nombreKDSB = nombreKDSB;
    }


}
