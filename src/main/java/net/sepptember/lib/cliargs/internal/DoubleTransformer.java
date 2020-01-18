package net.sepptember.lib.cliargs.internal;

public class DoubleTransformer extends TransformerSupport<Double> {
	DoubleTransformer() {
		super(Double.class);
	}

	@Override
	protected Double unguardedTransform(String value) {
		return Double.parseDouble(value);
	}
}
