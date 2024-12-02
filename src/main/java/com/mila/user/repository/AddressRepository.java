package com.mila.user.repository;

import com.mila.user.infrastructure.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    

}
