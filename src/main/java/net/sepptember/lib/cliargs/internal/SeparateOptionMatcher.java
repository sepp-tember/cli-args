package net.sepptember.lib.cliargs.internal;

import java.lang.reflect.Field;
import java.util.List;

public class SeparateOptionMatcher {
	private final String optionName;
	private final Object target;
	private final Field field;

	public SeparateOptionMatcher(String optionName, Object target, Field field) {
		this.optionName = optionName;
		this.target = target;
		this.field = field;
		field.setAccessible(true);
	}

	public void populateWhenMatching(List<String> args) {
		int indexOfOption = args.indexOf(optionName);
		if (indexOfOption >= 0) {
			try {
				field.set(target, args.get(indexOfOption + 1));
			} catch (IllegalAccessException e) {
				// can't set field in target, so just don't do it
			}
		}
	}
}
