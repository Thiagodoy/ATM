package br.com.agencialove.tpa.ftp;

import java.io.File;
import java.io.FileNotFoundException;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class ClientFtpTest {

	public static void main(String[] args) {

		
		IClientFtp client = new ClientFtpImpl();
		
		File file = new File("teste10062019.txt");
		
		boolean a = file.exists();
		
		if(!a)
			return;
		
		try {
			client.uploadFile(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		

	}

}
