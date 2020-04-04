/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.repository
 * @FileName: AvailableSlotRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:52:56 pm
 */

package com.calendario.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calendario.user.entities.AvailableSlot;

public interface AvailableSlotRepository extends JpaRepository<AvailableSlot, UUID> {

}
