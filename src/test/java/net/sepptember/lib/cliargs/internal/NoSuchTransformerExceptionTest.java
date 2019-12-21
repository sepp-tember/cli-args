package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class NoSuchTransformerExceptionTest {
	@Test
	public void testMessageOfExceptionContainsInfoAboutTransformationType() {
		assertThat(new NoSuchTransformerException(Number.class).getMessage(), containsString("Number"));
	}
}