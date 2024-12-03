package com.mila.user.repository;

import com.mila.user.infrastructure.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone,Long> {

}
