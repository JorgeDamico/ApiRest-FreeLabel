package com.freeLabel.backend.pruebas.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	Calculadora calc;
	
	@BeforeAll
	public static void primero() {
		System.out.println("Primero");
	}
	
	@AfterAll
	public static void ultimo() {
		System.out.println("Ultimo");
	}
	
	@BeforeEach
	public void instancia() {
		calc = new Calculadora();
		System.out.println("antes");
	}
	
	@AfterEach
	public void despues() {
		calc = new Calculadora();
		System.out.println("despues");
	}
	
	@Test
	@DisplayName("Pruebas a Arrays")
	public void ArrayTest() {
		
		String [] a1 = {"Jorge","Damico"};
		String [] a2 = {"Jorge","Damico"};
		
		assertArrayEquals(a1,a2);
		System.out.println("Test 1");
		
	}
	
	@Test
	@Disabled
	public void calcTest() {
		
		
		//assertEquals(2, calc.sumar(1, 7));
		
		assertFalse(calc.verdadFalsa());
		System.out.println("Test 2");
		
	}
	
	@Test
	public void trueTest() {
		
		assertTrue(calc.exp());
		System.out.println("Test 3");
		
	}
	
	@Test
	public void trueCalcTest() {
		
		assertTrue(calc.sumar(1, 1) == 2);
		System.out.println("Test 4");
		
	}

}
