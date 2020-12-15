package com.cdmservicios.mantenimiento.services.apis;

import com.cdmservicios.mantenimiento.models.Registro;
import com.cdmservicios.mantenimiento.shared.GenericServiceAPI;

import java.util.List;

public interface RegistroServiceAPI extends GenericServiceAPI<Registro, String> {
    List<Registro> findAllByEquipo(String code);
}
