package br.com.cauezito.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConversionByType {

	public static byte[] streamToByte(InputStream data) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		try {
			int reads = data.read();
			reads = data.read();
			while(reads != -1) {
				stream.write(reads);
				reads = data.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return stream.toByteArray();
	}

}
