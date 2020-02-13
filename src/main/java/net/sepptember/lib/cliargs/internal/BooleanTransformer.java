package net.sepptember.lib.cliargs.internal;

public class BooleanTransformer implements Transformer<Boolean> {
	@Override
	public Class<Boolean> resultType() {
		return Boolean.class;
	}

	@Override
	public Result<Boolean> transform(ImmutableList<String> arguments) throws TransformationFailedException {
		if (arguments == null) {
			throw new TransformationFailedException(
					resultType(),
					new NullPointerException("Argument list must not be null")
			);
		}
		Boolean value;
		ImmutableList<String> remainingArguments;
		if (arguments.isEmpty()) {
			value = true;
			remainingArguments = arguments;
		} else {
			String argument = arguments.get(0);
			value = argument == null ? null : Boolean.parseBoolean(argument);
			remainingArguments = arguments.subList(1, arguments.size());
		}

		return new Result<>() {
			@Override
			public Boolean getValue() {
				return value;
			}

			@Override
			public ImmutableList<String> getRemainingArguments() {
				return remainingArguments;
			}
		};
	}
}
