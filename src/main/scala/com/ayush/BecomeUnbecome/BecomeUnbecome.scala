package com.ayush.BecomeUnbecome

import akka.actor.Actor
import akka.event.Logging

import scala.collection.mutable.ListBuffer

/**
  * Created by AyushM on 10/25/2017.
  */
class BecomeUnbecome extends Actor {

  val list: scala.collection.mutable.ListBuffer[Int] = ListBuffer[Int]()

  val logger =  Logging(context.system, this)

  def receive = {
    firstreceive
  }

  def firstreceive : Receive = {
    case Data(d) => logger.info(s"appending to list $d value")
                    list.append(d)
    case Calc => context.become(average)
    case Stop => context.stop(self)
  }

  def average : Receive = {
    case Average => logger.info(s"Average is ${list.sum/list.size}")
              context.become(firstreceive)
    case Stop => context.stop(self)
  }
}


case class Data(d: Int)
case object Average
case object Calc
case object Stop
