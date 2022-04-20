package com.jc.paywhere.dao.respository;

import com.jc.paywhere.dao.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: BaseRepository
 *
 * @author: admin
 * Date:     2021-09-23 13:48
 * Since: 1.0.0
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,ID extends Serializable> extends JpaRepository<T,ID> {
    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.deleted = false")
    List<T> findAll();

    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1 and e.deleted = false")
    Iterable<T> findAll(Iterable<ID> ids);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.deleted = false")
    T getById(ID id);

    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.deleted = false")
    long count();

    @Transactional(readOnly = true)
    default boolean exists(ID id) {
        return getById(id) != null;
    }

    @Query("update #{#entityName} e set e.deleted = true where e.id = ?1")
    @Transactional
    @Modifying
    void logicDelete(ID id);

    @Transactional
    default void logicDelete(T entity) {
        logicDelete((ID) entity.getId());
    }

    @Transactional
    default void logicDelete(Iterable<? extends T> entities) {
        entities.forEach(entity -> logicDelete((ID) entity.getId()));
    }

    @Query("update #{#entityName} e set e.deleted = true ")
    @Transactional
    @Modifying
    void logicDeleteAll();
}
