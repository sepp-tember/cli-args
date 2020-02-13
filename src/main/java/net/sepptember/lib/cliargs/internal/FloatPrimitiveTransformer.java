package net.sepptember.lib.cliargs.internal;

public class FloatPrimitiveTransformer extends AbstractSingleArgumentTransformer<Float> {
	FloatPrimitiveTransformer() {
		super(float.class);
	}

	@Override
	protected Float unguardedTransform(String argument) {
		return Float.parseFloat(argument);
	}
}
