package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

class DoubleTransformerTest {
	@Test
	public void testTransformDoubleAsStringToDouble() {
		assertAll(
				() -> assertThat(new DoubleTransformer().transform("1.2e153"), is(1.2e153)),
				() -> assertThat(new DoubleTransformer().transform("1.2e-153"), is(1.2e-153)),
				() -> assertThat(new DoubleTransformer().transform("NaN"), is(Double.NaN)),
				() -> assertThat(new DoubleTransformer().transform("Infinity"), is(Double.POSITIVE_INFINITY))
		);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenStringIsNoDouble() {
		DoubleTransformer transformer = new DoubleTransformer();
		assertAll(
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("abc")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1 def")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1,1"))
		);
	}

	@Test
	public void testTransformReturnsNullIfNullIsGiven() throws TransformationFailedException {
		assertThat(new DoubleTransformer().transform(null), is(nullValue()));
	}
}