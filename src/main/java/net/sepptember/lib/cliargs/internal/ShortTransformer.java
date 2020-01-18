package net.sepptember.lib.cliargs.internal;

public class ShortTransformer extends TransformerSupport<Short> {
	ShortTransformer() {
		super(Short.class);
	}

	@Override
	protected Short unguardedTransform(String value) {
		return Short.parseShort(value);
	}
}
