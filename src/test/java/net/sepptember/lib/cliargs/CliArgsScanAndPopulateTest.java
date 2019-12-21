package net.sepptember.lib.cliargs;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CliArgsScanAndPopulateTest {
	@Test
	public void testTargetStringOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		String expectedValue = "value";
		String[] args = {"-s", expectedValue};

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
	public void testTargetIntegerOptionIsSetWhenOptionIsPresentInArgs() {
		Target target = new Target();

		int expectedValue = 19;
		String[] args = {"-I", Integer.toString(expectedValue)};

		assertThat(CliArgs.scan(target).andPopulateWith(args).integerValue, is(equalTo(expectedValue)));
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
		String[] args = {"-s", "value"};
		assertThat(CliArgs.scan(null).andPopulateWith(args), is(nullValue()));
	}

	private class Target {
		@Option("-s")
		private String stringValue;

		@Option("-i")
		private int intValue;

		@Option("-I")
		private Integer integerValue;

		@Option("-u")
		private Object unsupportedValue;
	}
}
