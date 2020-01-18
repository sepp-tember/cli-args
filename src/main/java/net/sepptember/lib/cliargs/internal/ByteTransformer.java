package net.sepptember.lib.cliargs.internal;

public class ByteTransformer extends TransformerSupport<Byte> {
	ByteTransformer() {
		super(Byte.class);
	}

	@Override
	protected Byte unguardedTransform(String value) {
		return Byte.parseByte(value);
	}
}
