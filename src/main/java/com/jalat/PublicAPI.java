package com.jalat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation identifies the classes, interfaces and enums that are part of {@code Public API}.
 * Please, do not use any API that not marked by this annotation because them can be changed without any
 * notification.
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
public @interface PublicAPI {}
