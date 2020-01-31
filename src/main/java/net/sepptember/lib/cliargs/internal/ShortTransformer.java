package net.sepptember.lib.cliargs.internal;

public class ShortTransformer extends AbstractSingleArgumentTransformer<Short> {
	ShortTransformer() {
		super(Short.class);
	}

	@Override
	protected Short unguardedTransform(String argument) {
		return Short.parseShort(argument);
	}
}
