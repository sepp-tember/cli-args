package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SeparatedOptionFilterTest {

	private final String optionName;
	private final Target scanTarget;
	private final Field field;

	SeparatedOptionFilterTest() throws NoSuchFieldException {
		optionName = "-option";
		scanTarget = new Target();
		field = Target.class.getDeclaredField("option");
	}

	@Test
	public void testProcessReturnsGivenArgsWithoutOptionAndSucceedingValueWhenFirstArgIsMatching() {
		ImmutableList<String> args = ImmutableList.of(optionName, "value", "someOtherValue");
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		assertThat(filter.process(args), contains("someOtherValue"));
	}

	@Test
	public void testProcessSetFieldInTargetToSucceedingValueWhenFirstArgIsMatching() {
		String expectedValue = "value";
		ImmutableList<String> args = ImmutableList.of(optionName, expectedValue);
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		filter.process(args);

		assertThat(scanTarget.option, is(equalTo(expectedValue)));
	}

	@Test
	public void testProcessReturnsEmptyArrayWhenFirstArgIsMatchingButNoValueIsSucceeding() {
		ImmutableList<String> args = ImmutableList.of(optionName);
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		assertThat(filter.process(args), is(emptyIterable()));
	}

	@Test
	public void testProcessDoesNotModifyFieldInTargetWhenFirstArgIsMatchingButNoValueIsSucceeding() {
		String expectedValue = "value";
		ImmutableList<String> args = ImmutableList.of(optionName);
		scanTarget.option = expectedValue;
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		filter.process(args);

		assertThat(scanTarget.option, is(equalTo(expectedValue)));
	}

	@Test
	public void testProcessReturnsUnmodifiedArrayWhenFirstArgIsNotMatchingAndNoNextFilterIsSet() {
		ImmutableList<String> args = ImmutableList.of("-otherOption", "value", optionName);
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		assertThat(filter.process(args), contains("-otherOption", "value", optionName));
	}

	@Test
	public void testProcessDoesNotModifyFieldInTargetWhenFirstArgIsNotMatching() {
		String expectedValue = "value";
		ImmutableList<String> args = ImmutableList.of("-otherOption", "value", optionName);
		scanTarget.option = expectedValue;
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		filter.process(args);

		assertThat(scanTarget.option, is(equalTo(expectedValue)));
	}

	@Test
	public void testProcessCallsNextFilterWithArgsAndReturnsResultOfNextFilterWhenFirstArgIsNotMatching() {
		ImmutableList<String> args = ImmutableList.of("-otherOption", "value", optionName);
		ImmutableList<String> processedArgs = ImmutableList.of(optionName);
		SeparatedOptionFilter nextFilter = mock(SeparatedOptionFilter.class);
		Mockito.when(nextFilter.process(args)).thenReturn(processedArgs);
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);
		filter.add(nextFilter);

		assertThat(filter.process(args), is(sameInstance(processedArgs)));
		verify(nextFilter).process(args);
	}

	@Test
	public void testProcessReturnsEmptyArrayWhenArgsIsEmpty() {
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, "-option");

		assertThat(filter.process(ImmutableList.of()), is(emptyIterable()));
	}

	@Test
	public void testProcessDoesNotModifyFieldInTargetWhenArgsIsEmpty() {
		String expectedValue = "value";
		scanTarget.option = expectedValue;
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		filter.process(ImmutableList.of());

		assertThat(scanTarget.option, is(equalTo(expectedValue)));
	}

	private class Target {
		private String option;
	}
}