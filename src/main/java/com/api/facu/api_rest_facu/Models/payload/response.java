package com.api.facu.api_rest_facu.Models.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder

public class response implements Serializable {

    private String mensaje;
    private Object objecto;
}
