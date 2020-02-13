package net.sepptember.lib.cliargs.internal;

public class ShortPrimitiveTransformer extends AbstractSingleArgumentTransformer<Short> {
	ShortPrimitiveTransformer() {
		super(short.class);
	}

	@Override
	protected Short unguardedTransform(String argument) {
		return Short.parseShort(argument);
	}
}
