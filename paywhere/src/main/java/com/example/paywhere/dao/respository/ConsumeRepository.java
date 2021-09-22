package com.example.paywhere.dao.respository;

import com.example.paywhere.dao.entity.ConsumeRecord;
import com.example.paywhere.dao.entity.IdentifiableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FileName: ConsumeRepository
 *
 * @author: admin
 * Date:     2021-09-17 13:51
 * Since: 1.0.0
 */
public interface ConsumeRepository extends JpaRepository<ConsumeRecord, IdentifiableEntity<Long>> {
}
