package com.example.paywhere.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * FileName: IdentifiableEntity
 * Author:   admin
 * Date:     2021-07-29 10:12
 * Description:
 * History:
 * since: 1.0.0
 */

@MappedSuperclass
public class IdentifiableEntity<ID extends Serializable> implements Serializable{

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private ID id;

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if (!this.getClass().isInstance(obj))
            return false;
        @SuppressWarnings("unchecked")
        IdentifiableEntity<ID> target = (IdentifiableEntity<ID>)obj;
        return Objects.equals(this.getId(), target.getId());
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.getId());
    }
}
