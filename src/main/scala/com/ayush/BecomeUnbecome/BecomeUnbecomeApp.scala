package com.ayush.BecomeUnbecome

import akka.actor.{ActorSystem, Props}

/**
  * Created by AyushM on 10/25/2017.
  */
object BecomeUnbecomeApp extends App {

  val actorSystem = ActorSystem("BecomeUnbecomeSystem")

  val actor = actorSystem.actorOf(Props[BecomeUnbecome], "BecomeUnbecome")

  actor ! Data(20)
  actor ! Data(20)
  actor ! Data(40)
  actor ! Data(20)

  Thread.sleep(100)

  actor ! Calc
  actor ! Average

  Thread.sleep(100)

  actor ! Data(100)

  Thread.sleep(100)

  actor ! Calc
  actor ! Average

  actor ! Stop

}
