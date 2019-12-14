package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImmutableListTest {
	@Test
	public void testGetReturnsElement() {
		ImmutableList<String> list = ImmutableList.of("first", "second");
		assertThat(list.get(1), is(equalTo("second")));
	}

	@Test
	public void testGetThrowsIndexOutOfBoundsExceptionWhenIndexIsHigherOrEqualToSizeOfList() {
		ImmutableList<String> list = ImmutableList.of("first", "second");
		assertAll(
				() -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(2)),
				() -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(3))
		);
	}

	@Test
	public void testGetThrowsIndexOutOfBoundsExceptionWhenIndexIsNegative() {
		ImmutableList<String> list = ImmutableList.of("first", "second");
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
	}

	@Test
	public void testSizeReturnsZeroForEmptyList() {
		assertThat(ImmutableList.of().size(), is(0));
	}

	@Test
	public void testSizeReturnsNumberOfElementsOfList() {
		assertThat(ImmutableList.of(2, 3, 5).size(), is(3));
	}

	@Test
	public void testIsEmptyIsTrueForEmptyList() {
		assertThat(ImmutableList.of().isEmpty(), is(true));
	}

	@Test
	public void testIsEmptyIsFalseForListWithElements() {
		assertThat(ImmutableList.of("element").isEmpty(), is(false));
	}

	@Test
	public void testContainsIsTrueWhenElementIsInList() {
		assertThat(ImmutableList.of("element").contains("element"), is(true));
	}

	@Test
	public void testContainsIsFalseWhenElementIsNotInList() {
		assertThat(ImmutableList.of("element").contains("otherElement"), is(false));
	}

	@Test
	public void testContainsAllIsTrueWhenAllGivenElementsAreInListInAnyOrder() {
		ImmutableList<String> list = ImmutableList.of("first", "second", "third");
		assertThat(list.containsAll(List.of("third", "first")), is(true));
	}

	@Test
	public void testContainsAllIsFalseWhenSomeGivenElementIsNotInList() {
		ImmutableList<String> list = ImmutableList.of("first", "second", "third");
		assertThat(list.containsAll(List.of("first", "something")), is(false));
	}

	@Test
	public void testIndexOfElementThatIsNotInListIsMinusOne() {
		assertThat(ImmutableList.of("first", "second").indexOf("something"), is(-1));
	}

	@Test
	public void testIndexOfElementIsLowestIndexOfSeveralMatchingElements() {
		ImmutableList<String> list = ImmutableList.of("first", "match", "second", "match");
		assertThat(list.indexOf("match"), is(1));
	}

	@Test
	public void testLastIndexOfElementThatIsNotInListIsMinusOne() {
		assertThat(ImmutableList.of("first", "second").lastIndexOf("something"), is(-1));
	}

	@Test
	public void testLastIndexOfElementIsHighestIndexOfSeveralMatchingElements() {
		ImmutableList<String> list = ImmutableList.of("first", "match", "second", "match");
		assertThat(list.lastIndexOf("match"), is(3));
	}

	@Test
	public void testToObjectArrayReturnsEmptyArrayForEmptyList() {
		assertThat(ImmutableList.of().toArray(), is(emptyArray()));
	}

	@Test
	public void testToObjectArrayReturnsArrayWithElementsOfList() {
		ImmutableList<String> list = ImmutableList.of("first", "second", "third");
		assertThat(list.toArray(), is(arrayContaining("first", "second", "third")));
	}

	@Test
	public void testToTypedArrayReturnsGivenArrayFilledWithElementsOfListAndSucceedingNullValue() {
		ImmutableList<String> list = ImmutableList.of("x", "y", "z");
		String[] givenArray = {"a", "b", "c", "d", "e"};
		assertThat(list.toArray(givenArray), is(
				both(sameInstance(givenArray))
						.and(arrayContaining("x", "y", "z", null, "e"))));
	}

	@Test
	public void testToTypedArrayReturnsNewArrayIfElementsDoNotFitIntoGivenArray() {
		ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d", "e");
		String[] givenArray = {"x", "y", "z"};
		assertThat(list.toArray(givenArray), is(
				both(not(sameInstance(givenArray)))
						.and(arrayContaining("a", "b", "c", "d", "e"))));
	}

	@Test
	public void testSubListReturnsNewListWithElementsFromInclusiveStartIndexToExclusiveEndIndex() {
		ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d", "e");
		assertAll(
				() -> assertThat(list.subList(1, 4), contains("b", "c", "d")),
				() -> assertThat(list.subList(0, 5), contains("a", "b", "c", "d", "e"))
		);
	}

	@Test
	public void testSubListThrowsIndexOutOfBoundsExceptionWhenStartIndexIsLowerThanZero() {
		ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d", "e");
		assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-1, 4));
	}

	@Test
	public void testSubListThrowsIndexOutOfBoundsExceptionWhenEndIndexIsGreaterThanSize() {
		ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d", "e");
		assertThrows(IndexOutOfBoundsException.class, () -> list.subList(1, 6));
	}

	@Test
	public void testSubListThrowsIllegalArgumentExceptionWhenStartIndexIsGreaterThanEndIndex() {
		ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d", "e");
		assertThrows(IllegalArgumentException.class, () -> list.subList(4, 1));
	}

	@Test
	public void testIteratorDeliversElementsOfList() {
		ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d", "e");
		assertThat(list, contains("a", "b", "c", "d", "e"));
	}

	@Test
	public void testIteratorReturnsIteratorThatThrowsUnsupportedOperationExceptionWhenRemoveIsCalled() {
		assertThrows(UnsupportedOperationException.class, () -> ImmutableList.of("a").iterator().remove());
	}

	@Test
	public void testAddElementAtGivenIndex() {
		ImmutableList<String> list = ImmutableList.of("a", "b", "c");
		assertThat(list.add("d", 2), contains("a", "b", "d", "c"));
	}

	@Test
	public void testAddDoesNotModifyInitialList() {
		ImmutableList<String> list = ImmutableList.of("a", "b", "c");
		list.add("d", 2);
		assertThat(list, contains("a", "b", "c"));
	}

	@Test
	public void testAddThrowsIndexOutOfBoundsExceptionWhenIndexIsNegative() {
		ImmutableList<String> list = ImmutableList.of("a", "b", "c");
		assertThrows(IndexOutOfBoundsException.class, () -> list.add("d", -1));
	}

	@Test
	public void testAddThrowsIndexOutOfBoundsExceptionWhenIndexIsGreaterThanSize() {
		ImmutableList<String> list = ImmutableList.of("a", "b", "c");
		assertThrows(IndexOutOfBoundsException.class, () -> list.add("d", 4));
	}
}