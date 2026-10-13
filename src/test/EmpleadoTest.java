package test;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import s2b.Empleado;

class EmpleadoTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception 
	{
	}

	@BeforeEach
	void setUp() throws Exception 
	{
	}

	@AfterEach
	void tearDown() throws Exception 
	{
	}

	//Pruebas para CalculaNominaEmpleado
	@Test
	void testCalculaNominaEmpleado_TipoNull() 
	{
		// Condición: tipo == null -> return -1
		float resultado = Empleado.CalculaNominaEmpleado(null, 1500f, 10f);
		assertEquals(-1f, resultado);
	}

	@Test
	void testCalculaNominaEmpleado_VentasMenor1000() 
	{
		// Condición: ventaMes < 1000 -> return -1
		float resultado = Empleado.CalculaNominaEmpleado(Empleado.TipoEmpleado.Vendedor, 500f, 10f);
		assertEquals(-1f, resultado);
	}

	@Test
	void testCalculaNominaEmpleado_HorasExtraNegativas() 
	{
		// Condición: horasExtra < 0 -> return -1
		float resultado = Empleado.CalculaNominaEmpleado(Empleado.TipoEmpleado.Encargado, 1500f, -5f);
		assertEquals(-1f, resultado);
	}

	@Test
	void testCalculaNominaEmpleado_Encargado_VentasMedias() 
	{
		// Encargado (2000) + Ventas 1000 (+100) + 0 horas = 2100
		float resultado = Empleado.CalculaNominaEmpleado(Empleado.TipoEmpleado.Encargado, 1000f, 0f);
		assertEquals(2100f, resultado);
	}

	@Test
	void testCalculaNominaEmpleado_Encargado_VentasAltas() 
	{
		// Encargado (2000) + Ventas 1500 (+200) + 2 horas (+60) = 2260
		float resultado = Empleado.CalculaNominaEmpleado(Empleado.TipoEmpleado.Encargado, 1500f, 2f);
		assertEquals(2260f, resultado);
	}

	@Test
	void testCalculaNominaEmpleado_Vendedor_VentasMedias() 
	{
		// Vendedor (2500) + Ventas 1200 (+100) + 0 horas = 2600
		float resultado = Empleado.CalculaNominaEmpleado(Empleado.TipoEmpleado.Vendedor, 1200f, 0f);
		assertEquals(2600f, resultado);
	}

	@Test
	void testCalculaNominaEmpleado_Vendedor_VentasAltas() 
	{
		// Vendedor (2500) + Ventas 2000 (+200) + 3 horas (+90) = 2790
		float resultado = Empleado.CalculaNominaEmpleado(Empleado.TipoEmpleado.Vendedor, 2000f, 3f);
		assertEquals(2790f, resultado);
	}
	
	@Test
	void testCalculaNominaEmpleado_Vendedor_LimiteVentasMedias() 
	{
		// Vendedor (2500) + Ventas 1499 (+100) + 1 hora (+30) = 2630
		float resultado = Empleado.CalculaNominaEmpleado(Empleado.TipoEmpleado.Vendedor, 1499f, 1f);
		assertEquals(2630f, resultado);
	}

	//Pruebas para calculoNominaNeta
	@Test
	void testCalculoNominaNeta_Negativa() 
	{
		// Condición: nominaBruta < 0 -> return -1
		float resultado = Empleado.calculoNominaNeta(-100f);
		assertEquals(-1f, resultado);
	}

	@Test
	void testCalculoNominaNeta_Menor2100() 
	{
		// Condición: [0, 2100] -> Retención 0%
		// 2000 * 1 = 2000
		float resultado = Empleado.calculoNominaNeta(2000f);
		assertEquals(2000f, resultado);
	}

	@Test
	void testCalculoNominaNeta_Limite2100() 
	{
		// Valor frontera exacto: 2100 no es > 2100, así que retención es 0%
		float resultado = Empleado.calculoNominaNeta(2100f);
		assertEquals(2100f, resultado);
	}

	@Test
	void testCalculoNominaNeta_Entre2100y2500() 
	{
		// Condición: (2100, 2500] -> Retención 15%
		// 2300 * 0.85 = 1955
		float resultado = Empleado.calculoNominaNeta(2300f);
		assertEquals(1955f, resultado);
	}

	@Test
	void testCalculoNominaNeta_Mayor2500() 
	{
		// Condición: > 2500 -> Retención 18%
		// 2600 * 0.82 = 2132
		float resultado = Empleado.calculoNominaNeta(2600f);
		assertEquals(2132f, resultado);
	}
}
