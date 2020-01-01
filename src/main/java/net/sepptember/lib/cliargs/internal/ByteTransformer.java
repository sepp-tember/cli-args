package net.sepptember.lib.cliargs.internal;

public class ByteTransformer extends NumberTransformer<Byte> {
	ByteTransformer() {
		super(Byte.class);
	}

	@Override
	protected Byte unguardedTransform(String value) {
		return Byte.parseByte(value);
	}
}
