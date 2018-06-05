package com.org.dilip.pipeline.jobs

import com.org.dilip.pipeline.pipeline.Transformation

class PrintJob2 extends Transformation[Seq[String], Seq[String]] {

  override def execute = x => for (y <- x) yield print(y)
  def print(v: String) = v + "PrintJob2=>"
}

object PrintJob2 {
  def apply(): PrintJob2 = new PrintJob2()
}
  