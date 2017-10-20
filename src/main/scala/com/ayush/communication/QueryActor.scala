package com.ayush.communication

import akka.actor.{Actor, ActorRef}

/**
  * Created by AyushM on 10/18/2017.
  */
class QueryActor extends Actor {

  import Messages._

  def receive = {
    case Start(actorRef: ActorRef) => actorRef ! GiveMeRandomNumber
    case Done(randomNumber: Int) => println(s"received $randomNumber as the random number")
  }

}
