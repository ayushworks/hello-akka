package com.ayush.priority

import akka.actor.ActorSystem
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import com.typesafe.config.Config

class PriorityActorMailBox(settings: ActorSystem.Settings, config : Config) extends UnboundedPriorityMailbox(PriorityGenerator{
  case i : Int => 1
  case s : String => 0
  case l : Long => 2
  case any => 3
})
