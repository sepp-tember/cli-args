package net.sepptember.lib.cliargs.internal;

public class DoubleTransformer extends AbstractSingleArgumentTransformer<Double> {
	DoubleTransformer() {
		super(Double.class);
	}

	@Override
	protected Double unguardedTransform(String argument) {
		return Double.parseDouble(argument);
	}
}
