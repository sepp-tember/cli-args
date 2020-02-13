package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static net.sepptember.lib.cliargs.internal.TransformerTestUtil.testTransformOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class IntegerPrimitiveTransformerTest {
	@Test
	public void testTransformReturnsIntegerAsValueWhenHighestIntegerIsGivenAsArgument()
			throws TransformationFailedException {
		testTransformOf(new IntegerPrimitiveTransformer())
				.with(ImmutableList.of("2147483647"))
				.verifyValue(value -> assertThat(value, is(2147483647)));
	}

	@Test
	public void testTransformReturnsIntegerAsValueWhenLowestIntegerIsGivenAsArgument()
			throws TransformationFailedException {
		testTransformOf(new IntegerPrimitiveTransformer())
				.with(ImmutableList.of("-2147483648"))
				.verifyValue(value -> assertThat(value, is(-2147483648)));
	}

	@Test
	public void testTransformReturnsNullAsValueIfNullIsGivenAsArgument() throws TransformationFailedException {
		testTransformOf(new IntegerPrimitiveTransformer())
				.with(ImmutableList.of((String) null))
				.verifyValue(value -> assertThat(value, is(nullValue())));
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenArgumentIsNoInteger() {
		IntegerPrimitiveTransformer transformer = new IntegerPrimitiveTransformer();
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
						.with(ImmutableList.of("2147483648"))
						.willThrow(TransformationFailedException.class),
				() -> testTransformOf(transformer).
						with(ImmutableList.of("-2147483649"))
						.willThrow(TransformationFailedException.class)
		);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenNoArgumentIsGiven() {
		testTransformOf(new IntegerPrimitiveTransformer())
				.with(ImmutableList.of())
				.willThrow(TransformationFailedException.class);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenNullIsGivenAsArgumentList() {
		testTransformOf(new IntegerPrimitiveTransformer()).with(null).willThrow(TransformationFailedException.class);
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutIntegerArgument() throws TransformationFailedException {
		testTransformOf(new IntegerPrimitiveTransformer())
				.with(ImmutableList.of("123", "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}

	@Test
	public void testTransformReturnsEmptyArgumentListWhenOnlyIntegerArgumentIsGiven() throws TransformationFailedException {
		testTransformOf(new IntegerPrimitiveTransformer())
				.with(ImmutableList.of("123"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, is(emptyIterable())));
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutNullArgument() throws TransformationFailedException {
		testTransformOf(new IntegerPrimitiveTransformer())
				.with(ImmutableList.of(null, "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}
}