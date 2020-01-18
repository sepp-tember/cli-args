package net.sepptember.lib.cliargs.internal;

public class LongTransformer extends TransformerSupport<Long> {
	LongTransformer() {
		super(Long.class);
	}

	@Override
	protected Long unguardedTransform(String value) {
		return Long.parseLong(value);
	}
}
