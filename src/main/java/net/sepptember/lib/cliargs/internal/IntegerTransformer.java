package net.sepptember.lib.cliargs.internal;

public class IntegerTransformer implements Transformer<Integer> {
	@Override
	public Integer transform(String value) throws TransformationFailedException {
		try {
			return Integer.parseInt(value);
		} catch (Exception thrown) {
			throw new TransformationFailedException(Integer.class, thrown);
		}
	}
}
