package net.sepptember.lib.cliargs;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CliArgsScanAndPopulateTest {
	@Test
	public void testTargetStringOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		String expectedValue = "value";
		String[] args = {"-t", expectedValue};

		assertThat(CliArgs.scan(target).andPopulateWith(args).stringValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetIntOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		int expectedValue = 17;
		String[] args = {"-i", Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).intValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetIntegerObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		int expectedValue = 19;
		String[] args = {"-I", Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).integerObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetByteOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		byte expectedValue = 3;
		String[] args = {"-b", Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).byteValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetByteObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		byte expectedValue = 5;
		String[] args = {"-B", Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).byteObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetShortOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		short expectedValue = 113;
		String[] args = {"-s", Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).shortValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetShortObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		short expectedValue = 117;
		String[] args = {"-S", Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).shortObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetLongOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		long expectedValue = 2147483651L;
		String[] args = {"-l", Long.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).longValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetLongObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		long expectedValue = 2147483651L;
		String[] args = {"-L", Long.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).longObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetDoubleOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		double expectedValue = 1.1e123;
		String[] args = {"-d", Double.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).doubleValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetDoubleObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		double expectedValue = 1.2e234;
		String[] args = {"-D", Double.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).doubleObjectValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetFloatOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		float expectedValue = 1.1e24f;
		String[] args = {"-f", Float.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).floatValue, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetFloatObjectOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		float expectedValue = 1.2e23f;
		String[] args = {"-F", Float.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).floatObjectValue, is(equalTo(expectedValue)));
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
		String[] args = {"-t", "value"};
		assertThat(CliArgs.scan(null).andPopulateWith(args), is(nullValue()));
	}

	private class Target {
		@Option("-t")
		private String stringValue;

		@Option("-i")
		private int intValue;

		@Option("-I")
		private Integer integerObjectValue;

		@Option("-b")
		private byte byteValue;

		@Option("-B")
		private Byte byteObjectValue;

		@Option("-s")
		private short shortValue;

		@Option("-S")
		private Short shortObjectValue;

		@Option("-l")
		private long longValue;

		@Option("-L")
		private Long longObjectValue;

		@Option("-d")
		private double doubleValue;

		@Option("-D")
		private Double doubleObjectValue;

		@Option("-f")
		private float floatValue;

		@Option("-F")
		private Float floatObjectValue;

		@Option("-u")
		private Object unsupportedValue;
	}
}
