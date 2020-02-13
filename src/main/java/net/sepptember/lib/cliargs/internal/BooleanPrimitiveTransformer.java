package net.sepptember.lib.cliargs.internal;

public class BooleanPrimitiveTransformer extends BooleanTransformer{
	@Override
	public Class<Boolean> resultType() {
		return boolean.class;
	}
}
