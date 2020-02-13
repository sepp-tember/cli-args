package net.sepptember.lib.cliargs.internal;

public class LongPrimitiveTransformer extends AbstractSingleArgumentTransformer<Long> {
	LongPrimitiveTransformer() {
		super(long.class);
	}

	@Override
	protected Long unguardedTransform(String argument) {
		return Long.parseLong(argument);
	}
}
