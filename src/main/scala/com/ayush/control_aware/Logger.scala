package com.ayush.control_aware

import akka.actor.Actor
import akka.dispatch.ControlMessage
import akka.event.Logging

/**
  * Created by AyushM on 10/24/2017.
  */
class Logger extends Actor {

  val logger = Logging(context.system, this);

  def receive = {
    case x : MyObject => logger.info(s"Will process $x first as it extends ControlMessage ")
    case any => logger.info(s"received $any")
  }

}

case class MyObject(x:Any) extends ControlMessage
