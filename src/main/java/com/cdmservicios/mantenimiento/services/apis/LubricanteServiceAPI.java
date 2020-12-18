package com.cdmservicios.mantenimiento.services.apis;

import com.cdmservicios.mantenimiento.models.Lubricante;
import com.cdmservicios.mantenimiento.shared.GenericServiceAPI;

import java.util.List;

public interface LubricanteServiceAPI extends GenericServiceAPI<Lubricante, String> {
    List<Lubricante> findByEquipo(String code);
}
