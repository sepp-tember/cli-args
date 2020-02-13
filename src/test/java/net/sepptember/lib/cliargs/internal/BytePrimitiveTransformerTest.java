package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static net.sepptember.lib.cliargs.internal.TransformerTestUtil.testTransformOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class BytePrimitiveTransformerTest {
	@Test
	public void testTransformReturnsByteAsValueWhenHighestByteIsGivenAsArgument()
			throws TransformationFailedException {
		testTransformOf(new BytePrimitiveTransformer())
				.with(ImmutableList.of("127"))
				.verifyValue(value -> assertThat(value, is((byte) 127)));
	}

	@Test
	public void testTransformReturnsByteAsValueWhenLowestByteIsGivenAsArgument()
			throws TransformationFailedException {
		testTransformOf(new BytePrimitiveTransformer())
				.with(ImmutableList.of("-128"))
				.verifyValue(value -> assertThat(value, is((byte) -128)));
	}

	@Test
	public void testTransformReturnsNullAsValueIfNullIsGivenAsArgument() throws TransformationFailedException {
		testTransformOf(new BytePrimitiveTransformer())
				.with(ImmutableList.of((String) null))
				.verifyValue(value -> assertThat(value, is(nullValue())));
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenGivenArgumentIsNoByte() {
		BytePrimitiveTransformer transformer = new BytePrimitiveTransformer();
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
		testTransformOf(new BytePrimitiveTransformer())
				.with(ImmutableList.of())
				.willThrow(TransformationFailedException.class);
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenNullIsGivenAsArgumentList() {
		testTransformOf(new BytePrimitiveTransformer()).with(null).willThrow(TransformationFailedException.class);
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutByteArgument() throws TransformationFailedException {
		testTransformOf(new BytePrimitiveTransformer())
				.with(ImmutableList.of("123", "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}

	@Test
	public void testTransformReturnsEmptyArgumentListWhenOnlyByteArgumentIsGiven() throws TransformationFailedException {
		testTransformOf(new BytePrimitiveTransformer())
				.with(ImmutableList.of("123"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, is(emptyIterable())));
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutNullArgument() throws TransformationFailedException {
		testTransformOf(new BytePrimitiveTransformer())
				.with(ImmutableList.of(null, "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}
}