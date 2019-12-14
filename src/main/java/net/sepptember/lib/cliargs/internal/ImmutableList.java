package net.sepptember.lib.cliargs.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class ImmutableList<E> implements Iterable<E> {
	private final List<E> backingList;

	private ImmutableList(List<E> backingList) {
		this.backingList = backingList;
	}

	@SafeVarargs
	public static <E> ImmutableList<E> of(E... elements) {
		return new ImmutableList<>(List.of(elements));
	}

	public E get(int index) {
		return backingList.get(index);
	}

	public int size() {
		return backingList.size();
	}

	public boolean isEmpty() {
		return backingList.isEmpty();
	}

	public boolean contains(Object o) {
		return backingList.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return backingList.containsAll(c);
	}

	public int indexOf(Object o) {
		return backingList.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return backingList.lastIndexOf(o);
	}

	public ImmutableIterator<E> iterator() {
		return new ImmutableIterator<>(backingList.iterator());
	}

	public Object[] toArray() {
		return backingList.toArray();
	}

	public E[] toArray(E[] a) {
		return backingList.toArray(a);
	}

	public ImmutableList<E> subList(int fromIndex, int toIndex) {
		return new ImmutableList<>(backingList.subList(fromIndex, toIndex));
	}

	public ImmutableList<E> add(E element, int index) {
		ArrayList<E> newBackingList = new ArrayList<>(backingList);
		newBackingList.add(index, element);
		return new ImmutableList<>(newBackingList);
	}

	public static class ImmutableIterator<E> implements Iterator<E> {
		private final Iterator<E> backingIterator;

		private ImmutableIterator(Iterator<E> backingIterator) {
			this.backingIterator = backingIterator;
		}

		@Override
		public boolean hasNext() {
			return backingIterator.hasNext();
		}

		@Override
		public E next() {
			return backingIterator.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove is not supported for immutable iterator");
		}
	}
}
