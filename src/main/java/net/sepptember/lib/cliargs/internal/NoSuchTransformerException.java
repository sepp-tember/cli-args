package net.sepptember.lib.cliargs.internal;

public class NoSuchTransformerException extends Exception {
	NoSuchTransformerException(Class<?> transformationType) {
		super("No transformer available for type '" + transformationType.getCanonicalName() + "'");
	}
}
