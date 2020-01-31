package net.sepptember.lib.cliargs.internal;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class TransformerTestUtil {
	private TransformerTestUtil() {}

	@SuppressWarnings("unchecked")
	static <T> Transformer<T> mockTransformer() {
		return mock(Transformer.class);
	}

	static <T> TestArguments<T> testTransformOf(Transformer<T> transformer) {
		return arguments -> new TestVerification<>() {
			@Override
			public void verifyValue(Consumer<T> valueAssertion) throws TransformationFailedException {
				T value = transformer.transform(arguments).getValue();
				valueAssertion.accept(value);
			}

			@Override
			public void verifyRemainingArguments(Consumer<ImmutableList<String>> argumentsAssertion)
					throws TransformationFailedException {
				argumentsAssertion.accept(transformer.transform(arguments).getRemainingArguments());
			}

			@Override
			public void willThrow(Class<? extends Throwable> exceptionType) {
				assertThrows(exceptionType, () -> transformer.transform(arguments));
			}
		};
	}

	interface TestArguments<T> {
		TestVerification<T> with(ImmutableList<String> arguments);
	}

	interface TestVerification<T> {
		void verifyValue(Consumer<T> valueAssertion) throws TransformationFailedException;

		void verifyRemainingArguments(Consumer<ImmutableList<String>> argumentsAssertion)
				throws TransformationFailedException;

		void willThrow(Class<? extends Throwable> exceptionType);
	}
}
