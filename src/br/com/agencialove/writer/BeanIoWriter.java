package br.com.agencialove.writer;




import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.Writer;
import java.util.List;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;




public class BeanIoWriter {

    //private BeanErrorHandler beanErrorHandler;

   
    public <T> File writer(List<T>list, String nameFile) {
        
        
        BeanWriter writer = null;
        
        try {
            StreamFactory factory = StreamFactory.newInstance();
            InputStream stream = factory.getClass().getClassLoader().getResourceAsStream("");
            factory.load(stream);
            Writer out = new BufferedWriter(new FileWriter("out.txt"));
            writer = factory.createWriter("",out);
            
            
            for (T t : list) {
            	writer.write(t);
			}
            
            writer.flush();
            writer.close();



        } catch (Exception ex) {
        }

        
        return null;
        
    }

  
}

