package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

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
	public void testProcessReturnsGivenArgsWithoutOptionAndSucceedingValueWhenFirstArgIsMatching()
			throws NoSuchTransformerException {
		ImmutableList<String> args = ImmutableList.of(optionName, "value", "someOtherValue");
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		assertThat(filter.process(args), contains("someOtherValue"));
	}

	@Test
	public void testProcessSetFieldInTargetToSucceedingValueWhenFirstArgIsMatching() throws NoSuchTransformerException {
		String expectedValue = "value";
		ImmutableList<String> args = ImmutableList.of(optionName, expectedValue);
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		filter.process(args);

		assertThat(scanTarget.option, is(equalTo(expectedValue)));
	}

	@Test
	public void testProcessReturnsEmptyArrayWhenFirstArgIsMatchingButNoValueIsSucceeding()
			throws NoSuchTransformerException {
		ImmutableList<String> args = ImmutableList.of(optionName);
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		assertThat(filter.process(args), is(emptyIterable()));
	}

	@Test
	public void testProcessDoesNotModifyFieldInTargetWhenFirstArgIsMatchingButNoValueIsSucceeding()
			throws NoSuchTransformerException {
		String expectedValue = "value";
		ImmutableList<String> args = ImmutableList.of(optionName);
		scanTarget.option = expectedValue;
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		filter.process(args);

		assertThat(scanTarget.option, is(equalTo(expectedValue)));
	}

	@Test
	public void testProcessReturnsUnmodifiedArrayWhenFirstArgIsNotMatchingAndNoNextFilterIsSet()
			throws NoSuchTransformerException {
		ImmutableList<String> args = ImmutableList.of("-otherOption", "value", optionName);
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		assertThat(filter.process(args), contains("-otherOption", "value", optionName));
	}

	@Test
	public void testProcessDoesNotModifyFieldInTargetWhenFirstArgIsNotMatching() throws NoSuchTransformerException {
		String expectedValue = "value";
		ImmutableList<String> args = ImmutableList.of("-otherOption", "value", optionName);
		scanTarget.option = expectedValue;
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		filter.process(args);

		assertThat(scanTarget.option, is(equalTo(expectedValue)));
	}

	@Test
	public void testProcessCallsNextFilterWithArgsAndReturnsResultOfNextFilterWhenFirstArgIsNotMatching()
			throws NoSuchTransformerException {
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
	public void testProcessReturnsEmptyArrayWhenArgsIsEmpty() throws NoSuchTransformerException {
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, "-option");

		assertThat(filter.process(ImmutableList.of()), is(emptyIterable()));
	}

	@Test
	public void testProcessDoesNotModifyFieldInTargetWhenArgsIsEmpty() throws NoSuchTransformerException {
		String expectedValue = "value";
		scanTarget.option = expectedValue;
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName);

		filter.process(ImmutableList.of());

		assertThat(scanTarget.option, is(equalTo(expectedValue)));
	}

	@Test
	public void testProcessUsesTransformerWhenSettingValueInTargetField() throws TransformationFailedException {
		String inputValue = "13";
		String outputValue = "17";
		Transformer transformer = mock(Transformer.class);
		when(transformer.transform(inputValue)).thenReturn(outputValue);
		SeparatedOptionFilter filter = new SeparatedOptionFilter(scanTarget, field, optionName, transformer);

		filter.process(ImmutableList.of(optionName, inputValue));

		assertThat(scanTarget.option, is(equalTo(outputValue)));
		verify(transformer).transform(inputValue);
	}

	private class Target {
		private String option;
	}
}