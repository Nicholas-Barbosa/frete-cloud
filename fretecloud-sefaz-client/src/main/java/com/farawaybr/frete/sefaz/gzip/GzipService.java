package com.farawaybr.frete.sefaz.gzip;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public interface GzipService {

	default String decompress(byte[] compressed) {

		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(compressed);
				GZIPInputStream gZipInputStream = new GZIPInputStream(new BufferedInputStream(inputStream))) {

			byte buffer[] = new byte[2048];
			StringBuilder stringBuilder = new StringBuilder();

			int byteRead;

			while ((byteRead = gZipInputStream.read(buffer)) != -1) {
				stringBuilder.append(new String(buffer, 0, byteRead));
			}
			return stringBuilder.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
