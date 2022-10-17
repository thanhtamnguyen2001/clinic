package com.dev.clinic.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicine")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(nullable = false)
    private String name;

    @Size(max = 255)
    @Column
    private String note;

    @Column
    private Double price;

    @Column(name = "quantity_per_unit")
    private Integer quantityPerUnit;

    @JsonIgnore
    @Column
    private Integer quantity;

    @JsonIgnore
    @Column
    private Boolean actived = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id", nullable = false, referencedColumnName = "id")
    private Unit unit;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicine", fetch = FetchType.LAZY)
    private Set<PrescriptionMedicine> prescriptions = new HashSet<>();

}
