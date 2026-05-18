package com.pitstop.garage.car.repository;

import com.pitstop.garage.car.model.Car;
import com.pitstop.garage.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

    List<Car> findAllByOwnerAndDeletedAtIsNull(User owner);

    List<Car> findAllByDeletedAtIsNull();

    Optional<Car> findByIdAndOwnerAndDeletedAtIsNull(UUID id, User owner);

    Optional<Car> findByIdAndDeletedAtIsNull(UUID id);

    boolean existsByVinAndDeletedAtIsNull(String vin);

    boolean existsByVinAndDeletedAtIsNullAndIdNot(String vin, UUID id);
}
