package net.sepptember.lib.cliargs;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CliArgsScanAndPopulateTest {

	private static final String STRING_OPTION = "-String";
	private static final String INT_PRIMITIVE_OPTION = "-int";
	private static final String INT_OBJECT_OPTION = "-Integer";
	private static final String BYTE_PRIMITIVE_OPTION = "-byte";
	private static final String BYTE_OBJECT_OPTION = "-Byte";
	private static final String SHORT_PRIMITIVE_OPTION = "-short";
	private static final String SHORT_OBJECT_OPTION = "-Short";
	private static final String LONG_PRIMITIVE_OPTION = "-long";
	private static final String LONG_OBJECT_OPTION = "-Long";
	private static final String DOUBLE_PRIMITIVE_OPTION = "-double";
	private static final String DOUBLE_OBJECT_OPTION = "-Double";
	private static final String FLOAT_PRIMITIVE_OPTION = "-float";
	private static final String FLOAT_OBJECT_OPTION = "-Float";
	private static final String BOOLEAN_PRIMITIVE_OPTION = "-boolean";
	private static final String BOOLEAN_OBJECT_OPTION = "-Boolean";

	@Test
	public void testTargetStringOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		String expectedValue = "value";
		String[] args = {STRING_OPTION, expectedValue};

		assertThat(CliArgs.scan(target).andPopulateWith(args).stringValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetIntOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		int expectedValue = 17;
		String[] args = {INT_PRIMITIVE_OPTION, Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).intValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetIntegerObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		int expectedValue = 19;
		String[] args = {INT_OBJECT_OPTION, Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).integerObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetByteOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		byte expectedValue = 3;
		String[] args = {BYTE_PRIMITIVE_OPTION, Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).byteValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetByteObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		byte expectedValue = 5;
		String[] args = {BYTE_OBJECT_OPTION, Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).byteObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetShortOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		short expectedValue = 113;
		String[] args = {SHORT_PRIMITIVE_OPTION, Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).shortValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetShortObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		short expectedValue = 117;
		String[] args = {SHORT_OBJECT_OPTION, Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).shortObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetLongOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		long expectedValue = 2147483651L;
		String[] args = {LONG_PRIMITIVE_OPTION, Long.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).longValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetLongObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		long expectedValue = 2147483651L;
		String[] args = {LONG_OBJECT_OPTION, Long.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).longObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetDoubleOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		double expectedValue = 1.1e123;
		String[] args = {DOUBLE_PRIMITIVE_OPTION, Double.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).doubleValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetDoubleObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		double expectedValue = 1.2e234;
		String[] args = {DOUBLE_OBJECT_OPTION, Double.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).doubleObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetFloatOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		float expectedValue = 1.1e24f;
		String[] args = {FLOAT_PRIMITIVE_OPTION, Float.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).floatValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetFloatObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		float expectedValue = 1.2e23f;
		String[] args = {FLOAT_OBJECT_OPTION, Float.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).floatObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetBooleanOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		boolean expectedValue = true;
		String[] args = {BOOLEAN_PRIMITIVE_OPTION, Boolean.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).booleanValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetBooleanObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		boolean expectedValue = true;
		String[] args = {BOOLEAN_OBJECT_OPTION, Boolean.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).booleanObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetOptionIsIgnoredWhenFieldTypeIsNotSupported() {
		Target target = new Target();
		String[] args = {"-u", "value"};
		assertThat(CliArgs.scan(target).andPopulateWith(args).unsupportedValue, is(nullValue()));
	}

	@Test
	public void testTargetOptionIsNotSetWhenArgsIsEmptyArray() {
		Target target = new Target();
		assertThat(CliArgs.scan(target).andPopulateWith(new String[]{}).stringValue, is(nullValue()));
	}

	@Test
	public void testTargetOptionIsNotSetWhenArgsIsNull() {
		Target target = new Target();
		assertThat(CliArgs.scan(target).andPopulateWith(null).stringValue, is(nullValue()));
	}

	@Test
	public void testPopulateWithArgsReturnsNullWhenScanTargetIsNull() {
		String[] args = {STRING_OPTION, "value"};
		assertThat(CliArgs.scan(null).andPopulateWith(args), is(nullValue()));
	}

	private class Target {
		@Option(STRING_OPTION)
		private String stringValue;

		@Option(INT_PRIMITIVE_OPTION)
		private int intValue;

		@Option(INT_OBJECT_OPTION)
		private Integer integerObjectValue;

		@Option(BYTE_PRIMITIVE_OPTION)
		private byte byteValue;

		@Option(BYTE_OBJECT_OPTION)
		private Byte byteObjectValue;

		@Option(SHORT_PRIMITIVE_OPTION)
		private short shortValue;

		@Option(SHORT_OBJECT_OPTION)
		private Short shortObjectValue;

		@Option(LONG_PRIMITIVE_OPTION)
		private long longValue;

		@Option(LONG_OBJECT_OPTION)
		private Long longObjectValue;

		@Option(DOUBLE_PRIMITIVE_OPTION)
		private double doubleValue;

		@Option(DOUBLE_OBJECT_OPTION)
		private Double doubleObjectValue;

		@Option(FLOAT_PRIMITIVE_OPTION)
		private float floatValue;

		@Option(FLOAT_OBJECT_OPTION)
		private Float floatObjectValue;

		@Option(BOOLEAN_PRIMITIVE_OPTION)
		private boolean booleanValue;

		@Option(BOOLEAN_OBJECT_OPTION)
		private Boolean booleanObjectValue;

		@Option("-u")
		private Object unsupportedValue;
	}
}
