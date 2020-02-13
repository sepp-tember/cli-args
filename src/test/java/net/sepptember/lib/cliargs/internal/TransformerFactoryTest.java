package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransformerFactoryTest {
	@Test
	public void testCreateTransformerForStringReturnsStringTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(String.class), isA(StringTransformer.class));
	}

	@Test
	public void testCreateTransformerForIntegerWrapperReturnsIntegerTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(Integer.class), isA(IntegerTransformer.class));
	}

	@Test
	public void testCreateTransformerForPrimitiveIntReturnsIntegerTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(int.class), isA(IntegerPrimitiveTransformer.class));
	}

	@Test
	public void testCreateTransformerForByteWrapperReturnsByteTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(Byte.class), isA(ByteTransformer.class));
	}

	@Test
	public void testCreateTransformerForPrimitiveByteReturnsByteTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(byte.class), isA(BytePrimitiveTransformer.class));
	}

	@Test
	public void testCreateTransformerForShortWrapperReturnsShortTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(Short.class), isA(ShortTransformer.class));
	}

	@Test
	public void testCreateTransformerForPrimitiveShortReturnsShortTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(short.class), isA(ShortPrimitiveTransformer.class));
	}

	@Test
	public void testCreateTransformerForLongWrapperReturnsLongTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(Long.class), isA(LongTransformer.class));
	}

	@Test
	public void testCreateTransformerForPrimitiveLongReturnsLongTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(long.class), isA(LongPrimitiveTransformer.class));
	}

	@Test
	public void testCreateTransformerForDoubleWrapperReturnsDoubleTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(Double.class), isA(DoubleTransformer.class));
	}

	@Test
	public void testCreateTransformerForPrimitiveDoubleReturnsDoubleTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(double.class), isA(DoublePrimitiveTransformer.class));
	}

	@Test
	public void testCreateTransformerForFloatWrapperReturnsFloatTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(Float.class), isA(FloatTransformer.class));
	}

	@Test
	public void testCreateTransformerForPrimitiveFloatReturnsFloatTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(float.class), isA(FloatPrimitiveTransformer.class));
	}

	@Test
	public void testCreateTransformerForBooleanWrapperReturnsBooleanTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(Boolean.class), isA(BooleanTransformer.class));
	}

	@Test
	public void testCreateTransformerForPrimitiveBooleanReturnsBooleanTransformer() throws NoSuchTransformerException {
		TransformerFactory factory = new TransformerFactory();
		assertThat(factory.createTransformerFor(boolean.class), isA(BooleanPrimitiveTransformer.class));
	}

	@Test
	public void testCreateTransformerForObjectThrowsException() {
		TransformerFactory factory = new TransformerFactory();
		assertThrows(NoSuchTransformerException.class, () -> factory.createTransformerFor(Object.class));
	}
}