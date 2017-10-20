package com.ayush.custom_mailbox

import akka.actor.{ActorRef, ActorSystem}
import akka.dispatch.{Envelope, MailboxType, MessageQueue, ProducesMessageQueue}
import java.util.concurrent.ConcurrentLinkedQueue

import com.typesafe.config.Config

/**
  * Created by AyushM on 10/18/2017.
  */
class MyMessageQueue extends MessageQueue{

  private final val queue = new ConcurrentLinkedQueue[Envelope]()

  override def enqueue(receiver: ActorRef, handle: Envelope): Unit = {

    if(handle.message.isInstanceOf[String]) {
      handle.sender ! s"Will process String request"
      queue.offer(handle)
    }

    else handle.sender ! s"I do not know ${handle.message.getClass} type"
  }

  override def dequeue(): Envelope = queue.poll

  override def numberOfMessages: Int = queue.size

  override def hasMessages: Boolean = !queue.isEmpty

  override def cleanUp(owner: ActorRef, deadLetters: MessageQueue): Unit = {
    while(hasMessages) {
      deadLetters.enqueue(owner, dequeue)
    }
  }
}

class UnboundedMailBox extends MailboxType with ProducesMessageQueue[MyMessageQueue] {

  // This constructor signature must exist, it will be called by Akka
  def this(settings: ActorSystem.Settings,
           config: Config) = { this()
  }
  override def create(owner: Option[ActorRef], system: Option[ActorSystem]): MessageQueue = new MyMessageQueue
}
