package net.sepptember.lib.cliargs.internal;

import java.lang.reflect.Field;

public class SeparatedOptionFilter {
	private final Object target;
	private final Field field;
	private final String option;
	private SeparatedOptionFilter nextFilter;

	public SeparatedOptionFilter(Object target, Field field, String option) {
		this.target = target;
		this.field = field;
		field.setAccessible(true);
		this.option = option;
	}

	public ImmutableList<String> process(ImmutableList<String> args) {
		if (!args.isEmpty() && option.equals(args.get(0))) {
			if (args.size() > 1) {
				try {
					field.set(target, args.get(1));
				} catch (IllegalAccessException e) {
					// TODO use logger
					e.printStackTrace();
				}
				return args.subList(2, args.size());
			} else {
				return ImmutableList.of();
			}
		} else {
			if (nextFilter == null) {
				return args;
			} else {
				return nextFilter.process(args);
			}
		}
	}

	public SeparatedOptionFilter add(SeparatedOptionFilter nextFilter) {
		this.nextFilter = nextFilter;
		return this;
	}
}
