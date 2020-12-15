package com.cdmservicios.mantenimiento.services.apis;

import com.cdmservicios.mantenimiento.models.Lubricante;
import com.cdmservicios.mantenimiento.shared.GenericServiceAPI;

public interface LubricanteServiceAPI extends GenericServiceAPI<Lubricante, String> {
    Lubricante findByEquipo(String code);
}
