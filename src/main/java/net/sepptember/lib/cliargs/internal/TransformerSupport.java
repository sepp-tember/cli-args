package net.sepptember.lib.cliargs.internal;

public abstract class TransformerSupport<T> implements Transformer<T> {
	private final Class<T> transformationType;

	TransformerSupport(Class<T> transformationType) {
		if (transformationType == null) {
			throw new NullPointerException("Transformation type must be given but was 'null'");
		}
		this.transformationType = transformationType;
	}

	@Override
	public T transform(String value) throws TransformationFailedException {
		if (value == null) {
			return null;
		} else {
			try {
				return unguardedTransform(value);
			} catch (Exception thrown) {
				throw new TransformationFailedException(transformationType, thrown);
			}
		}
	}

	protected abstract T unguardedTransform(String value);
}
