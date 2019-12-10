package net.sepptember.lib.cliargs;

import net.sepptember.lib.cliargs.internal.SeparateOptionMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class CliArgs<T> {
	private T target;
	private List<SeparateOptionMatcher> matchers = new ArrayList<>();

	private CliArgs(T target) {
		this.target = target;
	}

	public static <T> CliArgs<T> scan(T target) {
		return new CliArgs<>(target).scan();
	}

	private CliArgs<T> scan() {
		if (target != null) {
			matchers = Arrays.stream(target.getClass().getDeclaredFields())
					.filter(field -> field.isAnnotationPresent(Option.class))
					.map(field -> new SeparateOptionMatcher(field.getAnnotation(Option.class).value(), target, field))
					.collect(Collectors.toList());
		}
		return this;
	}

	public T andPopulateWith(String[] args) {
		if (args != null) {
			matchers.forEach(matcher -> matcher.populateWhenMatching(Arrays.asList(args)));
		}
		return target;
	}
}
