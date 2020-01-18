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
	public void testCreateTransformerForIntegerObjectFieldReturnsIntegerTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field integerField = Fields.class.getDeclaredField("integerObjectField");
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
	public void testCreateTransformerForByteObjectFieldReturnsByteTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field byteObjectField = Fields.class.getDeclaredField("byteObjectField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(byteObjectField), isA(ByteTransformer.class));
	}

	@Test
	public void testCreateTransformerForByteFieldReturnsByteTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field byteField = Fields.class.getDeclaredField("byteField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(byteField), isA(ByteTransformer.class));
	}

	@Test
	public void testCreateTransformerForShortObjectFieldReturnsShortTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field shortObjectField = Fields.class.getDeclaredField("shortObjectField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(shortObjectField), isA(ShortTransformer.class));
	}

	@Test
	public void testCreateTransformerForShortFieldReturnsShortTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field shortField = Fields.class.getDeclaredField("shortField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(shortField), isA(ShortTransformer.class));
	}

	@Test
	public void testCreateTransformerForLongObjectFieldReturnsLongTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field longObjectField = Fields.class.getDeclaredField("longObjectField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(longObjectField), isA(LongTransformer.class));
	}

	@Test
	public void testCreateTransformerForLongFieldReturnsLongTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field longField = Fields.class.getDeclaredField("longField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(longField), isA(LongTransformer.class));
	}

	@Test
	public void testCreateTransformerForDoubleObjectFieldReturnsDoubleTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field doubleObjectField = Fields.class.getDeclaredField("doubleObjectField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(doubleObjectField), isA(DoubleTransformer.class));
	}

	@Test
	public void testCreateTransformerForDoubleFieldReturnsDoubleTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field doubleField = Fields.class.getDeclaredField("doubleField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(doubleField), isA(DoubleTransformer.class));
	}

	@Test
	public void testCreateTransformerForFloatObjectFieldReturnsFloatTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field floatObjectField = Fields.class.getDeclaredField("floatObjectField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(floatObjectField), isA(FloatTransformer.class));
	}

	@Test
	public void testCreateTransformerForFloatFieldReturnsFloatTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field floatField = Fields.class.getDeclaredField("floatField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(floatField), isA(FloatTransformer.class));
	}

	@Test
	public void testCreateTransformerForBooleanObjectFieldReturnsBooleanTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field booleanObjectField = Fields.class.getDeclaredField("booleanObjectField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(booleanObjectField), isA(BooleanTransformer.class));
	}

	@Test
	public void testCreateTransformerForBooleanFieldReturnsBooleanTransformer()
			throws NoSuchFieldException, NoSuchTransformerException {
		Field booleanField = Fields.class.getDeclaredField("booleanField");
		TransformerFactory factory = new TransformerFactory();

		assertThat(factory.createTransformerFor(booleanField), isA(BooleanTransformer.class));
	}

	@Test
	public void testCreateTransformerForObjectFieldThrowsException() throws NoSuchFieldException {
		Field objectField = Fields.class.getDeclaredField("objectField");
		TransformerFactory factory = new TransformerFactory();

		assertThrows(NoSuchTransformerException.class, () -> factory.createTransformerFor(objectField));
	}

	private static class Fields {
		String stringField;
		Integer integerObjectField;
		int intField;
		Byte byteObjectField;
		byte byteField;
		Short shortObjectField;
		short shortField;
		Long longObjectField;
		long longField;
		Double doubleObjectField;
		double doubleField;
		Float floatObjectField;
		float floatField;
		Boolean booleanObjectField;
		boolean booleanField;
		Object objectField;
	}
}