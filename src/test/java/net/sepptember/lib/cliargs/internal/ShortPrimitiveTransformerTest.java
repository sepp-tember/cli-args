package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static net.sepptember.lib.cliargs.internal.TransformerTestUtil.testTransformOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class ShortPrimitiveTransformerTest {
	@Test
	public void testTransformReturnsShortAsValueWhenHighestShortIsGivenAsArgument()
			throws TransformationFailedException {
		testTransformOf(new ShortPrimitiveTransformer())
				.with(ImmutableList.of("32767"))
				.verifyValue(value -> assertThat(value, is((short) 32767)));
	}

	@Test
	public void testTransformReturnsShortAsValueWhenLowestShortIsGivenAsArgument()
			throws TransformationFailedException {
		testTransformOf(new ShortPrimitiveTransformer())
				.with(ImmutableList.of("-32768"))
				.verifyValue(value -> assertThat(value, is((short) -32768)));
	}

	@Test
	public void testTransformReturnsNullAsValueIfNullIsGivenAsArgument() throws TransformationFailedException {
		testTransformOf(new ShortPrimitiveTransformer())
				.with(ImmutableList.of((String) null))
				.verifyValue(value -> assertThat(value, is(nullValue())));
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenArgumentIsNoShort() {
		ShortPrimitiveTransformer transformer = new ShortPrimitiveTransformer();
		assertAll(
				() -> testTransformOf(transformer)
						.with(ImmutableList.of("abc"))
						.willThrow(TransformationFailedException.class),
				() -> testTransformOf(transformer)
						.with(ImmutableList.of("1 def"))
						.willThrow(TransformationFailedException.class),
				() -> testTransformOf(transformer)
						.with(ImmutableList.of("1.1"))
						.willThrow(TransformationFailedException.class),
				() -> testTransformOf(transformer)
						.with(ImmutableList.of("32768"))
						.willThrow(TransformationFailedException.class),
				() -> testTransformOf(transformer).
						with(ImmutableList.of("-32769"))
						.willThrow(TransformationFailedException.class)
		);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenNoArgumentIsGiven() {
		testTransformOf(new ShortPrimitiveTransformer())
				.with(ImmutableList.of())
				.willThrow(TransformationFailedException.class);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenNullIsGivenAsArgumentList() {
		testTransformOf(new ShortPrimitiveTransformer()).with(null).willThrow(TransformationFailedException.class);
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutShortArgument() throws TransformationFailedException {
		testTransformOf(new ShortPrimitiveTransformer())
				.with(ImmutableList.of("123", "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}

	@Test
	public void testTransformReturnsEmptyArgumentListWhenOnlyShortArgumentIsGiven() throws TransformationFailedException {
		testTransformOf(new ShortPrimitiveTransformer())
				.with(ImmutableList.of("123"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, is(emptyIterable())));
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutNullArgument() throws TransformationFailedException {
		testTransformOf(new ShortPrimitiveTransformer())
				.with(ImmutableList.of(null, "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}
}