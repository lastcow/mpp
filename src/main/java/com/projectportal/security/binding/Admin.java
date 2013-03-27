/**
 * 
 */
package com.projectportal.security.binding;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import org.jboss.seam.security.annotations.SecurityBindingType;



/**
 * @author lastcow
 *
 */
@SecurityBindingType
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface Admin {}
