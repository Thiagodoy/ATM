package br.com.agencialove.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import br.com.agencialove.tpa.utils.Stream;

public class BeanIoWriter {

	public static <T> boolean writer(List<T> list, File file, Stream stream) {

		BeanWriter writer = null;
		Writer out = null;

		try {
			StreamFactory factory = StreamFactory.newInstance();
			factory.load(stream.getStreamFile());

			out = new BufferedWriter(new FileWriter(file));
			writer = factory.createWriter(stream.getStreamId(), out);

			for (T t : list) {
				writer.write(t);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}

		return true;

	}

}
