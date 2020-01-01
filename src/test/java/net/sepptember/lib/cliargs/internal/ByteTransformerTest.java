package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

class ByteTransformerTest {
	@Test
	public void testTransformByteAsStringToByte() {
		assertAll(
				() -> assertThat(new ByteTransformer().transform("127"), is((byte) 127)),
				() -> assertThat(new ByteTransformer().transform("-128"), is((byte) -128))
		);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenStringIsNoByte() {
		ByteTransformer transformer = new ByteTransformer();
		assertAll(
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("abc")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1 def")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("1.1")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("128")),
				() -> assertThrows(TransformationFailedException.class, () -> transformer.transform("-129"))
		);
	}

	@Test
	public void testTransformReturnsNullIfNullIsGiven() throws TransformationFailedException {
		assertThat(new ByteTransformer().transform(null), is(nullValue()));
	}
}