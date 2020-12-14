package com.cdmservicios.mantenimiento.shared;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface GenericServiceAPI<T, ID extends Serializable> {

    List<T> getAll();

    T getById (ID id);

    T save (T entity);

    void delete (ID id);

    JpaRepository<T, ID> getRepository();

}
