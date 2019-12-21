package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

class StringTransformerTest {
	@Test
	public void testTransformReturnsGivenString() {
		String string = "someString";
		assertThat(new StringTransformer().transform(string), is(sameInstance(string)));
	}
}