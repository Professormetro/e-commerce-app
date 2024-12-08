package org.chernov.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.chernov.ecommerce.entity.Address;

@Builder
public record CustomerResponse (
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
){
}
