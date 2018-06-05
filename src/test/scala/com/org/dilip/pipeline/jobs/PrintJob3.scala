package com.org.dilip.pipeline.jobs

import com.org.dilip.pipeline.pipeline.Transformation

class PrintJob3 extends Transformation[Seq[String], Seq[String]] {

  override def execute = x => for (y <- x) yield print(y)
  def print(v: String) = v + "PrintJob3=>"
}

object PrintJob3 {
  def apply(): PrintJob3 = new PrintJob3()
}