package net.sepptember.lib.cliargs.internal;

public class ShortTransformer extends NumberTransformer<Short> {
	ShortTransformer() {
		super(Short.class);
	}

	@Override
	protected Short unguardedTransform(String value) {
		return Short.parseShort(value);
	}
}
