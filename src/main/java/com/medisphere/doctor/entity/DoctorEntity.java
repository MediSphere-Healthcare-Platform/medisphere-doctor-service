package com.medisphere.doctor.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Entity
@Table(name = "doctor_table")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dr_id", nullable = false)
    private Integer id;

    @Column(name = "dr_name", nullable = false, length = 200)
    private String drName;

    @Column(name = "specialty", nullable = false, length = 50)
    private String specialty;

    @Column(name = "dr_licence", nullable = false, length = 800)
    private String drLicence;

    @Column(name = "dr_contact_no", length = 15)
    private String drContactNo;

    @Column(name = "dr_nic", nullable = false, length = 20)
    private String drNic;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @CreationTimestamp
    @Column(name = "create_date")
    private Instant createDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private Instant modifiedDate;

}