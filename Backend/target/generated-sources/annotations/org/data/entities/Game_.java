package org.data.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Game.class)
public abstract class Game_ {

	public static volatile SingularAttribute<Game, User> user1;
	public static volatile SingularAttribute<Game, User> user2;
	public static volatile SingularAttribute<Game, User> winner;
	public static volatile ListAttribute<Game, Move> moves;
	public static volatile SingularAttribute<Game, Long> id;

	public static final String USER1 = "user1";
	public static final String USER2 = "user2";
	public static final String WINNER = "winner";
	public static final String MOVES = "moves";
	public static final String ID = "id";

}

