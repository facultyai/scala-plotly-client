package co.theasi.plotly.writer

import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL._

import co.theasi.plotly.{ScatterOptions, ScatterMode}

object OptionsWriter {

  def scatterOptionsToJson(options: ScatterOptions): JObject = {
    val xAxis = axisToJson(options.xAxis, "x")
    val yAxis = axisToJson(options.yAxis, "y")

    ("xaxis" -> xAxis) ~
    ("yaxis" -> yAxis) ~
    ("name" -> options.name) ~
    ("mode" -> scatterModeToJson(options.mode))
  }

  private def axisToJson(axis: Int, root: String): String =
    if (axis == 0) { root } else { root + (axis + 1).toString }

  private def scatterModeToJson(mode: Set[ScatterMode.Value]): String =
    if (mode.isEmpty) { "none" } else { mode.map { _.toString.toLowerCase }.mkString("+") }
}