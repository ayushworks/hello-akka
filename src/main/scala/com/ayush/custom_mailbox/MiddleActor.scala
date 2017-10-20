package com.ayush.custom_mailbox

import akka.actor.{Actor, ActorRef}
import akka.event.Logging
import akka.event.LoggingAdapter

/**
  * Created by AyushM on 10/20/2017.
  */
class MiddleActor extends Actor {

  val log = Logging(context.system, this)

  def receive = {
    case(msg: Any, actoref: ActorRef) => actoref ! msg

    case msg: String => log.info(msg)
  }

}
