package net.sepptember.lib.cliargs.internal;

public interface Transformer<T> {
	Result<T> transform(ImmutableList<String> arguments) throws TransformationFailedException;

	interface Result<T> {
		T getValue();
		ImmutableList<String> getRemainingArguments();
	}
}
