package net.sepptember.lib.cliargs.internal;

public class LongTransformer extends AbstractSingleArgumentTransformer<Long> {
	LongTransformer() {
		super(Long.class);
	}

	@Override
	protected Long unguardedTransform(String argument) {
		return Long.parseLong(argument);
	}
}
