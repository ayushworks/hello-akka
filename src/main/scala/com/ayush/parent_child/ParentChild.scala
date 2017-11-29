package com.ayush.parent_child

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.actor.{ActorSystem, Props}

/**
  * Created by AyushM on 11/9/2017.
  */

case object CreateChild
case class Greet(msg: String)

class ChildActor extends Actor {

  val logger = Logging(context.system, this);

  def receive = {
    case Greet(msg) => logger.info(s"My parent [${self.path.parent}] greeted to me [${self.path}] $msg")
  }
}

class ParentActor extends Actor {

  val logger = Logging(context.system, this);

  def receive = {
    case CreateChild =>
      val child = context.actorOf(Props[ChildActor], "child")
      child ! Greet("Hello Child")
  }
}

object ParentChild extends App {
  val actorSystem = ActorSystem("MyActorSystem")
  val parent = actorSystem.actorOf(Props[ParentActor], "parent")
  parent ! CreateChild
}