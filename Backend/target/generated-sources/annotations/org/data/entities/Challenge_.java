package org.data.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Challenge.class)
public abstract class Challenge_ {

	public static volatile SingularAttribute<Challenge, User> challenged;
	public static volatile SingularAttribute<Challenge, Boolean> isAccepted;
	public static volatile SingularAttribute<Challenge, User> challenger;
	public static volatile SingularAttribute<Challenge, Long> id;

	public static final String CHALLENGED = "challenged";
	public static final String IS_ACCEPTED = "isAccepted";
	public static final String CHALLENGER = "challenger";
	public static final String ID = "id";

}

