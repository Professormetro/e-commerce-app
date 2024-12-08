package org.chernov.ecommerce.dto;


import java.util.Map;

public record ErrorResponse (
        Map<String, String> errors
){



}
