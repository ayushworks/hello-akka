package com.ayush.communication

import akka.actor.{ActorRef, ActorSystem, Props}
import Messages.Start

/**
  * Created by AyushM on 10/18/2017.
  */


object Messages {
  case class Done(randomNumber: Int)
  case object GiveMeRandomNumber
  case class Start(actorRef: ActorRef)
}
object Communication extends  App {

  val actorSystem = ActorSystem("communication")

  val randomNumberGenerator = actorSystem.actorOf(Props[RandomNumberGenerator])

  val queryActor = actorSystem.actorOf(Props[QueryActor])

  while(true) {
    queryActor ! Start(randomNumberGenerator)
  }
}
