package net.sepptember.lib.cliargs.internal;

public class FloatTransformer extends AbstractSingleArgumentTransformer<Float> {
	FloatTransformer() {
		super(Float.class);
	}

	@Override
	protected Float unguardedTransform(String argument) {
		return Float.parseFloat(argument);
	}
}
