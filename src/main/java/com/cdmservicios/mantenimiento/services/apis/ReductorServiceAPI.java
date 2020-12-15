package com.cdmservicios.mantenimiento.services.apis;

import com.cdmservicios.mantenimiento.models.Reductor;
import com.cdmservicios.mantenimiento.shared.GenericServiceAPI;

public interface ReductorServiceAPI extends GenericServiceAPI<Reductor, String> {
    Reductor findByEquipo(String code);
}
