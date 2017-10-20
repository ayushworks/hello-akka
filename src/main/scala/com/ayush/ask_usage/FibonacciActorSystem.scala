package com.ayush.ask_usage

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by AyushM on 10/17/2017.
  */
object FibonacciActorSystem extends App{

  implicit val timeout = Timeout(10 seconds)

  val actorSystem = ActorSystem("FibbonaciSystem")

  val actor = actorSystem.actorOf(Props[FibbonaciActor])

  val future = (actor ? 10).mapTo[Int]

  println(Await.result(future, 10 seconds))

}
