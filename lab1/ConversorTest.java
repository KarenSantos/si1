package si1.lab1;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ConversorTest {

	Conversor conv;

	@Before
	public void setUp() throws Exception {
		conv = new Conversor();
	}

	@Test
	public void testUnidades() throws Exception {
		assertEquals(conv.converter(1), "um");
		assertEquals(conv.converter(5), "cinco");
		assertEquals(conv.converter(7), "sete");
		assertEquals(conv.converter(9), "nove");
	}

	@Test
	public void testDezenas() throws Exception {
		assertEquals(conv.converter(10), "dez");
		assertEquals(conv.converter(30), "trinta");
		assertEquals(conv.converter(90), "noventa");
	}

	@Test
	public void testDezenasEspecial() throws Exception {
		assertEquals(conv.converter(11), "onze");
		assertEquals(conv.converter(15), "quinze");
		assertEquals(conv.converter(17), "dezessete");
		assertEquals(conv.converter(19), "dezenove");
	}

	@Test
	public void testDezenasEUnidades() throws Exception {
		assertEquals(conv.converter(27), "vinte e sete");
		assertEquals(conv.converter(33), "trinta e tres");
		assertEquals(conv.converter(72), "setenta e dois");
	}

	@Test
	public void testCentenas() throws Exception {
		assertEquals(conv.converter(100), "cem");
		assertEquals(conv.converter(700), "setecentos");
		assertEquals(conv.converter(456), "quatrocentos e cinquenta e seis");
		assertEquals(conv.converter(234), "duzentos e trinta e quatro");
		assertEquals(conv.converter(999), "novecentos e noventa e nove");
	}

	@Test
	public void testMilhares() throws Exception {
		assertEquals(conv.converter(1000), "mil");
		assertEquals(conv.converter(1076),
				"mil e setenta e seis");
		assertEquals(conv.converter(9852),
				"nove mil e oitocentos e cinquenta e dois");
		assertEquals(conv.converter(23944),
				"vinte e tres mil e novecentos e quarenta e quatro");
		assertEquals(conv.converter(752136),
				"setecentos e cinquenta e dois mil e cento e trinta e seis");
		assertEquals(conv.converter(100000), "cem mil");
	}
	
	@Test
	public void testMilhoes() throws Exception{
		assertEquals(conv.converter(1000000), "um milhao");
		assertEquals(conv.converter(132000000),
				"cento e trinta e dois milhoes");
		assertEquals(conv.converter(497525000),
				"quatrocentos e noventa e sete milhoes e quinhentos e vinte e cinco mil");
		assertEquals(conv.converter(781324900),
				"setecentos e oitenta e um milhoes, trezentos e vinte e quatro mil e novecentos");
		assertEquals(conv.converter(1002010), "um milhao, dois mil e dez");
	}
	
	@Test
	public void testExtremos() throws Exception{

		assertEquals(conv.converter(0), "zero");
		assertEquals(conv.converter(1000000000), "um bilhao");
		
		try {
			conv.converter(-1);
			fail("Deveria ter lancado excecao de numero invalido");
		} catch (Exception e){
			assertEquals(e.getMessage(), "Numero invalido");
		}
		
		try {
			conv.converter(-52);
			fail("Deveria ter lancado excecao de numero invalido");
		} catch (Exception e){
			assertEquals(e.getMessage(), "Numero invalido");
		}
		
		try {
			conv.converter(1000000001);
			fail("Deveria ter lancado excecao de numero invalido");
		} catch (Exception e){
			assertEquals(e.getMessage(), "Numero invalido");
		}
		
		try {
			conv.converter(1500000000);
			fail("Deveria ter lancado excecao de numero invalido");
		} catch (Exception e){
			assertEquals(e.getMessage(), "Numero invalido");
		}
		
	}

}
