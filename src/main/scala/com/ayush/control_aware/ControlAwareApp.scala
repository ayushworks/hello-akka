package com.ayush.control_aware

import akka.actor.{ActorSystem, Props}

/**
  * Created by AyushM on 10/24/2017.
  */
object ControlAwareApp extends App {

  val actorSystem = ActorSystem("ControlAware")

  val actor = actorSystem.actorOf(Props[Logger].withDispatcher("control-aware-dispatcher"), "Logger")

  actor ! 1
  actor ! "hello"
  actor ! MyObject(22)


}
