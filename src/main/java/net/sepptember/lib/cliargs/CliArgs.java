package net.sepptember.lib.cliargs;

import net.sepptember.lib.cliargs.internal.ImmutableList;
import net.sepptember.lib.cliargs.internal.SeparatedOptionFilter;

import java.util.Arrays;

public class CliArgs<T> {
	private final T target;
	private SeparatedOptionFilter rootFilter;

	private CliArgs(T target) {
		this.target = target;
	}

	public static <T> CliArgs<T> scan(T target) {
		return new CliArgs<>(target).scan();
	}

	private CliArgs<T> scan() {
		if (target != null) {
			rootFilter = Arrays.stream(target.getClass().getDeclaredFields())
					.filter(field -> field.isAnnotationPresent(Option.class))
					.map(field -> new SeparatedOptionFilter(target, field, field.getAnnotation(Option.class).value()))
					.reduce((collectedFilters, filter) -> filter.add(collectedFilters))
					.orElse(null);
		}
		return this;
	}

	public T andPopulateWith(String[] args) {
		if (args != null && rootFilter != null) {
			ImmutableList<String> filterArgs = ImmutableList.of();
			for (int i = args.length - 1; i >= 0; i--) {
				filterArgs = rootFilter.process(filterArgs.add(args[i], 0));
			}
		}
		return target;
	}
}
