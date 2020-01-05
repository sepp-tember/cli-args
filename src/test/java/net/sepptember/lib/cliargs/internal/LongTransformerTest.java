package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

class LongTransformerTest {
	@Test
	public void testTransformLongAsStringToLong() {
		assertAll(
				() -> assertThat(new LongTransformer().transform("9223372036854775807"), is(9223372036854775807L)),
				() -> assertThat(new LongTransformer().transform("-9223372036854775808"), is(-9223372036854775808L))
		);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenStringIsNoLong() {
		LongTransformer transformer = new LongTransformer();
		assertAll(
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("abc")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1 def")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1.1")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("9223372036854775808")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("-9223372036854775809"))
		);
	}

	@Test
	public void testTransformReturnsNullIfNullIsGiven() throws TransformationFailedException {
		assertThat(new LongTransformer().transform(null), is(nullValue()));
	}
}