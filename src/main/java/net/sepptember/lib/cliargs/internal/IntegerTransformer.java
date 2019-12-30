package net.sepptember.lib.cliargs.internal;

public class IntegerTransformer implements Transformer<Integer> {
	@Override
	public Integer transform(String value) throws TransformationFailedException {
		if (value == null) {
			return null;
		} else {
			try {
				return Integer.parseInt(value);
			} catch (Exception thrown) {
				throw new TransformationFailedException(Integer.class, thrown);
			}
		}
	}
}
