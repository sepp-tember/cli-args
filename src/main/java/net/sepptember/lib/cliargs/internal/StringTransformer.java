package net.sepptember.lib.cliargs.internal;

public class StringTransformer extends AbstractSingleArgumentTransformer<String> {
	StringTransformer() {
		super(String.class);
	}

	@Override
	protected String unguardedTransform(String argument) {
		return argument;
	}
}
