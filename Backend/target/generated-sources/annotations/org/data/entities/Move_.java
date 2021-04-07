package org.data.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Move.class)
public abstract class Move_ {

	public static volatile SingularAttribute<Move, Integer> xSource;
	public static volatile SingularAttribute<Move, Integer> ySource;
	public static volatile SingularAttribute<Move, Integer> xTarget;
	public static volatile SingularAttribute<Move, Integer> yTarget;

	public static final String X_SOURCE = "xSource";
	public static final String Y_SOURCE = "ySource";
	public static final String X_TARGET = "xTarget";
	public static final String Y_TARGET = "yTarget";

}

