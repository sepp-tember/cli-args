package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SeparateOptionMatcherTest {
	@Test
	public void testPopulateWhenMatchingArgIsFound() throws NoSuchFieldException {
		Target target = new Target();
		String optionName = "-option";
		String value = "value";
		List<String> args = List.of(optionName, value);

		SeparateOptionMatcher matcher = new SeparateOptionMatcher(
				optionName,
				target,
				target.getClass().getDeclaredField("option")
		);

		matcher.populateWhenMatching(args);

		assertThat(target.option, is(equalTo(value)));
	}

	@Test
	public void testDoNotPopulateWhenMatchingArgIsNotPresent() throws NoSuchFieldException {
		Target target = new Target();
		String optionName = "-option";
		List<String> args = List.of("-other", "someValue");

		SeparateOptionMatcher matcher = new SeparateOptionMatcher(
				optionName,
				target,
				target.getClass().getDeclaredField("option")
		);

		matcher.populateWhenMatching(args);

		assertThat(target.option, is(nullValue()));
	}

	private class Target {
		private String option;
	}
}