package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class StringTransformerTest {
	@Test
	public void testTransformReturnsGivenString() {
		String string = "someString";
		assertThat(new StringTransformer().transform(string), is(sameInstance(string)));
	}

	@Test
	public void testTransformReturnsNullIfNullIsGiven() {
		assertThat(new StringTransformer().transform(null), is(nullValue()));
	}
}