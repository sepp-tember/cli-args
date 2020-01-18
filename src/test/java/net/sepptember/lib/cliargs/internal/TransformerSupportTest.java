package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class TransformerSupportTest {
	@Test
	public void testConstructorThrowsNullPointerExceptionWhenTransformationTypeIsNull() {
		assertThrows(NullPointerException.class, () -> new TransformerSupport<Integer>(null) {
			@Override
			protected Integer unguardedTransform(String value) {
				return null;
			}
		});
	}

	@Test
	public void testTransformCallsUnguardedTransformForTransformation() throws TransformationFailedException {
		NumberTransformerSpy transformer = new NumberTransformerSpy();
		transformer.transform("123");
		assertThat(transformer.unguardedTransformHasBeenCalled, is(true));
	}

	@Test
	public void testTransformDoesNotCallUnguardedTransformWhenGivenValueIsNull() throws TransformationFailedException {
		NumberTransformerSpy transformer = new NumberTransformerSpy();
		transformer.transform(null);
		assertThat(transformer.unguardedTransformHasBeenCalled, is(false));
	}

	@Test
	public void testTransformReturnsNullWhenGivenValueIsNull() throws TransformationFailedException {
		TransformerSupport<Integer> transformer = new TransformerSupport<>(Integer.class) {
			@Override
			protected Integer unguardedTransform(String value) {
				return 3;
			}
		};
		assertThat(transformer.transform(null), is(nullValue()));
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWrappingExceptionThrownInUnguardedTransform() {
		RuntimeException exception = new RuntimeException();
		TransformerSupport<Integer> transformer = new TransformerSupport<>(Integer.class) {
			@Override
			protected Integer unguardedTransform(String value) {
				throw exception;
			}
		};

		try {
			transformer.transform("abc");
		} catch (TransformationFailedException e) {
			assertThat(e.getCause(), is(sameInstance(exception)));
			return;
		}
		fail("Expected TransformationFailedException to be thrown but was not.");
	}

	@Test
	public void testTransformThrowsTransformationFailedExceptionWithTransformationTypeInfoWhenUnguardedTransformThrowsSomeException() {
		TransformerSupport<Integer> transformer = new TransformerSupport<>(Integer.class) {
			@Override
			protected Integer unguardedTransform(String value) {
				throw new RuntimeException();
			}
		};

		try {
			transformer.transform("abc");
		} catch (TransformationFailedException e) {
			assertThat(e.getTransformationType(), is(Integer.class));
			return;
		}
		fail("Expected TransformationFailedException to be thrown but was not.");
	}

	private static class NumberTransformerSpy extends TransformerSupport<Integer> {
		private boolean unguardedTransformHasBeenCalled = false;

		NumberTransformerSpy() {
			super(Integer.class);
		}

		@Override
		protected Integer unguardedTransform(String value) {
			unguardedTransformHasBeenCalled = true;
			return null;
		}
	}
}