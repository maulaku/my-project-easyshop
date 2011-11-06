package br.com.easyShop.utils;

public class TipoEndereco {

	public static String getNomeTipo(int tipo) {

		if (tipo == Constantes.TIPO_RESIDENCIA) {
			return "Resid�ncia";
		} else if (tipo == Constantes.TIPO_COMERCIAL) {
			return "Comercial";
		} else if (tipo == Constantes.TIPO_APARTAMENTO) {
			return "Apartamento";
		}
		return "";
	}

	public static int getIndexTipoEndereco(String nome) {

		if (nome.equals(Constantes.RESIDENCIA)) {
			return Constantes.TIPO_RESIDENCIA;
		} else if (nome.equals(Constantes.COMERCIAL)) {
			return Constantes.TIPO_COMERCIAL;
		} else if (nome.equals(Constantes.APARTAMENTO)) {
			return Constantes.TIPO_APARTAMENTO;
		}

		return 0;
	}
	
	public static int posicaoCombo(String nome){
		
		if (nome.equals(Constantes.RESIDENCIA)) {
			return 0;
		} else if (nome.equals(Constantes.COMERCIAL)) {
			return 2;
		} else if (nome.equals(Constantes.APARTAMENTO)) {
			return 1;
		}

		return 0;
	}

}
