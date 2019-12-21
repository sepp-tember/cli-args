package net.sepptember.lib.cliargs.internal;

public interface Transformer<T> {
	T transform(String value) throws TransformationFailedException;
}
