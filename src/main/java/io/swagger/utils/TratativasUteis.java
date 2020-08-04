package io.swagger.utils;

import java.util.List;

public class TratativasUteis {

	public static <T> boolean isListNotEmpty(List<T> list) {

		if (list != null && !list.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

}
