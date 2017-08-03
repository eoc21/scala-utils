import play.api.libs.json._
//import web.EocHttpClient
import web.PubChemExtractor
/**
  * Created by edwardcannon on 03/08/2017.
  */
object TestMain {

  def main(args: Array[String]) {
    val pce = new PubChemExtractor()
    //val molecularFormula = pce.extractChemicalFormulaFromName("Heroin-7,8-oxide")
    val properties = pce.extractAllProperties("Heroin-7,8-oxide")
    println(properties)
  }
}
