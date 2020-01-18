package net.sepptember.lib.cliargs.internal;

public class BooleanTransformer extends TransformerSupport<Boolean> {
	BooleanTransformer() {
		super(Boolean.class);
	}

	@Override
	protected Boolean unguardedTransform(String value) {
		return Boolean.parseBoolean(value);
	}
}
