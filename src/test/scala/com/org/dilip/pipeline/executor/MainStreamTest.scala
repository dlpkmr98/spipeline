package com.org.dilip.pipeline.executor

import org.scalatest.FlatSpec
import com.org.dilip.pipeline.pipeline.Pipeline
import com.org.dilip.pipeline.jobs._
import scala.util._
import org.scalatest.Matchers
import scala.concurrent.ExecutionContext.Implicits.global

trait MainStreamService {
  implicit def >>[A](c: A) = c.asInstanceOf[Pipeline[Seq[String], Seq[String]]]
  def init[A, B <: String](f1: => A, f2: (A, B) => Unit) = (x: B) => f2(f1, x)
  implicit val pipelineExecutor: PipelineExecutor[Seq[String], Seq[String]] = SyncExecution()
  //implicit val pipelineExecutor: PipelineExecutor[Seq[String], Seq[String]] = new AsyncExecution()

}

class MainStreamTest extends FlatSpec with MainStreamService with Matchers {

  def createPipline = Pipeline[Seq[String], Seq[String]]() >> PrintJob1() >> PrintJob2() >> PrintJob3()
  def executePipline(pl: Pipeline[Seq[String], Seq[String]], in: String) = pl.start(in.split(",").toSeq) {
    case Success(s)  => println(s)
    case Failure(ex) => ex.printStackTrace()
  }

  "This Test" should "test pipeline" in {

    val res = MainStreamTest().init(createPipline, executePipline)("=>,=>")
    res.isInstanceOf[Unit] shouldBe true

  }
}
object MainStreamTest {
  def apply() = new MainStreamTest

}