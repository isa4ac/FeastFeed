package com.feastfeed.feastfeed;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class JUnitAnnotationExamples {
	
	@BeforeAll
	public static void setupEverything() {
		int i = 1+1;
	}
	
	@BeforeEach
	public void setupBeforeEachTest() {
		int i = 1+1;
	}
	
	@Test
	public void runTests() {
		double i = 1.0+1.0;
		assertEquals(2.0,i,0.0);
	}
	
	@Test
	public void runMoreTests() {
		int i = 1+1;
		assertEquals(2,i);
		Object o = null;
		assertNull(o);
		assertTrue(4 == 2+2);
	}
	
	@JsonIgnore
	@Test
	public void runFailTests() {
		fail();
	}
	
	@AfterAll
	public static void teardownEverything() {
		int i = 1+1;
	}
	
	@AfterEach
	public void teardownBeforeEachTest() {
		int i = 1+1;
	}
}
