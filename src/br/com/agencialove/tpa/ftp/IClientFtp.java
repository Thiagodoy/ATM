package br.com.agencialove.tpa.ftp;

import java.io.File;
import java.io.FileNotFoundException;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public interface IClientFtp {
	void uploadFile(File file)throws Exception;
}
	