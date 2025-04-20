package edu.comillas.icai.gitt.pat.spring.p5.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO#7
 * Añade 2 tests unitarios adicionales que validen diferentes casos
 * (no variaciones del mismo caso) de registro con datos inválidos
 */

class RegisterRequestUnitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidRequest() {
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                Role.USER, "aaaaaaA1");
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registro);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "correo_invalido",
                Role.USER, "aaaaaaA1");
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registro);
        assertEquals(1, violations.size());
    }

    @Test
    public void testWeakPassword() {
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                Role.USER, "aaaaaaa"); // no cumple requisitos
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registro);
        assertEquals(1, violations.size());
    }


}