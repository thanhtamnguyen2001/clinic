package com.dev.clinic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name = "precription")
public class Prescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_id", nullable = false, referencedColumnName = "id")
    private Certificate certificate;

    // @JsonIgnore
    // @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    // @JoinTable(name = "prescription_detail", joinColumns = @JoinColumn(name =
    // "perscription_id"), inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    // private Set<Medicine> medicines = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prescription")
    private Set<PrescriptionMedicine> medicines = new HashSet<>();

    // public void addMedicine(Medicine medicine, int quanity) {
    // PrescriptionMedicine prescriptionMedicine = new
    // PrescriptionMedicine(medicine, this, quanity);

    // this.medicines.add(prescriptionMedicine);
    // // medicine.getPrescriptions().add(prescriptionMedicine);
    // }

    // public void removeMedicine(long medicineId) {
    // PrescriptionMedicine prescriptionMedicine = this.medicines.stream()
    // .filter(m -> m.getMedicine().getId() == medicineId).findFirst().orElse(null);
    // long aa = prescriptionMedicine.getMedicine().getId();
    // if (prescriptionMedicine != null) {
    // this.medicines.remove(prescriptionMedicine);

    // prescriptionMedicine.getMedicine().getPrescriptions().remove(prescriptionMedicine);
    // }
    // }

    public void addMedicine(Medicine medicine, int quanity) {
        PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine(medicine, this, quanity);
        this.medicines.add(prescriptionMedicine);
    }

    public PrescriptionMedicine getPrescriptionMedicine(long medicineId) {
        return this.medicines.stream()
                .filter(m -> m.getMedicine().getId() == medicineId).findFirst().orElse(null);
    }

    // public void removeMedicine(Medicine medicine) {
    // for (Iterator<PrescriptionMedicine> iterator = this.medicines.iterator();
    // iterator.hasNext();) {
    // PrescriptionMedicine prescriptionMedicine = iterator.next();

    // if (prescriptionMedicine.getPrescription().equals(this) &&
    // prescriptionMedicine.getMedicine().equals(medicine)) {
    // iterator.remove();
    // prescriptionMedicine.setPrescription(null);
    // prescriptionMedicine.setMedicine(null);
    // }
    // }
    // }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Prescription prescription = (Prescription) o;
        return Objects.equals(this.getId(), prescription.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

}
