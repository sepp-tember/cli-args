package net.sepptember.lib.cliargs.internal;

public class IntegerPrimitiveTransformer extends AbstractSingleArgumentTransformer<Integer> {
	IntegerPrimitiveTransformer() {
		super(int.class);
	}

	@Override
	protected Integer unguardedTransform(String argument) {
		return Integer.parseInt(argument);
	}
}
