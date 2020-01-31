package net.sepptember.lib.cliargs.internal;

public class ByteTransformer extends AbstractSingleArgumentTransformer<Byte> {
	ByteTransformer() {
		super(Byte.class);
	}

	@Override
	protected Byte unguardedTransform(String argument) {
		return Byte.parseByte(argument);
	}
}
