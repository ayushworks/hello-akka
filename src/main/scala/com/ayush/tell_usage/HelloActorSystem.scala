package com.ayush.tell_usage

import akka.actor.{ActorSystem, Props}

/**
  * Created by AyushM on 10/16/2017.
  */
object HelloActorSystem extends App {

  val actorSystem = ActorSystem("HelloAkka")

  println(actorSystem)

  val actor = actorSystem.actorOf(Props(classOf[SummingActor],10),"FirstActor")

  println(actor.path)

  while(true) {
    Thread.sleep(3000)
    actor ! 1
  }

}
