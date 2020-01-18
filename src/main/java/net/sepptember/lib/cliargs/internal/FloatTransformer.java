package net.sepptember.lib.cliargs.internal;

public class FloatTransformer extends TransformerSupport<Float> {
	FloatTransformer() {
		super(Float.class);
	}

	@Override
	protected Float unguardedTransform(String value) {
		return Float.parseFloat(value);
	}
}
