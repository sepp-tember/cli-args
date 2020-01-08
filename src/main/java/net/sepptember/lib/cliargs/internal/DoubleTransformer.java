package net.sepptember.lib.cliargs.internal;

public class DoubleTransformer extends NumberTransformer<Double> {
	DoubleTransformer() {
		super(Double.class);
	}

	@Override
	protected Double unguardedTransform(String value) {
		return Double.parseDouble(value);
	}
}
