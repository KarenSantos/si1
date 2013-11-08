package si1.lab1;

import java.util.Scanner;

/**
 * Classe que usa um conversor numerico para colocar numeros em seu formato por
 * extenso.
 * 
 * @author Karen
 * @version 1.0
 * 
 */
public class ConversorNumerico {

	private static String NUMERO_INVALIDO = "Voce precisa inserir um número entre 0 e 1.000.000.000 (um bilhao).";

	/**
	 * Converte um numero dado como entrada pelo usuario para a sua forma por
	 * extenso e imprime na tela.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Conversor conversor = new Conversor();
		String entrada = "";
		int numero = -1;

		System.out
				.println("Bem-vindo ao conversor numérico! \nPara sair digite: exit \n");

		while (!entrada.equals("exit")) {

			while (entrada.equals("")) {

				System.out.print("Informe um número: ");

				entrada = sc.nextLine();

				if (entrada.equals("exit"))
					break;
				if (entrada.equals(""))
					continue;

				try {
					numero = Integer.valueOf(entrada);
				} catch (Exception e) {
					System.out.println(NUMERO_INVALIDO + "\n");
					entrada = "";
				}
			}

			if (entrada.equals("exit"))
				break;

			try {
				System.out.println(conversor.converter(numero));
			} catch (Exception e) {
				System.out.println(NUMERO_INVALIDO);
			} finally {
				System.out.println();
				entrada = "";
			}
		}

		sc.close();
		System.out.println("Até mais!");
	}
}
