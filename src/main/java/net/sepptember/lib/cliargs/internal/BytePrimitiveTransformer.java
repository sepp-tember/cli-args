package net.sepptember.lib.cliargs.internal;

public class BytePrimitiveTransformer extends AbstractSingleArgumentTransformer<Byte> {
	BytePrimitiveTransformer() {
		super(byte.class);
	}

	@Override
	protected Byte unguardedTransform(String argument) {
		return Byte.parseByte(argument);
	}
}
