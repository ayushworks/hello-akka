package com.ayush.strategy.oneforone

import java.security.InvalidParameterException

import akka.actor.SupervisorStrategy.{Escalate, Restart, Stop}
import akka.actor.{Actor, ActorContext, ActorSystem, OneForOneStrategy, Props}
import akka.event.Logging

/**
  * Created by AyushM on 11/30/2017.
  */

class IntActor extends Actor {

  val logger = Logging(context.system, this)

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    logger.info(s"Restarting because of ${reason.getCause}")
  }

  override def receive: Receive = {
    case number: Double => logger.info(s"Received request to process $number . Square root is ${Math.sqrt(number)}")
    case _ => throw new InvalidParameterException
  }

}

class StringActor extends Actor {

  val logger = Logging(context.system, this)

  override def postStop(): Unit = {
    logger.info(s" ${context.self.path.name} getting stopped")
  }

  override def receive: Receive = {
    case msg: String => logger.info(s"Received request to process $msg. Length is ${msg.length}")
    case _ => throw new UnsupportedOperationException
  }

}

class SupervisorActor extends Actor {

  import scala.concurrent.duration._

  override val supervisorStrategy  = OneForOneStrategy(10, 1 minute) {
    case _: InvalidParameterException => Restart
    case _: UnsupportedOperationException => Stop
    case _ : Exception => Escalate
  }

  val intActor = context.actorOf(Props[IntActor], "IntActor")
  val stringActor = context.actorOf(Props[StringActor], "StringActor")

  override def receive : Receive = {
    case "Start" =>
      intActor ! 16.0
      intActor ! 24.0
      stringActor ! "String message"
      intActor ! "wrong" //will cause exception, actor wil restart
      stringActor ! 36.0 //will cause exception, actor will stop
      intActor ! 49.0 // will be processed as actor restarted
      stringActor ! "new String message" //this will go to dead letter queue
  }
}

object SupervisorStratergy extends App{

  val actorSystem = ActorSystem("Supervision")

  actorSystem.actorOf(Props[SupervisorActor], "SuperVisorActor") ! "Start"
}
