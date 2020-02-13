package net.sepptember.lib.cliargs.internal;

public class DoublePrimitiveTransformer extends AbstractSingleArgumentTransformer<Double> {
	DoublePrimitiveTransformer() {
		super(double.class);
	}

	@Override
	protected Double unguardedTransform(String argument) {
		return Double.parseDouble(argument);
	}
}
