package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static net.sepptember.lib.cliargs.internal.TransformerTestUtil.testTransformOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class FloatPrimitiveTransformerTest {
	@Test
	public void testTransformFloatAsStringToFloat() {
		assertAll(
				() -> testTransformOf(new FloatPrimitiveTransformer())
						.with(ImmutableList.of("1.2e38"))
						.verifyValue(value -> assertThat(value, is(1.2e38f))),
				() -> testTransformOf(new FloatPrimitiveTransformer())
						.with(ImmutableList.of("1.2e-38"))
						.verifyValue(value -> assertThat(value, is(1.2e-38f))),
				() -> testTransformOf(new FloatPrimitiveTransformer())
						.with(ImmutableList.of("NaN"))
						.verifyValue(value -> assertThat(value, is(Float.NaN))),
				() -> testTransformOf(new FloatPrimitiveTransformer())
						.with(ImmutableList.of("Infinity"))
						.verifyValue(value -> assertThat(value, is(Float.POSITIVE_INFINITY)))
		);
	}

	@Test
	public void testTransformReturnsNullAsValueIfNullIsGivenAsArgument() throws TransformationFailedException {
		testTransformOf(new FloatPrimitiveTransformer())
				.with(ImmutableList.of((String) null))
				.verifyValue(value -> assertThat(value, is(nullValue())));
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenArgumentIsNoFloat() {
		FloatPrimitiveTransformer transformer = new FloatPrimitiveTransformer();
		assertAll(
				() -> testTransformOf(transformer)
						.with(ImmutableList.of("abc"))
						.willThrow(TransformationFailedException.class),
				() -> testTransformOf(transformer)
						.with(ImmutableList.of("1 def"))
						.willThrow(TransformationFailedException.class),
				() -> testTransformOf(transformer)
						.with(ImmutableList.of("1,1"))
						.willThrow(TransformationFailedException.class)
		);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenNoArgumentIsGiven() {
		testTransformOf(new FloatPrimitiveTransformer())
				.with(ImmutableList.of())
				.willThrow(TransformationFailedException.class);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenNullIsGivenAsArgumentList() {
		testTransformOf(new FloatPrimitiveTransformer()).with(null).willThrow(TransformationFailedException.class);
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutFloatArgument() throws TransformationFailedException {
		testTransformOf(new FloatPrimitiveTransformer())
				.with(ImmutableList.of("123", "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}

	@Test
	public void testTransformReturnsEmptyArgumentListWhenOnlyFloatArgumentIsGiven() throws TransformationFailedException {
		testTransformOf(new FloatPrimitiveTransformer())
				.with(ImmutableList.of("123"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, is(emptyIterable())));
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutNullArgument() throws TransformationFailedException {
		testTransformOf(new FloatPrimitiveTransformer())
				.with(ImmutableList.of(null, "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}
}