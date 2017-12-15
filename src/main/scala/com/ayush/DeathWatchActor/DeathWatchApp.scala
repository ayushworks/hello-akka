package com.ayush.DeathWatchActor

import akka.actor.{Actor, ActorSystem, PoisonPill, Props, Terminated}
import akka.event.Logging


case object Service
case object Kill

class ServiceActor extends Actor {

  val logger = Logging(context.system, this)

  def receive = {
    case Service => logger.info("Processing special request")
  }

}

class DeathWatchActor extends Actor {

  val logger = Logging(context.system, this)

  val child = context.actorOf(Props[ServiceActor], "serviceActor")

  context.watch(child)

  def receive = {
    case Service => child ! Service
    case Kill => child ! PoisonPill
    case Terminated(`child`) =>   logger.info("special service actor is no more")
  }
}

object DeathWatchApp extends App {

  val system = ActorSystem("DeathWatch")

  val parent = system.actorOf(Props[DeathWatchActor], "DeathWatcher")

  parent ! Service

  parent ! Service

  parent ! Kill

  parent ! Service
}
