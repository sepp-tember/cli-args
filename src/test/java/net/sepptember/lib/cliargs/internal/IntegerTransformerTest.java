package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

class IntegerTransformerTest {
	@Test
	public void testTransformIntegerAsStringToInteger() throws TransformationFailedException {
		assertThat(new IntegerTransformer().transform("123"), is(123));
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenStringIsNoInteger() {
		IntegerTransformer transformer = new IntegerTransformer();
		assertAll(
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("abc")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1 def")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1.1"))
		);
	}

	@Test
	public void testTransformReturnsNullIfNullIsGiven() throws TransformationFailedException {
		assertThat(new IntegerTransformer().transform(null), is(nullValue()));
	}
}