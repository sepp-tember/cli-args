package net.sepptember.lib.cliargs.internal;

public class IntegerTransformer extends TransformerSupport<Integer> {
	IntegerTransformer() {
		super(Integer.class);
	}

	@Override
	protected Integer unguardedTransform(String value) {
		return Integer.parseInt(value);
	}
}
