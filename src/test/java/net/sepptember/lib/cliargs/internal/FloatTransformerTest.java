package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

class FloatTransformerTest {
	@Test
	public void testTransformFloatAsStringToFloat() {
		assertAll(
				() -> assertThat(new FloatTransformer().transform("1.2e38"), is(1.2e38f)),
				() -> assertThat(new FloatTransformer().transform("1.2e-38"), is(1.2e-38f)),
				() -> assertThat(new FloatTransformer().transform("NaN"), is(Float.NaN)),
				() -> assertThat(new FloatTransformer().transform("Infinity"), is(Float.POSITIVE_INFINITY))
		);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenStringIsNoFloat() {
		FloatTransformer transformer = new FloatTransformer();
		assertAll(
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("abc")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1 def")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1,1"))
		);
	}

	@Test
	public void testTransformReturnsNullIfNullIsGiven() throws TransformationFailedException {
		assertThat(new FloatTransformer().transform(null), is(nullValue()));
	}
}