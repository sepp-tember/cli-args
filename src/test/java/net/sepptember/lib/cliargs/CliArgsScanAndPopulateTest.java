package net.sepptember.lib.cliargs;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CliArgsScanAndPopulateTest {
	@Test
	public void testTargetOptionIsSetWhenOptionIsPresentInArgs() {
		class Target {
			@Option("-o")
			private String option;
		}
		Target target = new Target();

		String expectedValue = "value";
		String[] args = {"-o", expectedValue};

		assertThat(CliArgs.scan(target).andPopulateWith(args).option, is(equalTo(expectedValue)));
	}

	@Test
	public void testTargetOptionIsNotSetWhenArgsIsEmptyArray() {
		class Target {
			@Option("-o")
			private String option;
		}
		Target target = new Target();

		String[] args = {};

		assertThat(CliArgs.scan(target).andPopulateWith(args).option, is(nullValue()));
	}

	@Test
	public void testTargetOptionIsNotSetWhenArgsIsNull() {
		class Target {
			@Option("-o")
			private String option;
		}
		Target target = new Target();

		assertThat(CliArgs.scan(target).andPopulateWith(null).option, is(nullValue()));
	}

	@Test
	public void testPopulateWithArgsReturnsNullWhenScanTargetIsNull() {
		String[] args = {"-o", "value"};
		assertThat(CliArgs.scan(null).andPopulateWith(args), is(nullValue()));
	}
}
