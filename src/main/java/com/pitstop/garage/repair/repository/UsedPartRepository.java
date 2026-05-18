package com.pitstop.garage.repair.repository;

import com.pitstop.garage.repair.model.ServiceRepair;
import com.pitstop.garage.repair.model.UsedPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsedPartRepository extends JpaRepository<UsedPart, UUID> {

    List<UsedPart> findAllByServiceRepair(ServiceRepair serviceRepair);
}
