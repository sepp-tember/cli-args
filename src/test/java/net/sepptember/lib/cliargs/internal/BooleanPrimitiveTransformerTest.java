package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static net.sepptember.lib.cliargs.internal.TransformerTestUtil.testTransformOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class BooleanPrimitiveTransformerTest {
	@Test
	public void testTransformReturnsTrueAsValueWhenTrueLikeArgumentIsGiven() throws TransformationFailedException {
		testTransformOf(new BooleanPrimitiveTransformer())
				.with(ImmutableList.of("true"))
				.verifyValue(value -> assertThat(value, is(true)));
	}

	@Test
	public void testTransformReturnsFalseAsValueWhenFalseLikeArgumentIsGiven() throws TransformationFailedException {
		testTransformOf(new BooleanPrimitiveTransformer())
				.with(ImmutableList.of("notTrue"))
				.verifyValue(value -> assertThat(value, is(false)));
	}

	@Test
	public void testTransformReturnsTrueAsValueWhenNoArgumentIsGiven() throws TransformationFailedException {
		testTransformOf(new BooleanPrimitiveTransformer())
				.with(ImmutableList.of())
				.verifyValue(value -> assertThat(value, is(true)));
	}

	@Test
	public void testTransformReturnsFalseAsValueWhenNullIsGivenAsArgument()  throws TransformationFailedException {
		testTransformOf(new BooleanPrimitiveTransformer())
				.with(ImmutableList.of((String) null))
				.verifyValue(value -> assertThat(value, is(nullValue())));
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutTrueLikeArgument() throws TransformationFailedException {
		testTransformOf(new BooleanPrimitiveTransformer())
				.with(ImmutableList.of("true", "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutFalseLikeArgument() throws TransformationFailedException {
		testTransformOf(new BooleanPrimitiveTransformer())
				.with(ImmutableList.of("falseLike", "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}

	@Test
	public void testTransformReturnsEmptyArgumentListWhenOnlyBooleanArgumentIsGiven() throws TransformationFailedException {
		testTransformOf(new BooleanPrimitiveTransformer())
				.with(ImmutableList.of("true"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, is(emptyIterable())));
	}

	@Test
	public void testTransformReturnsGivenArgumentListWithoutNullArgument() throws TransformationFailedException {
		testTransformOf(new BooleanPrimitiveTransformer())
				.with(ImmutableList.of(null, "some", "other", "arguments"))
				.verifyRemainingArguments(arguments -> assertThat(arguments, contains("some", "other", "arguments")));
	}

	@Test
	public void testTransformReturnsEmptyListWhenGivenArgumentListIsEmpty() throws TransformationFailedException {
		testTransformOf(new BooleanPrimitiveTransformer())
				.with(ImmutableList.of())
				.verifyRemainingArguments(arguments -> assertThat(arguments, is(emptyIterable())));
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWhenArgumentListIsNull() {
		testTransformOf(new BooleanPrimitiveTransformer())
				.with(null)
				.willThrow(TransformationFailedException.class);
	}
}