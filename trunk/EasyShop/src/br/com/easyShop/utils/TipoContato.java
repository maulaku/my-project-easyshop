package br.com.easyShop.utils;

public class TipoContato {

	public static String getNomeTipo(int tipo) {

		if (tipo == Constantes.TIPO_CONTATO_EMAIL) {
			return Constantes.CONTATO_EMAIL;
		} else if (tipo == Constantes.TIPO_CONTATO_TELEFONE) {
			return Constantes.CONTATO_TELEFONE;
		} else if (tipo == Constantes.TIPO_CONTATO_CELULAR) {
			return Constantes.CONTATO_CELULAR;
		} else if (tipo == Constantes.TIPO_CONTATO_FAX) {
			return Constantes.CONTATO_FAX;
		}
		return null;
	}

	public static int getIndexTipoContato(String nome) {

		if (nome.equals(Constantes.CONTATO_EMAIL)) {
			return Constantes.TIPO_CONTATO_EMAIL;
		} else if (nome.equals(Constantes.CONTATO_TELEFONE)) {
			return Constantes.TIPO_CONTATO_TELEFONE;
		} else if (nome.equals(Constantes.CONTATO_CELULAR)) {
			return Constantes.TIPO_CONTATO_CELULAR;
		} else if (nome.equals(Constantes.CONTATO_FAX)) {
			return Constantes.TIPO_CONTATO_FAX;
		}
		return 0;
	}

}
