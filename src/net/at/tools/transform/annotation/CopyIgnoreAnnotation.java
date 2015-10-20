package net.at.tools.transform.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * コピーしないフィールドに付与するアノデーション
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CopyIgnoreAnnotation {
}
