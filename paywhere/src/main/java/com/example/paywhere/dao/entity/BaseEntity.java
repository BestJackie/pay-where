package com.example.paywhere.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
/**
 * FileName: BaseEntity
 * Author:   admin
 * Date:     2021-07-29 10:07
 * Description:
 * History:
 * since: 1.0.0
 */
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends IdentifiableEntity<Long> {
    @JsonIgnore
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createTime;
    @JsonIgnore
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @LastModifiedDate
    private LocalDateTime modifyTime;

    @PreUpdate
    public void preUpdate() {
        modifyTime = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        if (createTime == null)
            createTime = now;
        modifyTime = now;
    }
}
