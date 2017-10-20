package com.ayush.custom_mailbox

import akka.actor.{Actor, ActorRef}
import akka.event.Logging

/**
  * Created by AyushM on 10/20/2017.
  */
class SpecialActor extends  Actor {

  val log = Logging(context.system, this)

  def receive = {
    case msg: String  => log.info(s"Msg is $msg")
  }
}

