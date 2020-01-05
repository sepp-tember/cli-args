package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

class IntegerTransformerTest {
	@Test
	public void testTransformIntegerAsStringToInteger() {
		assertAll(
				() -> assertThat(new IntegerTransformer().transform("2147483647"), is(2147483647)),
				() -> assertThat(new IntegerTransformer().transform("-2147483648"), is(-2147483648))
		);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenStringIsNoInteger() {
		IntegerTransformer transformer = new IntegerTransformer();
		assertAll(
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("abc")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1 def")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1.1")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("2147483648")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("-2147483649"))
		);
	}

	@Test
	public void testTransformReturnsNullIfNullIsGiven() throws TransformationFailedException {
		assertThat(new IntegerTransformer().transform(null), is(nullValue()));
	}
}