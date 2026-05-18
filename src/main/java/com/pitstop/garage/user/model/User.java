package com.pitstop.garage.user.model;

import com.pitstop.garage.car.model.Car;
import com.pitstop.garage.repair.model.ServiceRepair;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    @Builder.Default
    private boolean enabled = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Car> ownedCars = new ArrayList<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @Builder.Default
    private List<ServiceRepair> clientRepairs = new ArrayList<>();

    @OneToMany(mappedBy = "mechanic", fetch = FetchType.EAGER)
    @Builder.Default
    private List<ServiceRepair> mechanicRepairs = new ArrayList<>();

    @PrePersist
    void onCreate() {
        if (createdOn == null) {
            createdOn = LocalDateTime.now();
        }
    }
}
