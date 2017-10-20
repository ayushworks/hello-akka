package com.ayush.custom_mailbox

import akka.actor.{ActorSystem, Props}

/**
  * Created by AyushM on 10/20/2017.
  */
object CustomMailBoxApp extends App {

  val actorSystem = ActorSystem("CustomMailBoxAkka")

  val actor = actorSystem.actorOf(Props[SpecialActor].withDispatcher("custom-dispatcher"), "SpecialActor")

  val actor1 = actorSystem.actorOf(Props[MiddleActor], "MiddleActor")

  actor1 ! ("hello", actor)

  actor1 ! (2, actor)
}
