package com.dev.clinic.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "prescription_medicine")
public class PrescriptionMedicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantity;

    @JsonIgnore
    @JoinColumn(name = "prescription_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Prescription prescription;

    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Medicine medicine;

    public PrescriptionMedicine(Medicine medicine, Prescription prescription, int quantity) {
        this.medicine = medicine;
        this.prescription = prescription;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;
        PrescriptionMedicine that = (PrescriptionMedicine) obj;
        return Objects.equals(this.prescription.getId(), that.prescription.getId())
                && Objects.equals(this.medicine.getId(), that.medicine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescription.getId(), medicine.getId());
    }

    @Override
    public String toString() {
        return "com.ntt.model.PrescriptionMedicine[ medicine_id=" + medicine.getId() + " ]";
    }
}
