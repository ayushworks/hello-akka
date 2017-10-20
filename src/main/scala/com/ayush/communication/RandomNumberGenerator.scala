package com.ayush.communication

import akka.actor.Actor

import scala.util.Random._
/**
  * Created by AyushM on 10/18/2017.
  */
class RandomNumberGenerator extends Actor {

  import Messages._


  def receive  = {
    case GiveMeRandomNumber => sender() ! Done(nextInt())
  }
}
