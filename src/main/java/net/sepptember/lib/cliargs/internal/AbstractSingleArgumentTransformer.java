package net.sepptember.lib.cliargs.internal;

public abstract class AbstractSingleArgumentTransformer<T> implements Transformer<T> {
	private final Class<T> transformationType;

	AbstractSingleArgumentTransformer(Class<T> transformationType) {
		this.transformationType = transformationType;
	}

	@Override
	public Class<T> resultType() {
		return transformationType;
	}

	@Override
	public Result<T> transform(ImmutableList<String> arguments) throws TransformationFailedException {
		T value;
		ImmutableList<String> remainingArguments;
		try {
			if (arguments == null) {
				throw new NullPointerException("Argument list must not be null");
			} else if (arguments.isEmpty()) {
				throw new IllegalArgumentException("Argument list must not be empty");
			}
			String firstArgument = arguments.get(0);
			value = firstArgument == null ? null : unguardedTransform(firstArgument);
			remainingArguments = arguments.subList(1, arguments.size());
		} catch (Exception e) {
			throw new TransformationFailedException(transformationType, e);
		}

		return new Result<>() {
			@Override
			public T getValue() {
				return value;
			}

			@Override
			public ImmutableList<String> getRemainingArguments() {
				return remainingArguments;
			}
		};
	}

	protected abstract T unguardedTransform(String argument);
}
