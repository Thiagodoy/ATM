package br.com.agencialove.writer;




import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;

import java.io.InputStream;

import java.io.Writer;
import java.util.List;
import java.util.Optional;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import br.com.agencialove.tpa.utils.Stream;




public class BeanIoWriter {
   
    public static <T> Optional<File> writer(List<T>list, String nameFile,Stream stream) {       
        
        BeanWriter writer = null;
        File file = null;
        
        try {
            StreamFactory factory = StreamFactory.newInstance();
            //InputStream str = factory.getClass().getClassLoader().getResourceAsStream(stream.getStreamFile());
            factory.load(stream.getStreamFile());
            file = new File(nameFile);
            Writer out = new BufferedWriter(new FileWriter(file));
            writer = factory.createWriter(stream.getStreamId(),out);
            
            
            for (T t : list) {
            	writer.write(t);
			}
            
            writer.flush();
            writer.close();



        } catch (Exception ex) {
        	ex.printStackTrace();
        	
        }finally {
        	
		}

        
        return Optional.ofNullable(file);
        
    }

  
}

