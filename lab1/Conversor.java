package si1.lab1;

/**
 * Classe de conversor numerico para sua forma por extenso.
 * 
 * @author Karen
 * @version 1.0
 * 
 */
public class Conversor {

	private String[] unidade;
	private String[] dezena;
	private String[] dezenaEspecial;
	private String[] centena;

	private static int ZERO = 0;
	private static int DEZ = 10;
	private static int CEM = 100;
	private static int MIL = 1000;
	private static int UM_MILHAO = 1000000;
	private static int UM_BILHAO = 1000000000;

	// Construtor
	/**
	 * Cria um conversor para transformar inteiros em numeros por extenso.
	 */
	public Conversor() {

		unidade = new String[] { "zero", "um", "dois", "tres", "quatro",
				"cinco", "seis", "sete", "oito", "nove" };
		dezena = new String[] { "zero", "dez", "vinte", "trinta", "quarenta",
				"cinquenta", "sessenta", "setenta", "oitenta", "noventa" };
		dezenaEspecial = new String[] { "dez", "onze", "doze", "treze",
				"catorze", "quinze", "dezesseis", "dezessete", "dezoito",
				"dezenove" };
		centena = new String[] { "zero", "cento", "duzentos", "trezentos",
				"quatrocentos", "quinhentos", "seiscentos", "setecentos",
				"oitocentos", "novecentos" };
	}

	/**
	 * Converte um numero inteiro de zero a um bilhao para sua forma por
	 * extenso.
	 * 
	 * @param numero
	 *            - valor a ser convertido
	 * @return A forma por extenso do numero inserido
	 * @throws Exception
	 *             Se o numero é menor que zero ou maior que um bilhao.
	 * 
	 */
	public String converter(int numero) throws Exception {

		if (numero < ZERO || numero > UM_BILHAO) {
			throw new Exception("Numero invalido");
		}
		return caso1(numero);
	}

	/**
	 * Caso1 identifica se o numero é o extremo superior ou se é menor que mil,
	 * se não, passa o numero para o caso2.
	 * 
	 * @param numero
	 *            Valor a ser convertido.
	 * @return o numero convertido para sua forma por extenso.
	 */
	private String caso1(int numero) {
		String resposta = "";

		if (numero == UM_BILHAO) {
			resposta = "um bilhao";

		} else if (numero < MIL) {
			resposta = conversorCentenas(numero);

		} else {
			resposta = caso2(numero);
		}
		return resposta;
	}

	/**
	 * Caso2 identifica as centenas de milhoes, e se existirem milhares ou
	 * centenas passa o numero para o caso 3.
	 * 
	 * @param numero
	 *            Valor a ser convertido.
	 * @return o numero convertido para sua forma por extenso.
	 */
	private String caso2(int numero) {

		String resposta = "";

		if (numero >= UM_MILHAO) {
			resposta = conversorCentenas(numero / UM_MILHAO);
			if (resposta.equals("um")) { // tratando o caso do singular milhao
				resposta += " milhao";
			} else {
				resposta += " milhoes";
			}
		}
		resposta += conector(numero, resposta);

		numero = numero % UM_MILHAO; // tirando as casas dos milhoes do numero
		if (numero != ZERO) {
			resposta += caso3(numero);
		}
		return resposta;
	}

	/**
	 * Caso3 identifica as centenas de milhares e se existirem, as centenas,
	 * dezenas e unidades passa o numero para o conversor de centenas.
	 * 
	 * @param numero
	 *            Valor a ser convertido.
	 * @return O numero convertido para sua forma por extenso.
	 */
	private String caso3(int numero) {
		String resposta = "";

		if (numero >= MIL) {
			resposta = conversorCentenas(numero / MIL);
			if (resposta.equals("um")) { // tratando o caso de 'um mil'
				resposta = "mil";
			} else {
				resposta += " mil";
			}
		}
		resposta += conector(numero, resposta);

		numero = numero % MIL; // tirando as casas dos milhares do numero
		if (numero != ZERO) {
			resposta += conversorCentenas(numero);
		}
		return resposta;

	}

	/**
	 * Identifica as centenas e converte para sua forma por extenso, e se
	 * existirem dezenas, chama o metodo das dezenas.
	 * 
	 * @param numero
	 *            O valor a ser convertido.
	 * @return O numero convertido para sua forma por extenso.
	 */
	private String conversorCentenas(int numero) {
		String resp = "";

		if (numero < MIL) {
			if (numero == CEM) {
				resp = "cem";
			} else if (numero % CEM == ZERO) {
				resp = centena[numero / CEM];
			} else if (numero > CEM) {
				resp = centena[numero / CEM] + " e "
						+ conversorDezenas(numero % CEM);
			} else { // numero que nao tem centena passa direto
				resp = conversorDezenas(numero);
			}
		}
		return resp;
	}

	/**
	 * Identifica as dezenas e converte para sua forma por extenso, e se
	 * existirem unidades, chama o metodo das unidades.
	 * 
	 * @param numero
	 *            O valor a ser convertido.
	 * @return O numero convertido para sua forma por extenso.
	 */
	private String conversorDezenas(int numero) {
		String resp = "";

		if (numero % DEZ == ZERO) {
			resp = dezena[numero / DEZ];
		} else if (numero > DEZ && numero < 20) { // caso de dezenas especial
			resp = dezenaEspecial[(numero % DEZ)];
		} else if (numero > 20) {
			resp = dezena[numero / DEZ] + " e "
					+ conversorUnidades(numero % DEZ);
		} else { // numero que nao tem dezena passa direto
			resp = conversorUnidades(numero);
		}
		return resp;
	}

	/**
	 * Identifica unidades e converte para sua forma por extenso.
	 * 
	 * @param numero
	 *            O valor a ser convertido.
	 * @return O numero convertido para sua forma por extenso.
	 */
	private String conversorUnidades(int numero) {
		return unidade[numero];
	}

	/**
	 * Identifica o conector adequado, "," ou "e" para a forma por extenso do
	 * numero.
	 * 
	 * @param numero
	 *            O numero que deve ser convertido para sua forma por extenso.
	 * @param s
	 *            A string contendo a parte inicial do numero por extenso.
	 * @return O conector adequado para o numero a ser convertido.
	 */
	private String conector(int numero, String s) {
		String conect = "";

		if (!s.equals("")) { // se o string do numero ainda esta vazio nao
								// precisa de conector
			if (numero >= UM_MILHAO) {
				numero = numero % UM_MILHAO;

				if (numero != ZERO) {
					if (numero % MIL == ZERO) {
						conect += " e ";
					} else {
						conect += ", ";
					}
				}
			} else {
				if (numero % MIL != ZERO) {
					conect += " e ";
				}
			}
		}
		return conect;
	}
}
