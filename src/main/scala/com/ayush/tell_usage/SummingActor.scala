package com.ayush.tell_usage

import akka.actor.Actor

/**
  * Created by AyushM on 10/17/2017.
  */
class SummingActor(initial: Int) extends Actor{

  var sum = initial

  def receive = {
    case x: Int => {
      sum = sum +x
      println(s"State(sum) is $sum")
    }
    case _ => println("Excuse me?")
  }
}
