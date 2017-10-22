package com.ayush.priority

import akka.actor.{ActorSystem, Props}

object PriorityActorApp extends App{

  val actorSystem = ActorSystem("PrioritySystem")
  val priorityActor = actorSystem.actorOf(Props[PriorityActor].withDispatcher("prio-dispatcher"), "PriorityActor")

  priorityActor ! 1.0
  priorityActor ! Option
  priorityActor ! 33
  priorityActor ! "String printed first. Followed by Int Long and others"

}
