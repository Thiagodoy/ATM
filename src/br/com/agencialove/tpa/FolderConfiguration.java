package br.com.agencialove.tpa;

import java.io.File;

public class FolderConfiguration {

	public static void config() {

		String currentDirectory =  System.getProperty("user.dir");
		File directorio = new File(currentDirectory + "/upload");

		if (!directorio.isDirectory()) {
			directorio.mkdir();
		}

		File embalagem = new File(directorio, "embalagem");
		File encomenda = new File(directorio, "encomenda");

		if (!embalagem.isDirectory()) {
			embalagem.mkdir();
		}

		if (!encomenda.isDirectory()) {
			encomenda.mkdir();
		}

	}

}
