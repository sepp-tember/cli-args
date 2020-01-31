package net.sepptember.lib.cliargs.internal;

public class IntegerTransformer extends AbstractSingleArgumentTransformer<Integer> {
	IntegerTransformer() {
		super(Integer.class);
	}

	@Override
	protected Integer unguardedTransform(String argument) {
		return Integer.parseInt(argument);
	}
}
