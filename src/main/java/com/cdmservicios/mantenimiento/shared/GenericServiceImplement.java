package com.cdmservicios.mantenimiento.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericServiceImplement<T, ID extends Serializable> implements GenericServiceAPI<T, ID> {

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

    @Override
    public T getById(ID id) {
        // Optional<T> from java8
        Optional<T> optional = getRepository().findById(id);
        return optional.orElse(null);
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public abstract JpaRepository<T, ID> getRepository();

}
