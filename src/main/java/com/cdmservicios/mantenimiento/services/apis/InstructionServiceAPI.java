package com.cdmservicios.mantenimiento.services.apis;

import com.cdmservicios.mantenimiento.models.Instruction;
import com.cdmservicios.mantenimiento.shared.GenericServiceAPI;

public interface InstructionServiceAPI extends GenericServiceAPI<Instruction, String> {
    Instruction findByEquipo(String code);
}
