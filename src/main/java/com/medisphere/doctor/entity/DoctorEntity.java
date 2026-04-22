package com.medisphere.doctor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "medisphere_doctor")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Size(max = 100)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Size(max = 50)
    @NotNull
    @Column(name = "doctor_id", nullable = false, length = 50)
    private String doctorId;

    @Size(max = 50)
    @NotNull
    @Column(name = "ms_user_id", nullable = false, length = 50)
    private String msUserId;

    @Size(max = 50)
    @NotNull
    @Column(name = "specialty", nullable = false, length = 50)
    private String specialty;

    @Size(max = 800)
    @NotNull
    @Column(name = "dr_licence", nullable = false, length = 800)
    private String drLicence;

    @Size(max = 15)
    @Column(name = "dr_contact_no", length = 15)
    private String drContactNo;

    @Size(max = 20)
    @NotNull
    @Column(name = "dr_nic", nullable = false, length = 20)
    private String drNic;

    @Size(max = 20)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "profile_pic", length = 500)
    private String profilePic;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_date")
    private Instant createDate;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "modified_date")
    private Instant modifiedDate;

}