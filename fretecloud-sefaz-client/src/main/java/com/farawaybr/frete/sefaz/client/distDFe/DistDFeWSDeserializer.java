package com.farawaybr.frete.sefaz.client.distDFe;

import java.util.List;

public interface DistDFeWSDeserializer<R, P> {

	/**
	 * This method will decompress the gzip value of P and deserialize the resulting
	 * value from the decompression, resulting in a decompressed object.
	 * 
	 * @param <P> param object that will be gzip decompressed.
	 * @return <R> resulting decompressed object.
	 */
	List<R> deserialize(P r);

	

}
