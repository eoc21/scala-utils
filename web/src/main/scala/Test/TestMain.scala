import play.api.libs.json._
//import web.EocHttpClient
import web.PubChemExtractor
/**
  * Created by edwardcannon on 03/08/2017.
  */
object TestMain {

  def main(args: Array[String]) {
    val pce = new PubChemExtractor()
    val molecularFormula = pce.extractChemicalFormula("2244")
    println(molecularFormula)
  }
}
