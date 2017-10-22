package com.ayush.priority

import akka.actor.Actor
import akka.event.Logging

class PriorityActor extends Actor {

  val log = Logging(context.system, this)

  def receive = {
    case i : Int => log.info(s"$i")
    case s : String => log.info(s"$s")
    case l : Long => log.info(s"$l")
    case any => log.info(s"$any")
  }
}
