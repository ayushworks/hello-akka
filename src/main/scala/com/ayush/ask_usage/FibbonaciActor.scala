package com.ayush.ask_usage

import akka.actor.Actor

/**
  * Created by AyushM on 10/17/2017.
  */
class FibbonaciActor extends Actor {

  def receive = {
    case x:Int =>
      val res = fib(x)
      sender ! res
  }

  def fib(x: Int) : Int =
    x match {
      case 0 | 1=> x
      case _ => fib(x-1) + fib(x-2)
    }

}
