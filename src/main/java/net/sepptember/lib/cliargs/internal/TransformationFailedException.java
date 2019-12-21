package net.sepptember.lib.cliargs.internal;

class TransformationFailedException extends Exception {
	TransformationFailedException(Class<?> transformationType, Throwable cause) {
		super("Transformation from String to " + transformationType.getCanonicalName() + " failed", cause);
	}
}
