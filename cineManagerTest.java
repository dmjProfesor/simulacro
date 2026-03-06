package controladores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;

class CineManagerTest {

	CineManager cine;

	@BeforeEach
	void preparacion() {
		cine = new CineManager(100);
	}

	@Test
	void testPrecioMiercoles() {
		String dia = new String("miercoles");
		assertEquals(5.0,cine.calcularPrecioBase(dia, 30), "El miércoles debería costar 5 euros");
	}
	@Test
	void testJoven() {
		String dia = new String("lunes");
		assertEquals(0.0,cine.calcularPrecioBase(dia, 5), "A los menores a 10 años debería costar 0 euros");
		String dia2 = new String("miercoles");
		assertEquals(0.0,cine.calcularPrecioBase(dia2, 5), "A los menores a 10 años debería costar 0 euros");
	}
	@Test
	void testJubilado() {
		String dia = new String("lunes");
		assertEquals(6.0,cine.calcularPrecioBase(dia, 70), "A los mayores de 65 años debería costar 6 euros");
		String dia2 = new String("miercoles");
		assertEquals(5.0,cine.calcularPrecioBase(dia2, 70), "A los mayores de 65 años debería costar 5 euros los miercoles");
	}
	@Test
	void testJubiladoLimite() {
		String dia = new String("lunes");
		assertEquals(6.0,cine.calcularPrecioBase(dia, 65), "A los jubilados de 65 años debería costar 6 euros");
	}
	
	
	//se podría probar con edades negativas---No estaba contemplado dentro de los bugs indicados
	@Disabled
	@Test
	void testEdadNegativa() {
		String dia = new String("lunes");
		assertEquals(6.0,cine.calcularPrecioBase(dia, -65), "¿Qué ocurre si es edad imposible?");
	}
	
	@Test
	void testDescuentoJoven() {
		assertEquals(0.0,cine.aplicarDescuentos(0.0,false,false),0.01, "Joven NO estudiante NI socio será 0");
		assertEquals(0.0,cine.aplicarDescuentos(0.0,false,true), 0.01,"Joven NO estudiante pero SI socio será 0");
		assertEquals(0.0,cine.aplicarDescuentos(0.0,true,false), 0.01,"Joven  estudiante pero NO socio será 0");
		assertEquals(0.0,cine.aplicarDescuentos(0.0,true,true), 0.01,"Joven  estudiante Y socio será 0");
	}
	@Test
	void testDescuentoNormal() {
		assertEquals(10.0,cine.aplicarDescuentos(10.0,false,false),0.01, "normal NO estudiante NI socio será 10");
		assertEquals(8.0,cine.aplicarDescuentos(10.0,false,true), 0.01,"normal NO estudiante pero SI socio será 8");
		assertEquals(9.0,cine.aplicarDescuentos(10.0,true,false), 0.01,"normal  estudiante pero NO socio será 9");
		assertEquals(8.0,cine.aplicarDescuentos(10.0,true,true), 0.01,"normal  estudiante Y socio será 8");
	}
	@Test
	void testDescuentoJubilado() {
		assertEquals(6.0,cine.aplicarDescuentos(6.0,false,false),0.01, "jubilado NO estudiante NI socio será 6");
		assertEquals(4.8,cine.aplicarDescuentos(6.0,false,true), 0.01, "jubilado NO estudiante pero SI socio será 4.8");
		assertEquals(5.4,cine.aplicarDescuentos(6.0,true,false),0.01, "jubilado  estudiante pero NO socio será 5.4");
		assertEquals(4.8,cine.aplicarDescuentos(6.0,true,true), 0.01, "jubilado  estudiante Y socio será 4.8");
	}
	@Test
	void testDescuentoMiercoles() {
		assertEquals(5.0,cine.aplicarDescuentos(5.0,false,false),0.01, "en miércoles NO estudiante NI socio será 5");
		assertEquals(4.0,cine.aplicarDescuentos(5.0,false,true), 0.01,"en miércoles NO estudiante pero SI socio será 4");
		assertEquals(4.5,cine.aplicarDescuentos(5.0,true,false), 0.01,"en miércoles  estudiante pero NO socio será 4.5");
		assertEquals(4.0,cine.aplicarDescuentos(5.0,true,true), 0.01,"en miércoles  estudiante Y socio será 4");
	}
	
	@Test
	void reservarButacasMas() {
		
		assertFalse(cine.reservarButacas(120));
		assertEquals(100,cine.getAsientosDisponibles(),"No se deberían haber reservado ninguna butaca");
	}
	
	@Test
	void reservarButacas() {
		assertTrue(cine.reservarButacas(50));
		assertEquals(50,cine.getAsientosDisponibles(),"Se deberían haber reservado 50 butacas");
		assertTrue(cine.reservarButacas(50));
		assertEquals(0,cine.getAsientosDisponibles(),"Se deberían haber reservado todas las butacas");
	}
	
	

}
