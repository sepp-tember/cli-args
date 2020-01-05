package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

class ShortTransformerTest {
	@Test
	public void testTransformShortAsStringToShort() {
		assertAll(
				() -> assertThat(new ShortTransformer().transform("32767"), is((short) 32767)),
				() -> assertThat(new ShortTransformer().transform("-32768"), is((short) -32768))
		);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenStringIsNoShort() {
		ShortTransformer transformer = new ShortTransformer();
		assertAll(
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("abc")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1 def")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1.1")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("32768")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("-32769"))
		);
	}

	@Test
	public void testTransformReturnsNullIfNullIsGiven() throws TransformationFailedException {
		assertThat(new ShortTransformer().transform(null), is(nullValue()));
	}
}