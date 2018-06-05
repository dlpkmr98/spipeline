package com.org.dilip.pipeline.jobs

import com.org.dilip.pipeline.pipeline.Transformation

class PrintJob1 extends Transformation[Seq[String], Seq[String]] {

  override def execute = x => for (y <- x) yield print(y)
  def print(v: String) = v + "PrintJob1=>"
}

object PrintJob1 {
  def apply(): PrintJob1 = new PrintJob1()
}