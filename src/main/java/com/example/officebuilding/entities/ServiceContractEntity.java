package com.example.officebuilding.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "service_contract")
public class ServiceContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String scDateBegin;

    @Column(nullable = false)
    private String scDateEnd;

    @Column(nullable = false)
    private double scPrice;

    @Column(nullable = false)
    private Integer scStatus;


    private Timestamp scTime;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        scTime = new Timestamp(System.currentTimeMillis());
    }

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

}
