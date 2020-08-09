package co.theasi.plotly.writer

import co.theasi.plotly.{AxisOptions, CartesianPlot, ViewPort}
import org.json4s._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CartesianPlotLayoutWriterSpec extends AnyFlatSpec with Matchers {

  val defaultViewPort = ViewPort((0.0, 0.5), (0.1, 0.6))

  "toJson" should "serialize axes with the right domain" in {
    val axisIndex = 1
    val plot = CartesianPlot()
    val jobj = CartesianPlotLayoutWriter.toJson(axisIndex, defaultViewPort, plot)

    (jobj \ "xaxis" \ "domain") shouldEqual JArray(
      List(JDouble(0.0), JDouble(0.5)))
    (jobj \ "yaxis" \ "domain") shouldEqual JArray(
      List(JDouble(0.1), JDouble(0.6)))
  }

  it should "serialize axes with the right anchor" in {
    val axisIndex = 2
    val plot = CartesianPlot()
    val jobj = CartesianPlotLayoutWriter.toJson(axisIndex, defaultViewPort, plot)

    (jobj \ "xaxis2" \ "anchor") shouldEqual JString("y2")
    (jobj \ "yaxis2" \ "anchor") shouldEqual JString("x2")
  }

  it should "serialize axis titles" in {
    val axisIndex = 1
    val plot = CartesianPlot()
      .xAxisOptions(AxisOptions().title("hello-x"))
    val jobj = CartesianPlotLayoutWriter.toJson(axisIndex, defaultViewPort, plot)

    (jobj \ "xaxis" \ "title") shouldEqual JString("hello-x")
    (jobj \ "yaxis" \ "title") shouldEqual JNothing
  }

}
