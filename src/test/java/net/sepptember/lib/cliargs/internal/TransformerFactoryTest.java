package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransformerFactoryTest {
	@Test
	public void testCreateTransformerForStringFieldReturnsStringTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field stringField = Fields.class.getDeclaredField("stringField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(stringField), isA(StringTransformer.class));
	}

	@Test
	public void testCreateTransformerForIntegerFieldReturnsIntegerTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field integerField = Fields.class.getDeclaredField("integerField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(integerField), isA(IntegerTransformer.class));
	}

	@Test
	public void testCreateTransformerForIntFieldReturnsIntegerTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field intField = Fields.class.getDeclaredField("intField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(intField), isA(IntegerTransformer.class));
	}

	@Test
	public void testCreateTransformerForObjectFieldThrowsException() throws NoSuchFieldException {
		Field objectField = Fields.class.getDeclaredField("objectField");
		TransformerFactory factory = new TransformerFactory();

		assertThrows(NoSuchTransformerException.class, () -> factory.createTransformerFor(objectField));
	}

	private static class Fields {
		String stringField;
		Integer integerField;
		int intField;
		Object objectField;
	}
}