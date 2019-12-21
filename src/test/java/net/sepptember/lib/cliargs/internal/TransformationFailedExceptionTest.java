package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class TransformationFailedExceptionTest {
	@Test
	public void testMessageContainsTransformationType() {
		assertThat(new TransformationFailedException(Number.class, null).getMessage(), containsString("Number"));
	}

	@Test
	public void testCauseIsSetInException() {
		RuntimeException cause = new RuntimeException();
		assertThat(new TransformationFailedException(Object.class, cause).getCause(), is(sameInstance(cause)));
	}
}