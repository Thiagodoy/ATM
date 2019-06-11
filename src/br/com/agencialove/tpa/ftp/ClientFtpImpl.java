package br.com.agencialove.tpa.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;




public class ClientFtpImpl implements IClientFtp {

	
	
	
	
	@Override
	public void uploadFile(File file) throws Exception {

		PropertiesFtp properties = this.loadProperties();
		
	     

	        FTPClient ftpClient = new FTPClient();

	        try {

	            ftpClient.connect(properties.getHost(),properties.getPort());
	            
	            int reply;
	    		
	    		reply = ftpClient.getReplyCode();
	    		if (!FTPReply.isPositiveCompletion(reply)) {
	    			ftpClient.disconnect();
	    			throw new Exception("Exception in connecting to FTP Server");
	    		}
	            

	            boolean success = ftpClient.login(properties.getUser(),properties.getPassword());
	            InputStream inputStream = new FileInputStream(file);
	            
	            boolean upload = ftpClient.storeFile(properties.getFolder() + "/" + file.getName(),inputStream);
	            
	             
	            reply = ftpClient.getReplyCode();
	    		if (!FTPReply.isPositiveCompletion(reply)) {
	    			ftpClient.disconnect();
	    			throw new Exception("Exception in connecting to FTP Server");
	    		}
	            

	        } catch (Exception ex) {
	            System.out.println("Oops! Something wrong happened");
	            ex.printStackTrace();
	        }finally {	        	
	            try {
					ftpClient.disconnect();					
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	}	
	
	
	private PropertiesFtp loadProperties() {
		
		
		  try (InputStream input = new FileInputStream("client-ftp.properties")) {

	            Properties prop = new Properties();
	            prop.load(input);

 
	            PropertiesFtp propertiesFtp = new PropertiesFtp();
	            propertiesFtp.setUser(prop.getProperty("user"));
	            propertiesFtp.setPassword(prop.getProperty("password"));
	            propertiesFtp.setHost(prop.getProperty("host"));
	            propertiesFtp.setPort(Integer.valueOf(prop.getProperty("port")));
	            propertiesFtp.setFolder(prop.getProperty("pathFolder"));
	            propertiesFtp.setProtocol(prop.getProperty("protocol"));
	            
	            return propertiesFtp;

	        } catch (IOException ex) {
	            ex.printStackTrace();
	            return null;
	        }
	}

}
