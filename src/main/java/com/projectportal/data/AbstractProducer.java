/**
 * 
 */
package com.projectportal.data;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author lastcow
 *
 */
public abstract class AbstractProducer {
	@Inject
	protected EntityManager em;
	protected Query query;
}
