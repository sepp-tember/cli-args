package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}