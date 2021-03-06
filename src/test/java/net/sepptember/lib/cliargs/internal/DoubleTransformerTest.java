package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static net.sepptember.lib.cliargs.internal.TransformerTestUtil.testTransformOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class DoubleTransformerTest {
	@Test
	public void testTransformDoubleAsStringToDouble() {
		assertAll(
				() -> testTransformOf(new DoubleTransformer())
						.with(ImmutableList.of("1.2e153"))
						.verifyValue(value -> assertThat(value, is(1.2e153))),
				() -> testTransformOf(new DoubleTransformer())
						.with(ImmutableList.of("1.2e-153"))
						.verifyValue(value -> assertThat(value, is(1.2e-153))),
				() -> testTransformOf(new DoubleTransformer())
						.with(ImmutableList.of("NaN"))
						.verifyValue(value -> assertThat(value, is(Double.NaN))),
				() -> testTransformOf(new DoubleTransformer())
						.with(ImmutableList.of("Infinity"))
						.verifyValue(value -> assertThat(value, is(Double.POSITIVE_INFINITY)))
		);
	}

	@Test
	public void testTransformReturnsNullAsValueIfNullIsGivenAsArgument() throws TransformationFailedException {
		testTransformOf(new DoubleTransformer())
				.with(ImmutableList.of((String) null))
				.verifyValue(value -> assertThat(value, is(nullValue())));
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenArgumentIsNoDouble() {
		DoubleTransformer transformer = new DoubleTransformer();
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
		testTransformOf(new DoubleTransformer())
				.with(ImmutableList.of())
				.willThrow(TransformationFailedException.class);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenNullIsGivenAsArgumentList() {
		testTransformOf(new DoubleTransformer()).with(null).willThrow(TransformationFailedException.class);
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutDoubleArgument() throws TransformationFailedException {
		testTransformOf(new DoubleTransformer())
				.with(ImmutableList.of("123", "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}

	@Test
	public void testTransformReturnsEmptyArgumentListWhenOnlyDoubleArgumentIsGiven() throws TransformationFailedException {
		testTransformOf(new DoubleTransformer())
				.with(ImmutableList.of("123"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, is(emptyIterable())));
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutNullArgument() throws TransformationFailedException {
		testTransformOf(new DoubleTransformer())
				.with(ImmutableList.of(null, "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}
}