package com.netease.JavaFinal.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

public class ValidCreator {
	private Map<Class<?>, Method> creator;

	public ValidCreator() throws NoSuchMethodException, SecurityException {
		creator = new HashMap<Class<?>, Method>();
		this.creator.put(NotEmpty.class, ValidCreator.class.getDeclaredMethod("CreateNotEmptyRule", Annotation.class));
		this.creator.put(Size.class, ValidCreator.class.getDeclaredMethod("CreateSizeRule", Annotation.class));
		this.creator.put(Email.class, ValidCreator.class.getDeclaredMethod("CreateEmailRule", Annotation.class));
		this.creator.put(Min.class, ValidCreator.class.getDeclaredMethod("CreateMinRule", Annotation.class));
		this.creator.put(Length.class, ValidCreator.class.getDeclaredMethod("CreateLengthRule", Annotation.class));
	}

	public Map<String, String> CreateValidation(Annotation a)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, String> result = null;
		if (creator.containsKey(a.annotationType())) {
			result = (Map<String, String>) creator.get(a.annotationType()).invoke(this, a);
		}
		return result;
	}

	private Map<String, String> CreateNotEmptyRule(Annotation a) {
		Map<String, String> result = new HashMap<String, String>();
		NotEmpty notEmpty = (NotEmpty) a;
		result.put("rule", "\n\t\t\t\trequired:true,");
		result.put("message", "\n\t\t\t\trequired:\"" + notEmpty.message() + "\",");
		return result;
	}

	private Map<String, String> CreateSizeRule(Annotation a) {
		Map<String, String> result = new HashMap<String, String>();
		Size size = (Size) a;
		result.put("rule", String.format("\n\t\t\t\trangelength:[%s,%s],", size.min(), size.max()));
		result.put("message", "\n\t\t\t\trangelength:\"" + String.format(size.message(), size.min(), size.max()) + "\",");
		return result;
	}

	private Map<String, String> CreateEmailRule(Annotation a) {
		Map<String, String> result = new HashMap<String, String>();
		Email email = (Email) a;
		result.put("rule", "\n\t\t\t\temail:true,");
		result.put("message", "\n\t\t\t\temail:\"" + email.message() + "\",");
		return result;
	}

	private Map<String, String> CreateMinRule(Annotation a) {
		Map<String, String> result = new HashMap<String, String>();
		Min min = (Min) a;
		result.put("rule", String.format("\n\t\t\t\tmin:%s,", min.value()));
		result.put("message", "\n\t\t\t\trangelength:\"" + String.format(min.message(), min.value()) + "\",");
		return result;
	}

	private Map<String, String> CreateLengthRule(Annotation a) {
		Map<String, String> result = new HashMap<String, String>();
		Length length = (Length) a;
		if (length.min() == 0 && length.max() != Integer.MAX_VALUE) {
			result.put("rule", String.format("\n\t\t\t\tmaxlength:%s,", length.max()));
			result.put("message", "\n\t\t\t\tmaxlength:\"" + String.format(length.message(), length.max()) + "\",");
		}
		if (length.max() == Integer.MAX_VALUE && length.min() != 0) {
			result.put("rule", String.format("\n\t\t\t\tminlength:%s,", length.min()));
			result.put("message", "\n\t\t\t\tminlength:\"" + String.format(length.message(), length.min()) + "\",");
		}
		if (length.min() != 0 && length.max() != Integer.MAX_VALUE) {
			result.put("rule", String.format("\n\t\t\t\trangelength:[%s,%s],", length.min(), length.max()));
			result.put("message",
					"\n\t\t\t\trangelength:\"" + String.format(length.message(), length.min(), length.max()) + "\",");
		}
		return result;
	}
}
