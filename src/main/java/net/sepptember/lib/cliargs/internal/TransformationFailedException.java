package net.sepptember.lib.cliargs.internal;

class TransformationFailedException extends Exception {
	private final Class<?> transformationType;

	TransformationFailedException(Class<?> transformationType, Throwable cause) {
		super("Transformation from String to " + transformationType.getCanonicalName() + " failed", cause);
		this.transformationType = transformationType;
	}

	Class<?> getTransformationType() {
		return transformationType;
	}
}
